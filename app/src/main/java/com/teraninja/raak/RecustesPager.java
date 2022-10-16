package com.teraninja.raak;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.raak.DataClinet.OnClickRequste;
import com.teraninja.raak.Model.DataOrder;
import com.teraninja.raak.Model.DataServesOrder;
import com.teraninja.raak.Model.dataRecHome;
import com.teraninja.raak.databinding.FragmentRecustesPagerBinding;
import com.teraninja.raak.ui.main.AdapterHomePager;
import com.teraninja.raak.ui.main.AdapterOrders;
import com.teraninja.raak.ui.main.MoveViewModel;

import java.util.ArrayList;


public class RecustesPager extends Fragment implements OnClickRequste {
FragmentRecustesPagerBinding binding;
  MoveViewModel model;
    SharedPreferences preferences;
    String getcse;
    String token;
    public static RecustesPager getInstance(){
        RecustesPager pager = new RecustesPager();
        return pager;
    }
    public RecustesPager() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_recustes_pager, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        getcse=preferences.getString("case","Login as Visitor");
        if (getcse.equals("Login as Visitor")){

        }else{
            token = preferences.getString("token","no");
            model.getOrders("Bearer"+token);
        }

        AdapterOrders pager = new AdapterOrders(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(pager);
            model.getOrder.observe(getViewLifecycleOwner(), new Observer<DataServesOrder>() {
                @Override
                public void onChanged(DataServesOrder dataServesOrder) {
                pager.getList(dataServesOrder.getData());
                }
            });



       return binding.getRoot();
    }

    @Override
    public void getItem(String time,String number,String cases,int id) {
        HomeDirections.ActionHome2ToBlankFragment toBlankFragment=HomeDirections.actionHome2ToBlankFragment();
        toBlankFragment.setTime(time);
        toBlankFragment.setNumber(number);
        toBlankFragment.setCases(cases);
        toBlankFragment.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(toBlankFragment);
    }
}