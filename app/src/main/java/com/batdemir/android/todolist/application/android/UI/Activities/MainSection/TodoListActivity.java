package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.batdemir.android.todolist.application.android.API.Services.CustomService;
import com.batdemir.android.todolist.application.android.API.Services.TodoListService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolPdf;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TodoListDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewTodoList;
import com.batdemir.android.todolist.application.android.databinding.ActivityTodoListBinding;

import java.util.ArrayList;

import retrofit2.Response;

public class TodoListActivity extends BaseActivity implements
        CustomService.CustomServiceListener,
        TodoListService.TodoListServiceListener {

    private Context context;
    private ActivityTodoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sendViaEmail:
                new ToolPdf(context,true).createPdf(binding.cardView,binding.recyclerViewTodoList);
                return true;
            case R.id.saveLocale:
                new ToolPdf(context,false).createPdf(binding.cardView,binding.recyclerViewTodoList);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true,getString(R.string.menu));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_todo_list);
    }

    @Override
    public void loadData() {
        new CustomService<>(context).Get(GlobalVariable.userModel.getUserName());
    }

    @Override
    public void setListeners() {

    }

    //----functions----//

    @Override
    public void onSuccess(CustomService.OperationType operationType, Response response) {
         switch (operationType){
             case Get:
                 AdapterRecyclerViewTodoList adapterRecyclerViewTodoList = new AdapterRecyclerViewTodoList(context, (ArrayList<CustomTodoModel>) response.body());
                 binding.recyclerViewTodoList.setAdapter(adapterRecyclerViewTodoList);
                 binding.recyclerViewTodoList.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
                 binding.recyclerViewTodoList.setItemViewCacheSize(((ArrayList<CustomTodoModel>) response.body()).size());
                 break;
         }
    }

    @Override
    public void onSuccess(TodoListService.OperationType operationType, Response response) {
        switch (operationType){
            case Insert:
                break;
            case Update:
                new CustomService<>(context).Get(GlobalVariable.userModel.getUserName());
                break;
            case Delete:
                new CustomService<>(context).Get(GlobalVariable.userModel.getUserName());
                break;
        }
    }

    @Override
    public void onFailure(CustomService.OperationType operationType) {
        switch (operationType){
            case Get:
                new Tool(context).move(TodoListDefinationActivity.class,true,false);
                break;
        }
    }

    @Override
    public void onFailure(TodoListService.OperationType operationType) {

    }

}
