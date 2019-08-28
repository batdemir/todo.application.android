package com.batdemir.android.todolist.application.android.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.batdemir.android.todolist.application.android.Entity.UIModels.EventsItemModel;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.ExitActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.SettingsActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TaskDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TodoListDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewEvents;
import com.batdemir.android.todolist.application.android.databinding.FragmentEventsBinding;
import com.batdemir.android.todolist.application.android.databinding.RecyclerViewItemEventsBinding;

import java.util.ArrayList;
import java.util.UUID;

public class EventsFragment extends Fragment implements BaseActions{

    private FragmentEventsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_events,container,false);
        getObjectReferences();
        loadData();
        setListeners();
        return binding.getRoot();
    }

    @Override
    public void getObjectReferences() {

    }

    @Override
    public void loadData() {
        AdapterRecyclerViewEvents adapterRecyclerViewEvents = new AdapterRecyclerViewEvents(getContext(),getModels());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        binding.recyclerViewEvents.setAdapter(adapterRecyclerViewEvents);
        binding.recyclerViewEvents.setLayoutManager(gridLayoutManager);
        binding.recyclerViewEvents.setItemViewCacheSize(getModels().size());
    }

    @Override
    public void setListeners() {

    }

    //----functions----//

    private ArrayList<EventsItemModel> getModels(){
        ArrayList<EventsItemModel> models = new ArrayList<>();
        models.add(new EventsItemModel(UUID.randomUUID().toString(),"Task Defination", TaskDefinationActivity.class,getContext().getDrawable(R.drawable.ic_task_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(),"Todo List Defination", TodoListDefinationActivity.class,getContext().getDrawable(R.drawable.ic_todo_list_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(),"Settings", SettingsActivity.class,getContext().getDrawable(R.drawable.ic_settings_gears_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(),"Exit", ExitActivity.class,getContext().getDrawable(R.drawable.ic_exit_mini)));
        return models;
    }
}
