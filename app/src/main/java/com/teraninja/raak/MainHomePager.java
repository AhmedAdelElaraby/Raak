package com.teraninja.raak;

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
import android.widget.Toast;

import com.teraninja.raak.DataClinet.OnClickItemHome;
import com.teraninja.raak.Model.DataHome;
import com.teraninja.raak.Model.SendDataHome;
import com.teraninja.raak.Model.dataRecHome;
import com.teraninja.raak.databinding.FragmentMainHomePagerBinding;
import com.teraninja.raak.ui.main.AdapterHomePager;
import com.teraninja.raak.ui.main.MoveViewModel;

import java.util.ArrayList;


public class MainHomePager extends Fragment implements OnClickItemHome {
FragmentMainHomePagerBinding binding;
MoveViewModel model;
ArrayList<dataRecHome> list = new ArrayList<>();
;
  public static MainHomePager getInstance(){

      MainHomePager pager = new MainHomePager();
      return pager;
  }
    public MainHomePager() {

        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main_home_pager, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);

            binding.shimmerViewContainer.setVisibility(View.VISIBLE);
            SendDataHome home = new SendDataHome();
            model.Home(home);



        AdapterHomePager pager = new AdapterHomePager(this);
        binding.rechome.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rechome.setAdapter(pager);



    model.Home.observe(getViewLifecycleOwner(), new Observer<DataHome>() {
        @Override
        public void onChanged(DataHome dataHome) {
            pager.getList(dataHome.getData());
            binding.shimmerViewContainer.setVisibility(View.GONE);

        }
    });

        return binding.getRoot();
  }

    @Override
    public void getBuildingId(int id) {
      HomeDirections.ActionHome2ToShowDetilseHome home= HomeDirections.actionHome2ToShowDetilseHome();
      home.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(home);
    }
}