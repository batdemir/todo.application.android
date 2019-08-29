package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoTasksModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemTodolistTasksBinding;

import java.util.ArrayList;

public class AdapterRecyclerViewTodoListTasks extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CustomTodoTasksModel> models;
    private RecyclerViewItemTodolistTasksBinding binding;

    public AdapterRecyclerViewTodoListTasks(Context context, ArrayList<CustomTodoTasksModel> models) {
        this.context = context;
        this.models = models;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder {
        public CustomTodoTasksModel itemModel;

        public TasksViewHolder(@NonNull RecyclerViewItemTodolistTasksBinding binding) {
            super(binding.getRoot());
        }

        public void setData(CustomTodoTasksModel itemModel) {
            this.itemModel = itemModel;

            binding.txtEditTask.setText(itemModel.getTaskName());
            binding.txtEditPriority.setText(itemModel.getPriorityName());
            binding.txtEditDeadLine.setText(new ToolTimeExpressions().setDateFormat(itemModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_FULL_FORMAT));
            binding.txtEditStatus.setText(itemModel.getStatusName());
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_todolist_tasks, parent, false);
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

    public ArrayList<CustomTodoTasksModel> getModels() {
        return models;
    }
}
