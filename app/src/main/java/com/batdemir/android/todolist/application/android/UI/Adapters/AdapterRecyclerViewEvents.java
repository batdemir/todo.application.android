package com.batdemir.android.todolist.application.android.UI.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.Entity.UIModels.EventsItemModel;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemEventsBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewEvents extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<EventsItemModel> models;
    private RecyclerViewItemEventsBinding binding;

    public AdapterRecyclerViewEvents(Context context, ArrayList<EventsItemModel> models) {
        this.context = context;
        this.models = models;
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public EventsItemModel itemModel;

        public EventsViewHolder(@NonNull RecyclerViewItemEventsBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
        }

        public void setData(EventsItemModel itemModel){
            this.itemModel = itemModel;

            binding.imgEditIcon.setImageDrawable(itemModel.getIcon());
            binding.txtEditItemName.setText(itemModel.getName());
        }

        @Override
        public void onClick(View v) {
            Activity activity = (Activity) context;
            EventsItemListener eventsItemListener = (EventsItemListener) activity;
            eventsItemListener.onItemClick(itemModel);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item_events,parent,false);
        return new EventsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventsViewHolder viewHolder = (EventsViewHolder) holder;
        viewHolder.setData(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public interface EventsItemListener{
        void onItemClick(EventsItemModel eventsItemModel);
    }
}
