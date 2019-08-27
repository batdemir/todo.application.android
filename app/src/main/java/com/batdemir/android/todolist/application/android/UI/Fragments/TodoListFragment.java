package com.batdemir.android.todolist.application.android.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.databinding.FragmentTodoListBinding;

public class TodoListFragment extends Fragment {

    FragmentTodoListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_todo_list,container,false);
        return binding.getRoot();
    }
}
