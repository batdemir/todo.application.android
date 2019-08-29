package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemTasksBinding;

import java.util.ArrayList;

public class AdapterRecyclerViewTasks extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<TaskModel> models;
    private RecyclerViewItemTasksBinding binding;

    public AdapterRecyclerViewTasks(Context context, ArrayList<TaskModel> models) {
        this.context = context;
        this.models = models;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TaskModel itemModel;

        public TasksViewHolder(@NonNull RecyclerViewItemTasksBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
        }

        public void setData(TaskModel itemModel){
            this.itemModel = itemModel;

            binding.txtEditItemName.setText(itemModel.getName());
            binding.txtEditDeadLineCalender.setText(new ToolTimeExpressions().setDateFormat(itemModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_DATE_FORMAT));
            binding.txtEditDeadLineClock.setText(new ToolTimeExpressions().setDateFormat(itemModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SMALL_TIME_FORMAT));
            binding.txtEditPriort.setText(itemModel.getPriorityName());
        }

        @Override
        public void onClick(View v) {
            Activity activity = (Activity) context;
            TasksItemListener eventsItemListener = (TasksItemListener) activity;
            eventsItemListener.onItemClick(itemModel);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_tasks,parent,false);
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

    public ArrayList<TaskModel> getModels(){
        return models;
    }

    public interface TasksItemListener{
        void onItemClick(TaskModel taskModel);
    }
}
