package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.API.Services.ConnectService;
import com.batdemir.android.todolist.application.android.API.Services.CustomService;
import com.batdemir.android.todolist.application.android.API.Services.TaskService;
import com.batdemir.android.todolist.application.android.API.Services.TodoListService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoTasksModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools.SwipeCallBack;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolPdf;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TodoListDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewTodoV2;
import com.batdemir.android.todolist.application.android.databinding.ActivityTodoListV2Binding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import retrofit2.Response;

public class TodoListActivityV2 extends BaseActivity implements
        ConnectService.ConnectServiceListener {

    private Context context;
    private ActivityTodoListV2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemNoFilter:
                new CustomService(context).GetTodoTasksV2(GlobalVariable.userModel.getUserName());
                return true;
            case R.id.menuItemShowOnlyCompleted:
                setFilterByCompleted();
                return true;
            case R.id.menuItemShowOnlyExperid:
                setFilterByExpired();
                return true;
            case R.id.sendViaEmail:
                new ToolPdf(context, true).createPdf(binding.cardView, binding.recyclerViewTodoListV2);
                return true;
            case R.id.saveLocale:
                new ToolPdf(context, false).createPdf(binding.cardView, binding.recyclerViewTodoListV2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true, getString(R.string.menu));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_todo_list_v2);
    }

    @Override
    public void loadData() {
        new CustomService(context).GetTodoTasksV2(GlobalVariable.userModel.getUserName());
    }

    @Override
    public void setListeners() {

        binding.txtTodoList.setOnTouchListener(new OnTouchEvent(binding.txtTodoList));
        binding.txtCreatedDate.setOnTouchListener(new OnTouchEvent(binding.txtCreatedDate));
        binding.txtTask.setOnTouchListener(new OnTouchEvent(binding.txtTask));
        binding.txtPriority.setOnTouchListener(new OnTouchEvent(binding.txtPriority));
        binding.txtDeadLine.setOnTouchListener(new OnTouchEvent(binding.txtDeadLine));
        binding.txtStatus.setOnTouchListener(new OnTouchEvent(binding.txtStatus));

        binding.txtTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
                ArrayList<CustomTodoTasksModel> customTodoTasksModels = adapterRecyclerViewTodoV2.getModels();
                if (v.isSelected()) {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_TODONAME(true));
                } else {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_TODONAME(false));
                }
                setOrderingDrawable(binding.txtTodoList);
                binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, customTodoTasksModels));
            }
        });

        binding.txtCreatedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
                ArrayList<CustomTodoTasksModel> customTodoTasksModels = adapterRecyclerViewTodoV2.getModels();
                if (v.isSelected()) {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_CREATEDDATE(true));
                } else {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_CREATEDDATE(false));
                }
                setOrderingDrawable(binding.txtCreatedDate);
                binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, customTodoTasksModels));
            }
        });

        binding.txtTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
                ArrayList<CustomTodoTasksModel> customTodoTasksModels = adapterRecyclerViewTodoV2.getModels();
                if (v.isSelected()) {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_TASKNAME(true));
                } else {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_TASKNAME(false));
                }
                setOrderingDrawable(binding.txtTask);
                binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, customTodoTasksModels));
            }
        });

        binding.txtPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
                ArrayList<CustomTodoTasksModel> customTodoTasksModels = adapterRecyclerViewTodoV2.getModels();
                if (v.isSelected()) {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_PRIORITY(true));
                } else {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_PRIORITY(false));
                }
                setOrderingDrawable(binding.txtPriority);
                binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, customTodoTasksModels));
            }
        });

        binding.txtDeadLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
                ArrayList<CustomTodoTasksModel> customTodoTasksModels = adapterRecyclerViewTodoV2.getModels();
                if (v.isSelected()) {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_DEADLINE(true));
                } else {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_DEADLINE(false));
                }
                setOrderingDrawable(binding.txtDeadLine);
                binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, customTodoTasksModels));
            }
        });

        binding.txtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
                ArrayList<CustomTodoTasksModel> customTodoTasksModels = adapterRecyclerViewTodoV2.getModels();
                if (v.isSelected()) {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_STATUS(true));
                } else {
                    Collections.sort(customTodoTasksModels, CustomTodoTasksModel.ORDERBY_STATUS(false));
                }
                setOrderingDrawable(binding.txtStatus);
                binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, customTodoTasksModels));
            }
        });
    }

    //----functions----//

    private void setOrderingDrawable(TextView textView) {
        if (textView.isSelected()) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_order_down_mini), null, null, null);
        } else {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_order_up_mini), null, null, null);
        }
    }

    private void setFilterByCompleted() {
        ArrayList<CustomTodoTasksModel> filteredList = new ArrayList<>();
        AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
        for (int i = 0; i < adapterRecyclerViewTodoV2.getModels().size(); i++) {
            if (adapterRecyclerViewTodoV2.getModels().get(i).getStatusName().equals("Completed"))
                filteredList.add(adapterRecyclerViewTodoV2.getModels().get(i));
        }
        binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, filteredList));
    }

    private void setFilterByExpired() {
        ArrayList<CustomTodoTasksModel> filteredList = new ArrayList<>();
        AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = (AdapterRecyclerViewTodoV2) binding.recyclerViewTodoListV2.getAdapter();
        for (int i = 0; i < adapterRecyclerViewTodoV2.getModels().size(); i++) {
            Date now = Calendar.getInstance().getTime();
            if (now.compareTo(new ToolTimeExpressions()
                    .setStringToDate(
                            adapterRecyclerViewTodoV2.getModels().get(i).getDeadLine(),
                            GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)) > 0)
                filteredList.add(adapterRecyclerViewTodoV2.getModels().get(i));
        }
        binding.recyclerViewTodoListV2.setAdapter(new AdapterRecyclerViewTodoV2(context, filteredList));
    }

    private void setCompleteSwipe(AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2) {
        new ItemTouchHelper(new SwipeCallBack(this, false, false) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final CustomTodoTasksModel item = adapterRecyclerViewTodoV2.getModels().get(position);
                final TodoListModel todoListModel = new TodoListModel(item.getId(), item.getTodoName(), item.getUserName(), item.getTaskName(), "Completed", item.getActive(), item.getCreatedDate());
                new TodoListService(context).Update(todoListModel);
                Snackbar snackbar = Snackbar.make(binding.rootTodoList, todoListModel.getTodoName() + getString(R.string.was_completed_from_the_list), Snackbar.LENGTH_LONG);
                snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        todoListModel.setStatusName("Created");
                        new TodoListService(context).Update(todoListModel);
                        binding.recyclerViewTodoListV2.scrollToPosition(position);
                    }
                });
                snackbar.setActionTextColor(getColor(R.color.white));
                snackbar.show();
            }
        }).attachToRecyclerView(binding.recyclerViewTodoListV2);
    }

    private void setDeleteSwipe(AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2) {
        new ItemTouchHelper(new SwipeCallBack(this, false, true) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final CustomTodoTasksModel item = adapterRecyclerViewTodoV2.getModels().get(position);
                final TodoListModel todoListModel = new TodoListModel(item.getId(), item.getTodoName(), item.getUserName(), item.getTaskName(), item.getStatusName(), item.getActive(), item.getCreatedDate());
                new TodoListService(context).Delete(todoListModel);
                Snackbar snackbar = Snackbar.make(binding.rootTodoList, todoListModel.getTodoName() + getString(R.string.was_removed_from_the_list), Snackbar.LENGTH_LONG);
                snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new TodoListService(context).Insert(todoListModel);
                        binding.recyclerViewTodoListV2.scrollToPosition(position);
                    }
                });
                snackbar.setActionTextColor(getColor(R.color.white));
                snackbar.show();
            }
        }).attachToRecyclerView(binding.recyclerViewTodoListV2);
    }

    private void setSearch(AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2) {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")) {
                    adapterRecyclerViewTodoV2.getFilter().filter(null);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    adapterRecyclerViewTodoV2.getFilter().filter(null);
                } else {
                    adapterRecyclerViewTodoV2.getFilter().filter(newText);
                }
                return false;
            }
        });
    }

    @Override
    public void onSuccess(ConnectService.OperationType operationType, Response response) {
        switch (operationType) {
            case CustomGetV2:
                AdapterRecyclerViewTodoV2 adapterRecyclerViewTodoV2 = new AdapterRecyclerViewTodoV2(context, (ArrayList<CustomTodoTasksModel>) response.body());
                binding.recyclerViewTodoListV2.setAdapter(adapterRecyclerViewTodoV2);
                binding.recyclerViewTodoListV2.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
                binding.recyclerViewTodoListV2.setItemViewCacheSize(((ArrayList<CustomTodoTasksModel>) response.body()).size());
                setCompleteSwipe(adapterRecyclerViewTodoV2);
                setDeleteSwipe(adapterRecyclerViewTodoV2);
                setSearch(adapterRecyclerViewTodoV2);
                break;
            case TodoListUpdate:
            case TodoListDelete:
                new CustomService(context).GetTodoTasksV2(GlobalVariable.userModel.getUserName());
                break;
        }
    }

    @Override
    public void onFailure(CustomService.OperationType operationType) {
        switch (operationType) {
            case CustomGetV2:
                new Tool(context).move(TodoListDefinationActivity.class, true, false);
                break;
        }
    }
}
