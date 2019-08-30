package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.API.Services.TodoListService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoTasksModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools.SwipeToCompleteCallback;
import com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools.SwipeToDeleteCallback;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemTodolistBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AdapterRecyclerViewTodoList extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CustomTodoModel> models;
    private RecyclerViewItemTodolistBinding binding;

    public AdapterRecyclerViewTodoList(Context context, ArrayList<CustomTodoModel> models) {
        this.context = context;
        this.models = models;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder {
        public CustomTodoModel itemModel;

        public TasksViewHolder(@NonNull RecyclerViewItemTodolistBinding binding) {
            super(binding.getRoot());
        }

        public void setData(CustomTodoModel itemModel){
            this.itemModel = itemModel;

            binding.txtEditTodoListName.setText(itemModel.getTodoName());
            binding.txtEditTodoListCreatedDate.setText("Created Date: "+new ToolTimeExpressions().setDateFormat(itemModel.getTodoCreatedDate(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT, GlobalVariable.DateFormat.SHOW_FULL_FORMAT));

            AdapterRecyclerViewTodoListTasks adapterRecyclerViewTodoListTasks = new AdapterRecyclerViewTodoListTasks(context,itemModel.getCustomTodoTasksModels());
            binding.recyclerViewTodoListTasks.setAdapter(adapterRecyclerViewTodoListTasks);
            binding.recyclerViewTodoListTasks.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
            binding.recyclerViewTodoListTasks.setItemViewCacheSize(itemModel.getCustomTodoTasksModels().size());
            new ItemTouchHelper(new SwipeToDeleteCallback(context,true) {
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                    final int position = viewHolder.getAdapterPosition();
                    final CustomTodoTasksModel item = adapterRecyclerViewTodoListTasks.getModels().get(position);
                    final TodoListModel todoListModel = new TodoListModel(
                            item.getId(),
                            item.getTodoName(),
                            item.getUserName(),
                            item.getTaskName(),
                            item.getStatusName(),
                            item.getActive(),
                            item.getCreatedDate()
                    );
                    new TodoListService<>(context).Delete(todoListModel);
                    Snackbar snackbar = Snackbar.make(binding.rootRecyclerViewItemTodoList, item.getTaskName()+" was removed from the list.", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new TodoListService<>(context).Insert(todoListModel);
                        }
                    });
                    snackbar.setActionTextColor(context.getColor(R.color.white));
                    snackbar.show();
                }
            }).attachToRecyclerView(binding.recyclerViewTodoListTasks);
            new ItemTouchHelper(new SwipeToCompleteCallback(context,true) {
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                    final int position = viewHolder.getAdapterPosition();
                    final CustomTodoTasksModel item = adapterRecyclerViewTodoListTasks.getModels().get(position);
                    final TodoListModel todoListModel = new TodoListModel(
                            item.getId(),
                            item.getTodoName(),
                            item.getUserName(),
                            item.getTaskName(),
                            "Completed",
                            item.getActive(),
                            item.getCreatedDate()
                    );
                    new TodoListService<>(context).Update(todoListModel);
                    Snackbar snackbar = Snackbar.make(binding.rootRecyclerViewItemTodoList, item.getTaskName()+" was completed from the list.", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            todoListModel.setStatusName("Created");
                            new TodoListService<>(context).Update(todoListModel);
                        }
                    });
                    snackbar.setActionTextColor(context.getColor(R.color.white));
                    snackbar.show();
                }
            }).attachToRecyclerView(binding.recyclerViewTodoListTasks);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_todolist,parent,false);
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

    public ArrayList<CustomTodoModel> getModels(){
        return models;
    }
}
