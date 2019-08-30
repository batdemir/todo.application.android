package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.batdemir.android.todolist.application.android.Entity.UIModels.EventsItemModel;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TaskDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TodoListDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewEvents;
import com.batdemir.android.todolist.application.android.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.UUID;


public class MenuActivity extends BaseActivity implements AdapterRecyclerViewEvents.EventsItemListener {

    private Context context;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(false,getString(R.string.menu));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_menu);
    }

    @Override
    public void loadData() {
        AdapterRecyclerViewEvents adapterRecyclerViewEvents = new AdapterRecyclerViewEvents(context,getModels());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
        binding.recyclerViewEvents.setAdapter(adapterRecyclerViewEvents);
        binding.recyclerViewEvents.setLayoutManager(gridLayoutManager);
        binding.recyclerViewEvents.setItemViewCacheSize(getModels().size());
    }

    @Override
    public void setListeners() {
    }

    //----functions----//

    @Override
    public void onItemClick(EventsItemModel eventsItemModel) {
        new Tool(context).move(eventsItemModel.getTo(),true,true);
    }

    private ArrayList<EventsItemModel> getModels(){
        ArrayList<EventsItemModel> models = new ArrayList<>();
        models.add(new EventsItemModel(UUID.randomUUID().toString(),getString(R.string.task_defination), TaskDefinationActivity.class,context.getDrawable(R.drawable.ic_task_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(),getString(R.string.todolist_defination), TodoListDefinationActivity.class,context.getDrawable(R.drawable.ic_todo_list_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(), getString(R.string.todolists), TodoListActivity.class,context.getDrawable(R.drawable.ic_todolist_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(),getString(R.string.settings), SettingsActivity.class,context.getDrawable(R.drawable.ic_settings_gears_mini)));
        models.add(new EventsItemModel(UUID.randomUUID().toString(),getString(R.string.logout), LogoutActivity.class,context.getDrawable(R.drawable.ic_exit_mini)));
        return models;
    }
}
