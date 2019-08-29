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
import android.widget.Toast;

import com.batdemir.android.todolist.application.android.API.Services.TaskService;
import com.batdemir.android.todolist.application.android.API.Services.TodoListService;
import com.batdemir.android.todolist.application.android.API.Services.TodoService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools.SwipeToDeleteCallback;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewSelectTasks;
import com.batdemir.android.todolist.application.android.databinding.ActivityTodoListDefinationBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import retrofit2.Response;

public class TodoListDefinationActivity extends BaseActivity implements
        AdapterRecyclerViewSelectTasks.TasksItemListener,
        TaskService.TaskServiceListener,
        TodoService.TaskServiceListener,
        TodoListService.TodoListServiceListener,
        ToolAlertDialog.AlertClickListener{

    private Context context;
    private ActivityTodoListDefinationBinding binding;
    private ArrayList<TaskModel> taskModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true,"TodoList Defination");
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_todo_list_defination);
        taskModels = new ArrayList<>();
    }

    @Override
    public void loadData() {
        new TaskService<>(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
    }

    @Override
    public void setListeners() {
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFloatButton();
            }
        });
    }

    //----functions----//

    private boolean controlInputs(){
        String message = "";
        message += binding.editTextName.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.textInputName.getHint() + "!\n":"";
        message += taskModels.size()==0
                ?"Please Select Some Tasks In Selectable View !\n":"";

        if(message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message,true,false).show(getSupportFragmentManager(),TaskDefinationActivity.class.getSimpleName());
            return false;
        }
    }

    private TodoModel getTodoModel(){
        return new TodoModel(
                UUID.randomUUID().toString(),
                binding.editTextName.getText().toString(),
                GlobalVariable.userModel.getUserName(),
                Boolean.TRUE,
                new ToolTimeExpressions().setDateToString(Calendar.getInstance().getTime(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)
        );
    }

    private TodoListModel getTodoListModel(TaskModel taskModel){
        return new TodoListModel(
                UUID.randomUUID().toString(),
                binding.editTextName.getText().toString(),
                GlobalVariable.userModel.getUserName(),
                taskModel.getName(),
                "Created",
                Boolean.TRUE,
                new ToolTimeExpressions().setDateToString(Calendar.getInstance().getTime(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)
        );
    }

    private void clickFloatButton(){
        if(controlInputs()){
            new TodoService<>(context).Insert(getTodoModel());
        }
    }

    @Override
    public void onSuccess(TaskService.OperationType operationType, Response response) {
        switch (operationType){
            case GetTasksByUser:
                AdapterRecyclerViewSelectTasks adapterRecyclerViewSelectableTasks = new AdapterRecyclerViewSelectTasks(context, (ArrayList<TaskModel>) response.body());
                binding.recyclerViewSelectableTasks.setAdapter(adapterRecyclerViewSelectableTasks);
                binding.recyclerViewSelectableTasks.setItemViewCacheSize(((ArrayList<TaskModel>) response.body()).size());
                binding.recyclerViewSelectableTasks.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
                break;
        }
    }

    @Override
    public void onSuccess(TodoService.OperationType operationType, Response response) {
        switch (operationType){
            case Insert:
                for(int i=0;i<taskModels.size();i++){
                    new TodoListService<>(context).Insert(getTodoListModel(taskModels.get(i)));
                }
        }
    }

    @Override
    public void onSuccess(TodoListService.OperationType operationType, Response response) {
        switch (operationType){
            case Insert:
                binding.editTextName.setText("");
                new TaskService<>(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
                break;
        }
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onItemCheckedChanged(boolean isChecked, TaskModel taskModel) {
        if(isChecked)
            taskModels.add(taskModel);
        else
            taskModels.remove(taskModel);
    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }
}
