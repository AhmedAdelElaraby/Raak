package com.teraninja.raak;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.raak.Model.DataLogOut;
import com.teraninja.raak.Model.DataProfile;
import com.teraninja.raak.Model.Datagetnumber;
import com.teraninja.raak.databinding.FragmentSetaingBinding;
import com.teraninja.raak.ui.main.MoveViewModel;


public class Setaing extends Fragment {
FragmentSetaingBinding binding;
    MoveViewModel model;
    SharedPreferences preferences;
    String token;
    public Setaing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_setaing, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        token = preferences.getString("token","no");
        model.Profile("Bearer"+token);
        model.Profile.observe(getViewLifecycleOwner(), new Observer<DataProfile>() {
            @Override
            public void onChanged(DataProfile dataProfile) {
                Picasso.get().load(dataProfile.getData().getUser().getImage()).into(binding.image);
                binding.name.setText(dataProfile.getData().getUser().getName());
                binding.nimberphone.setText(dataProfile.getData().getUser().getPhone());
            }
        });
        binding.LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.logout("Bearer"+token);

            }
        });
        model.logouttt.observe(getViewLifecycleOwner(), new Observer<DataLogOut>() {
            @Override
            public void onChanged(DataLogOut dataLogOut) {
                Toast.makeText(getContext(), ""+dataLogOut.getMessage(), Toast.LENGTH_SHORT).show();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_setaing_to_splashScreenOne);
            }
        });
        binding.Shereapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.teraninja.raak");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        binding.RateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.teraninja.raak"));
                startActivity(intent);
            }
        });
            binding.conteactus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Resalte();

                }
            });


       return binding.getRoot();
    }

    public void Resalte () {
        Dialog myDialog;

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.itemdilogenumberphone);
        ImageView imageView;
        imageView=myDialog.findViewById(R.id.call);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getnumbers("application/json","ar");
            }
        });
        model.getnumber.observe(getViewLifecycleOwner(), new Observer<Datagetnumber>() {
            @Override
            public void onChanged(Datagetnumber datagetnumber) {
                call(""+datagetnumber.getData().getPhone());
                Toast.makeText(getContext(), ""+datagetnumber.getData().getPhone(), Toast.LENGTH_SHORT).show();
            }
        });

        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
    public void call (String number) {
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_call);
        TextView textView = myDialog.findViewById(R.id.descrption);
        textView.setText(getString(R.string.are_you_sure_you_want_to_call)+" "+number+" ? ");
        TextView yes=myDialog.findViewById(R.id.yesone);
        TextView no=myDialog.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
}