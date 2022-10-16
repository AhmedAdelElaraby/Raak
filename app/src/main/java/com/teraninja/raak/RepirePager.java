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
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.raak.DataClinet.OnClickgetitemRepire;
import com.teraninja.raak.Model.DataMaintence;
import com.teraninja.raak.Model.DataProfile;
import com.teraninja.raak.Model.DataRepire;
import com.teraninja.raak.databinding.FragmentRepirePagerBinding;
import com.teraninja.raak.ui.main.AdapterMaintencene;
import com.teraninja.raak.ui.main.MoveViewModel;

import java.util.ArrayList;


public class RepirePager extends Fragment implements OnClickgetitemRepire {
FragmentRepirePagerBinding binding;
MoveViewModel model;
    SharedPreferences preferences;
    String getcse;
    String token;
    ArrayList<ArrayList<DataRepire>> list11 = new ArrayList<ArrayList<DataRepire>>();
    public static RepirePager getInstance(){
        RepirePager pager = new RepirePager();
        return pager;
    }

    public RepirePager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_repire_pager, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        getcse=preferences.getString("case","Login as Visitor");
        if (getcse.equals("Login as Visitor")){

        }else{
            token = preferences.getString("token","no");
            binding.shimmerViewContainer.setVisibility(View.VISIBLE);
            model.Profile("Bearer"+token);
            model.maintence("Bearer"+token);

        }




        AdapterMaintencene maintencene = new AdapterMaintencene(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(maintencene);
       model.Profile.observe(getViewLifecycleOwner(), new Observer<DataProfile>() {
           @Override
           public void onChanged(DataProfile dataProfile) {
               Picasso.get().load(dataProfile.getData().getUser().getImage()).into(binding.image);
               binding.name.setText(dataProfile.getData().getUser().getName());
               binding.nimberphone.setText(dataProfile.getData().getUser().getPhone());
           }
       });
    model.maintence1.observe(getViewLifecycleOwner(), new Observer<DataMaintence>() {
        @Override
        public void onChanged(DataMaintence dataMaintence) {
            maintencene.getList(dataMaintence.getData());
            list11.add(dataMaintence.getData());
            binding.shimmerViewContainer.setVisibility(View.GONE);
        }
    });

    binding.seting.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_setaing);
        }
    });
       return binding.getRoot();
    }

    @Override
    public void getData(int id) {
    int i= list11.get(id).get(id).getId();
    String location= list11.get(id).get(id).getAddress();
    String untinumber=list11.get(id).get(id).getResidential_unit_number();
    String start=list11.get(id).get(id).getMaintenance_contract().get(0).getStart_date();
    String end=list11.get(id).get(id).getMaintenance_contract().get(0).getEnd_date();
    String userid=String.valueOf(list11.get(id).get(id).getUser_id());
    Toast.makeText(getContext(), ""+i, Toast.LENGTH_SHORT).show();

    HomeDirections.ActionHome2ToShowOllDetilseRepire repire =HomeDirections.actionHome2ToShowOllDetilseRepire();
    repire.setUnit(untinumber);
    repire.setId(String.valueOf(i));
    repire.setLocation(location);
    repire.setStart(start);
  repire.setEnd(end);
  repire.setUserid(userid);
    Navigation.findNavController(binding.getRoot()).navigate(repire);

    }
}