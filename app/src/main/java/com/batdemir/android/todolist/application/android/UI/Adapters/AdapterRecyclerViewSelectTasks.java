package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemSelectTasksBinding;

import java.util.ArrayList;

public class AdapterRecyclerViewSelectTasks extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<TaskModel> models;
    private RecyclerViewItemSelectTasksBinding binding;

    public AdapterRecyclerViewSelectTasks(Context context, ArrayList<TaskModel> models) {
        this.context = context;
        this.models = models;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnCheckedChangeListener {
        public TaskModel itemModel;

        public TasksViewHolder(@NonNull RecyclerViewItemSelectTasksBinding binding) {
            super(binding.getRoot());
            binding.checkBoxTask.setOnCheckedChangeListener(this);
        }

        public void setData(TaskModel itemModel) {
            this.itemModel = itemModel;

            String str = "\t" + itemModel.getName() + "\n\t" + context.getString(R.string.dead_line) + new ToolTimeExpressions().setDateFormat(itemModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_FULL_FORMAT);

            binding.checkBoxTask.setText(str);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Activity activity = (Activity) context;
            TasksItemListener eventsItemListener = (TasksItemListener) activity;
            eventsItemListener.onItemCheckedChanged(isChecked, itemModel);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_select_tasks, parent, false);
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

    public ArrayList<TaskModel> getModels() {
        return models;
    }

    public interface TasksItemListener {
        void onItemCheckedChanged(boolean isChecked, TaskModel taskModel);
    }
}
