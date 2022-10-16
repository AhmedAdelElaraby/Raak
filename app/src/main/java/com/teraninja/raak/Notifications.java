package com.teraninja.raak;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.raak.Model.DataNotifications;
import com.teraninja.raak.databinding.FragmentNotificationsBinding;
import com.teraninja.raak.ui.main.AdapterNotification;
import com.teraninja.raak.ui.main.MoveViewModel;

public class Notifications extends Fragment {
FragmentNotificationsBinding binding;
 MoveViewModel model;
    SharedPreferences preferences;
    String token;

    public Notifications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_notifications, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        token = preferences.getString("token","no");
       model.Notifications("Bearer"+token);
        AdapterNotification notification = new AdapterNotification();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(notification);
        model.Notificationsget.observe(getViewLifecycleOwner(), new Observer<DataNotifications>() {
            @Override
            public void onChanged(DataNotifications dataNotifications) {
                Toast.makeText(getContext(), ""+dataNotifications.getMessage(), Toast.LENGTH_SHORT).show();

               notification.getList(dataNotifications.getData().getNotifications());
            }
        });
       return binding.getRoot();
    }
}