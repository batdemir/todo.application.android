package com.batdemir.android.todolist.application.android.UI.Activities.DetailSection;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.batdemir.android.todolist.application.android.API.Services.ConnectService;
import com.batdemir.android.todolist.application.android.API.Services.TaskService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools.SwipeCallBack;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewTasks;
import com.batdemir.android.todolist.application.android.databinding.ActivityTaskDefinationBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import retrofit2.Response;

public class TaskDefinationActivity extends BaseActivity implements
        AdapterRecyclerViewTasks.TasksItemListener,
        ConnectService.ConnectServiceListener,
        ToolAlertDialog.AlertClickListener {

    private Context context;
    private ActivityTaskDefinationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true, getString(R.string.task_defination));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_task_defination);
    }

    @Override
    public void loadData() {
        new TaskService(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
    }

    @Override
    public void setListeners() {
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFloatButton();
            }
        });
        new ToolTimeExpressions(context).showDatePicker(binding.contraintDeadCalender, binding.txtEditDeadLineCalender, getString(R.string.please_select_date), getString(R.string.yes), getString(R.string.no));
        new ToolTimeExpressions(context).showTimePicker(binding.contraintDeadLineClock, binding.txtEditDeadLineClock, getString(R.string.please_select_time), getString(R.string.yes), getString(R.string.no));
    }

    //----functions----//

    private boolean controlInputs() {
        String message = "";
        message += binding.editTextName.getError() != null || binding.editTextName.getText().toString().isEmpty()
                ? getString(R.string.please_enter_correctly) + binding.textInputName.getHint() + "!\n" : "";
        message += binding.txtEditDeadLineCalender.getText().toString().isEmpty()
                ? getString(R.string.please_select_date) + "\n" : "";
        message += binding.txtEditDeadLineClock.getText().toString().isEmpty()
                ? getString(R.string.please_select_time) + "\n" : "";

        if (message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message, true, false).show(getSupportFragmentManager(), TaskDefinationActivity.class.getSimpleName());
            return false;
        }
    }

    private TaskModel getTaskModel() {
        return new TaskModel(
                UUID.randomUUID().toString(),
                binding.editTextName.getText().toString(),
                binding.editTextNotes.getText().toString(),
                new ToolTimeExpressions().setDateFormat(binding.txtEditDeadLineCalender.getText().toString() + " " + binding.txtEditDeadLineClock.getText().toString(), GlobalVariable.DateFormat.SHOW_FULL_FORMAT, GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT),
                binding.spinnerPriority.getSelectedItem().toString(),
                GlobalVariable.userModel.getUserName(),
                Boolean.TRUE,
                new ToolTimeExpressions().setDateToString(Calendar.getInstance().getTime(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)
        );
    }

    private void clickFloatButton() {
        if (controlInputs()) {
            if (binding.floatingActionButton.getTag() != null) {
                new TaskService(context).Update(getTaskModel());
            } else {
                new TaskService(context).Insert(getTaskModel());
            }
        }
    }

    @Override
    public void onSuccess(TaskService.OperationType operationType, Response response) {
        assert response.body() != null;
        switch (operationType) {
            case TaskGetTasksByUser:
                AdapterRecyclerViewTasks adapterRecyclerViewTasks = new AdapterRecyclerViewTasks(context, (ArrayList<TaskModel>) response.body());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);
                binding.recyclerViewTasks.setAdapter(adapterRecyclerViewTasks);
                binding.recyclerViewTasks.setLayoutManager(gridLayoutManager);
                binding.recyclerViewTasks.setItemViewCacheSize(((ArrayList<TaskModel>) response.body()).size());
                new ItemTouchHelper(new SwipeCallBack(this, false, true) {
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        final int position = viewHolder.getAdapterPosition();
                        final TaskModel item = adapterRecyclerViewTasks.getModels().get(position);
                        adapterRecyclerViewTasks.notifyItemRemoved(position);
                        new TaskService(context).Delete(item);
                        Snackbar snackbar = Snackbar.make(binding.rootTaskDefination, item.getName() + getString(R.string.was_removed_from_the_list), Snackbar.LENGTH_LONG);
                        snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new TaskService(context).Insert(item);
                                binding.recyclerViewTasks.scrollToPosition(position);
                            }
                        });
                        snackbar.setActionTextColor(getColor(R.color.white));
                        snackbar.show();
                    }
                }).attachToRecyclerView(binding.recyclerViewTasks);
                break;
            case TaskInsert:
            case TaskUpdate:
                new TaskService(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
                binding.floatingActionButton.setTag(null);
                binding.floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_add_mini));
                binding.editTextName.setText("");
                binding.editTextNotes.setText("");
                binding.txtEditDeadLineCalender.setText("");
                binding.txtEditDeadLineClock.setText("");
                break;
            case TaskDelete:
                new TaskService(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
                break;
        }
    }

    @Override
    public void onFailure(TaskService.OperationType operationType) {
    }

    @Override
    public void onItemClick(TaskModel taskModel) {
        binding.floatingActionButton.setTag(taskModel.getName());
        binding.floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_tick_mini));
        binding.editTextName.setText(taskModel.getName());
        binding.editTextNotes.setText(taskModel.getDescription());
        binding.txtEditDeadLineCalender.setText(new ToolTimeExpressions().setDateFormat(taskModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_DATE_FORMAT));
        binding.txtEditDeadLineClock.setText(new ToolTimeExpressions().setDateFormat(taskModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SMALL_TIME_FORMAT));

        String[] priorityList = getResources().getStringArray(R.array.priority_list);
        for (int i = 0; i < priorityList.length; i++) {
            if (priorityList[i].equals(taskModel.getPriorityName())) {
                binding.spinnerPriority.setSelection(i, true);
            }
        }
    }

    @Override
    public void alertCancel() {
    }

    @Override
    public void alertOkey() {
    }

}