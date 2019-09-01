package com.batdemir.android.todolist.application.android.Tools;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ToolPdf {

    private String TAG = ToolPdf.class.getSimpleName();
    private final Activity activity;
    private final Context context;
    private boolean sendMail = false;

    public ToolPdf(Context context, boolean sendMail) {
        this.context = context;
        this.sendMail = sendMail;
        this.activity = (Activity) context;
    }

    public void createPdf(CardView cardView, RecyclerView recyclerView) {
        File folder = new File(new Tool(context).getPath(), "TodoPdf");

        if (!folder.exists()) {
            folder.mkdir();
        }

        String path = folder.getAbsolutePath() + "/todoPdf.pdf";

        String imageName = "todoJpg.jpg";
        String imagePath = new File(folder, imageName).getPath();

        FileOutputStream fileOutputStream = null;
        Bitmap bitmap = getRecyclerViewScreenshot(recyclerView);

        try {
            fileOutputStream = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        savePdf(bitmap, path);
    }

    private void savePdf(Bitmap bitmap, String path) {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                bitmap.getWidth(),
                bitmap.getHeight(),
                1
        ).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(context.getColor(R.color.white));
        canvas.drawPaint(paint);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth(),
                bitmap.getHeight(),
                true
        );

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        File createdFile = new File(path);
        try {
            document.writeTo(new FileOutputStream(createdFile));
            Toast.makeText(context, context.getString(R.string.pdf_created), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        document.close();

        if (sendMail) {
            sendMailPdf(createdFile);
        } else {
            openPdf(createdFile);
        }
    }

    private void openPdf(File createdFile) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(createdFile), "application/pdf");
        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent intent = Intent.createChooser(target, context.getString(R.string.open_file));
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMailPdf(File createdFile) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Intent emailSelectorIntent = new Intent(Intent.ACTION_SENDTO);
        emailSelectorIntent.setData(Uri.parse("mailto:"));

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        emailIntent.setSelector(emailSelectorIntent);

        Uri attachment = Uri.fromFile(createdFile);
        emailIntent.putExtra(Intent.EXTRA_STREAM, attachment);

        Intent intent = Intent.createChooser(emailIntent, context.getString(R.string.send_file));
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getRecyclerViewScreenshot(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                );
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {
                    bitmaCache.put(String.valueOf(i), drawingCache);
                }
                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            bigCanvas.drawColor(Color.WHITE);

            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmaCache.get(String.valueOf(i));
                bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }
        }
        return bigBitmap;
    }

}
