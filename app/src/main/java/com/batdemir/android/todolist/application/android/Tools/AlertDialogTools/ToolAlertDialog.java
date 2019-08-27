package com.batdemir.android.todolist.application.android.Tools.AlertDialogTools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.databinding.AlertDialogBinding;

public class ToolAlertDialog extends DialogFragment implements BaseActions {

    private AlertDialogBinding binding;
    private static final String key_message = "KEY_MESSAGE";
    private String message;

    public static ToolAlertDialog newInstance(String message, boolean isCancelable){
        ToolAlertDialog toolAlertDialog = new ToolAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putString(key_message,message);
        toolAlertDialog.setArguments(bundle);
        toolAlertDialog.setCancelable(isCancelable);
        return toolAlertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.alert_dialog,null,false);
        getObjectReferences();
        loadData();
        setListeners();
        return binding.getRoot();
    }

    @Override
    public void getObjectReferences() {
        assert getArguments() != null;
        message = getArguments().getString(key_message);
        new Tool(getContext()).animDialog(binding.linearProps);
    }

    @Override
    public void loadData() {
        binding.txtEditMessage.setText(message);
    }

    @Override
    public void setListeners() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCancel clickCancel = (ToolAlertDialog.clickCancel) getActivity();
                assert clickCancel != null;
                clickCancel.dialogCancel();
                dismiss();
            }
        });

        binding.btnOkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOkey clickOkey = (ToolAlertDialog.clickOkey) getActivity();
                assert clickOkey != null;
                clickOkey.dialogOkey();
                dismiss();
            }
        });
    }

    public interface clickOkey{
        void dialogOkey();
    }

    public interface clickCancel{
        void dialogCancel();
    }
}
