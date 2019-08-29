package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.batdemir.android.todolist.application.android.API.Services.CustomService;
import com.batdemir.android.todolist.application.android.API.Services.TodoListService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewTodoList;
import com.batdemir.android.todolist.application.android.databinding.ActivityTodoListBinding;

import java.util.ArrayList;

import retrofit2.Response;

public class TodoListActivity extends BaseActivity implements
        CustomService.CustomServiceListener {

    private Context context;
    private ActivityTodoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true,"Menu");
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
    public void onFailure() {

    }


}
