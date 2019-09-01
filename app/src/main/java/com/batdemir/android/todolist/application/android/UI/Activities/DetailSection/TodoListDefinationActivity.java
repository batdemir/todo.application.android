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
import com.batdemir.android.todolist.application.android.API.Services.TodoListService;
import com.batdemir.android.todolist.application.android.API.Services.TodoService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools.SwipeCallBack;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewSelectTasks;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewTodo;
import com.batdemir.android.todolist.application.android.databinding.ActivityTodoListDefinationBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import retrofit2.Response;

public class TodoListDefinationActivity extends BaseActivity implements
        AdapterRecyclerViewSelectTasks.TasksItemListener,
        AdapterRecyclerViewTodo.TodoItemListener,
        ConnectService.ConnectServiceListener,
        ToolAlertDialog.AlertClickListener {

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
        init_toolbar(true, getString(R.string.todolist_defination));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_todo_list_defination);
        taskModels = new ArrayList<>();
    }

    @Override
    public void loadData() {
        new TaskService(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
        new TodoService(context).GetTodoByUser(GlobalVariable.userModel.getUserName());
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

    private boolean controlInputs() {
        String message = "";
        message += binding.editTextName.getText().toString().isEmpty()
                ? getString(R.string.please_enter_correctly) + binding.textInputName.getHint() + "!\n" : "";
        message += taskModels.size() == 0
                ? getString(R.string.please_select_some_tasks_in_selectable_view) : "";

        if (message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message, true, false).show(getSupportFragmentManager(), TaskDefinationActivity.class.getSimpleName());
            return false;
        }
    }

    private TodoModel getTodoModel() {
        return new TodoModel(
                UUID.randomUUID().toString(),
                binding.editTextName.getText().toString(),
                GlobalVariable.userModel.getUserName(),
                Boolean.TRUE,
                new ToolTimeExpressions().setDateToString(Calendar.getInstance().getTime(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)
        );
    }

    private TodoListModel getTodoListModel(TaskModel taskModel) {
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

    private void clickFloatButton() {
        if (controlInputs()) {
            new TodoService(context).Insert(getTodoModel());
        }
    }

    @Override
    public void onSuccess(ConnectService.OperationType operationType, Response response) {
        switch (operationType) {
            case TaskGetTasksByUser:
                AdapterRecyclerViewSelectTasks adapterRecyclerViewSelectableTasks = new AdapterRecyclerViewSelectTasks(context, (ArrayList<TaskModel>) response.body());
                binding.recyclerViewSelectableTasks.setAdapter(adapterRecyclerViewSelectableTasks);
                binding.recyclerViewSelectableTasks.setItemViewCacheSize(((ArrayList<TaskModel>) response.body()).size());
                binding.recyclerViewSelectableTasks.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
                break;
            case TodoGetTodoByUser:
                AdapterRecyclerViewTodo adapterRecyclerViewTodo = new AdapterRecyclerViewTodo(context, (ArrayList<TodoModel>) response.body());
                binding.recyclerViewTodoList.setAdapter(adapterRecyclerViewTodo);
                binding.recyclerViewTodoList.setItemViewCacheSize(((ArrayList<TodoModel>) response.body()).size());
                binding.recyclerViewTodoList.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
                new ItemTouchHelper(new SwipeCallBack(this, true, true) {
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        final int position = viewHolder.getAdapterPosition();
                        final TodoModel item = adapterRecyclerViewTodo.getModels().get(position);
                        new TodoService(context).Delete(item);
                        Snackbar snackbar = Snackbar.make(binding.rootTodoListDefination, item.getName() + getString(R.string.was_removed_from_the_list), Snackbar.LENGTH_LONG);
                        snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new TodoService(context).Insert(item);
                                binding.recyclerViewTodoList.scrollToPosition(position);
                            }
                        });
                        snackbar.setActionTextColor(getColor(R.color.white));
                        snackbar.show();
                    }
                }).attachToRecyclerView(binding.recyclerViewTodoList);
                break;
            case TodoInsert:
                for (int i = 0; i < taskModels.size(); i++) {
                    new TodoListService(context).Insert(getTodoListModel(taskModels.get(i)));
                }
                new TodoService(context).GetTodoByUser(GlobalVariable.userModel.getUserName());
                break;
            case TodoDelete:
                new TodoService(context).GetTodoByUser(GlobalVariable.userModel.getUserName());
            case TodoListInsert:
                binding.editTextName.setText("");
                new TaskService(context).GetTasksByUser(GlobalVariable.userModel.getUserName());
                break;

        }
    }

    @Override
    public void onFailure(TaskService.OperationType operationType) {
        switch (operationType) {
            case TaskGetTasksByUser:
                new Tool(context).move(TaskDefinationActivity.class, true, false);
                break;
        }
    }

    @Override
    public void onItemCheckedChanged(boolean isChecked, TaskModel taskModel) {
        if (isChecked)
            taskModels.add(taskModel);
        else
            taskModels.remove(taskModel);
    }

    @Override
    public void onItemClick(TodoModel todoModel) {

    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }
}
