package com.batdemir.android.todolist.application.android.Tools.AlertDialogTools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.databinding.AlertDialogBinding;

public class ToolAlertDialog extends DialogFragment implements BaseActions {

    private AlertDialogBinding binding;
    private static final String key_message = "KEY_MESSAGE";
    private static final String key_showCancelButton = "KEY_SHOWCANCELBUTTON";
    private String message;
    private Boolean showCancelButton;

    public static ToolAlertDialog newInstance(String message, boolean isCancelable, boolean showCancelButton) {
        ToolAlertDialog toolAlertDialog = new ToolAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putString(key_message, message);
        bundle.putBoolean(key_showCancelButton, showCancelButton);
        toolAlertDialog.setArguments(bundle);
        toolAlertDialog.setCancelable(isCancelable);
        return toolAlertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.alert_dialog, null, false);
        getObjectReferences();
        loadData();
        setListeners();
        return binding.getRoot();
    }

    @Override
    public void getObjectReferences() {
        assert getArguments() != null;
        message = getArguments().getString(key_message);
        showCancelButton = getArguments().getBoolean(key_showCancelButton);
        new Tool(getContext()).animDialog(binding.linearProps);
    }

    @Override
    public void loadData() {
        binding.txtEditMessage.setText(message);
    }

    @Override
    public void setListeners() {
        binding.btnOkey.setOnTouchListener(new OnTouchEvent(binding.btnOkey));
        if (showCancelButton) {
            binding.btnCancel.setOnTouchListener(new OnTouchEvent(binding.btnCancel));
        } else {
            binding.btnCancel.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 0));
        }

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertClickListener clickCancel = (ToolAlertDialog.AlertClickListener) getActivity();
                assert clickCancel != null;
                clickCancel.alertCancel();
                dismiss();
            }
        });

        binding.btnOkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertClickListener clickOkey = (ToolAlertDialog.AlertClickListener) getActivity();
                assert clickOkey != null;
                clickOkey.alertOkey();
                dismiss();
            }
        });
    }

    public interface AlertClickListener {
        void alertOkey();

        void alertCancel();
    }
}
