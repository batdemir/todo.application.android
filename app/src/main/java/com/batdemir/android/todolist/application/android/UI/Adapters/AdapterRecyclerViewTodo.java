package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoModel;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemTodolistBinding;

import java.util.ArrayList;

public class AdapterRecyclerViewTodo extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<TodoModel> models;
    private RecyclerViewItemTodolistBinding binding;

    public AdapterRecyclerViewTodo(Context context, ArrayList<TodoModel> models) {
        this.context = context;
        this.models = models;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TodoModel itemModel;

        public TasksViewHolder(@NonNull RecyclerViewItemTodolistBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
        }

        public void setData(TodoModel itemModel) {
            this.itemModel = itemModel;

            binding.txtEditTodoName.setText(itemModel.getName());
        }

        @Override
        public void onClick(View v) {
            Activity activity = (Activity) context;
            TodoItemListener eventsItemListener = (TodoItemListener) activity;
            eventsItemListener.onItemClick(itemModel);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_todolist, parent, false);
        return new TasksViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TasksViewHolder viewHolder = (TasksViewHolder) holder;
        viewHolder.setData(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public ArrayList<TodoModel> getModels() {
        return models;
    }

    public interface TodoItemListener {
        void onItemClick(TodoModel todoModel);
    }
}
