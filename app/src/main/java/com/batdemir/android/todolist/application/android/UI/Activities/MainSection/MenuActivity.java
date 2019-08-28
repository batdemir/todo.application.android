package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.batdemir.android.todolist.application.android.Entity.UIModels.EventsItemModel;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TaskDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.DetailSection.TodoListDefinationActivity;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterFragmentPager;
import com.batdemir.android.todolist.application.android.UI.Adapters.AdapterRecyclerViewEvents;
import com.batdemir.android.todolist.application.android.UI.Fragments.EventsFragment;
import com.batdemir.android.todolist.application.android.UI.Fragments.TodoListFragment;
import com.batdemir.android.todolist.application.android.databinding.ActivityMenuBinding;

public class MenuActivity extends BaseActivity implements
        AdapterRecyclerViewEvents.EventsItemListener {

    private Context context;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(false,"Menu");
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_menu);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setListeners() {
        set_tabLayout();
    }

    //----functions----//

    private void set_tabLayout(){
        setupViewPager(binding.viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.setBackgroundColor(getColor(R.color.primaryLightColor));
        binding.tabLayout.setTabTextColors(getColor(R.color.white),getColor(R.color.white));
    }

    private void setupViewPager(ViewPager viewPager){
        AdapterFragmentPager adapterFragmentPager = new AdapterFragmentPager(getSupportFragmentManager());
        adapterFragmentPager.addFragment(new EventsFragment(),"Events");
        adapterFragmentPager.addFragment(new TodoListFragment(), "Todo Lists");
        adapterFragmentPager.notifyDataSetChanged();
        viewPager.setAdapter(adapterFragmentPager);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onItemClick(EventsItemModel eventsItemModel) {
        new Tool(context).move(eventsItemModel.getTo(),true,true);
    }
}
