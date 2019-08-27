package com.batdemir.android.todolist.application.android.UI.Activities.DetailSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.databinding.ActivityTodoListDefinationBinding;

public class TodoListDefinationActivity extends BaseActivity {

    private Context context;
    private ActivityTodoListDefinationBinding binding;

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
    }

    @Override
    public void loadData() {
        Toast.makeText(context,"TodoList Defination",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setListeners() {

    }
}
