package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoTasksModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemTodolistV2Binding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdapterRecyclerViewTodoV2 extends RecyclerView.Adapter implements
        Filterable {

    private Context context;
    private ArrayList<CustomTodoTasksModel> models;
    private ArrayList<CustomTodoTasksModel> filteredModels;
    private RecyclerViewItemTodolistV2Binding binding;

    public AdapterRecyclerViewTodoV2(Context context, ArrayList<CustomTodoTasksModel> models) {
        this.context = context;
        this.models = models;
        filteredModels = models;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder {
        public CustomTodoTasksModel itemModel;

        public TasksViewHolder(@NonNull RecyclerViewItemTodolistV2Binding binding) {
            super(binding.getRoot());
        }

        public void setData(CustomTodoTasksModel itemModel) {
            this.itemModel = itemModel;

            binding.txtEditTodoList.setText(itemModel.getTodoName());
            binding.txtEditCreatedDate.setText(new ToolTimeExpressions().setDateFormat(itemModel.getCreatedDate(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_FULL_FORMAT));
            binding.txtEditTask.setText(itemModel.getTaskName());
            binding.txtEditPriority.setText(itemModel.getPriorityName());
            binding.txtEditDeadLine.setText(new ToolTimeExpressions().setDateFormat(itemModel.getDeadLine(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_FULL_FORMAT));
            binding.txtEditStatus.setText(itemModel.getStatusName());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_todolist_v2, parent, false);
        return new TasksViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TasksViewHolder viewHolder = (TasksViewHolder) holder;
        viewHolder.setData(filteredModels.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredModels.size();
    }

    public ArrayList<CustomTodoTasksModel> getModels() {
        return filteredModels;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    results.values = models;
                    results.count = models.size();
                } else {
                    ArrayList<CustomTodoTasksModel> filterResultsData = new ArrayList<>();
                    for (CustomTodoTasksModel data : models) {
                        if (data.getTaskName().toString().contains(constraint)) {
                            filterResultsData.add(data);
                        }
                    }

                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredModels = (ArrayList<CustomTodoTasksModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}