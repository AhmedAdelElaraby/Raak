package com.teraninja.raak;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.teraninja.raak.databinding.FragmentHomeBinding;
import com.teraninja.raak.ui.main.AdapterViewPagerHome;

import java.util.ArrayList;

public class Home extends Fragment {

FragmentHomeBinding binding;
    SharedPreferences preferences;
    String name="";
String getcse;

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        getcse=preferences.getString("case","Login as Visitor");
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        AdapterViewPagerHome pagerHome = new AdapterViewPagerHome(getChildFragmentManager());

        pagerHome.addFragment(MainHomePager.getInstance(), getString(R.string.Home));
        pagerHome.addFragment(RepirePager.getInstance(), getString(R.string.Repire));
        pagerHome.addFragment(RecustesPager.getInstance(), getString(R.string.Recusetes));
        binding.viewpager.setAdapter(pagerHome);
        binding.tabs.setupWithViewPager(binding.viewpager);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (binding.viewpager.getCurrentItem()==0){
//                    Toast.makeText(getContext(), "ahmed"+binding.viewpager.getCurrentItem(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.viewpager.getCurrentItem()==1){
//                    Toast.makeText(getContext(), "mohmed", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.viewpager.getCurrentItem()==2){
//                    Toast.makeText(getContext(), "Ali", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else{
//                    Toast.makeText(getContext(), "welcome", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        if (getcse.equals("Login as Visitor")){
                            binding.viewpager.setCurrentItem(0,false);
                            return;
                        }
                        break;
                    case 2:
                        if (getcse.equals("Login as Visitor")){
                            binding.viewpager.setCurrentItem(0,false);
                            return;
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
               // Toast.makeText(getContext(), ""+state, Toast.LENGTH_SHORT).show();
            }
        });
        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "yes", Toast.LENGTH_SHORT).show();


            }
        });
        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_notifications);
            }
        });
        return binding.getRoot();
    }
}