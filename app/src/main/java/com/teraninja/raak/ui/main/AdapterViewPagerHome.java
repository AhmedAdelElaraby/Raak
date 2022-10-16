package com.teraninja.raak.ui.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterViewPagerHome extends FragmentPagerAdapter {

ArrayList<String> list = new ArrayList<>();
ArrayList<Fragment> listfragment = new ArrayList<>();

    public ArrayList<String> getList() {
        return list;
    }

    public ArrayList<Fragment> getListfragment() {
        return listfragment;
    }

    public AdapterViewPagerHome(@NonNull FragmentManager fm) {

        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

                return listfragment.get(position);



    }
    @NonNull
    @Override
    public CharSequence getPageTitle(int position){

     return list.get(position);

}
    @Override
    public int getCount() {
        return listfragment.size();
    }

    public void addFragment(Fragment fragment,String titile){
        list.add(titile);
        listfragment.add(fragment);
        notifyDataSetChanged();
    }
}
