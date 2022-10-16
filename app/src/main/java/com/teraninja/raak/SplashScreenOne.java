package com.teraninja.raak;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.raak.databinding.FragmentSplashScreenOneBinding;


public class SplashScreenOne extends Fragment {
FragmentSplashScreenOneBinding binding;
SharedPreferences preferences;


    public SplashScreenOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen_one, container, false);
     //  create Object SharedPreferences
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        // on click text skip this here
        binding.Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata("case","skip");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashScreenOne_to_home2);
            }
        });

        binding.loginone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata("case","Login as Visitor");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashScreenOne_to_home2);
            }
        });




    binding.logintoo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashScreenOne_to_login);
        }
    });

        return binding.getRoot();

    }


    public  void savedata(String key,String val){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,val);
        editor.commit();

    }
}