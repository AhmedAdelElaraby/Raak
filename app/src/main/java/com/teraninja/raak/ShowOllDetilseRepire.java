package com.teraninja.raak;

import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.raak.Model.DataNewSebiscribe;
import com.teraninja.raak.Model.DataNewbin;
import com.teraninja.raak.Model.Datagetnumber;
import com.teraninja.raak.Model.SendDatanew;
import com.teraninja.raak.databinding.FragmentShowOllDetilseRepireBinding;
import com.teraninja.raak.ui.main.MoveViewModel;

import java.util.ArrayList;
import java.util.Locale;


public class ShowOllDetilseRepire extends Fragment {
FragmentShowOllDetilseRepireBinding binding;
MoveViewModel model;
   String unit;
   String location;
    SharedPreferences preferences;
    String token;
   String start;
   String end;
   String id;
   String ie;
   String userid;
   int pos=0;
   String l;
    public ShowOllDetilseRepire() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_oll_detilse_repire, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        token = preferences.getString("token","no");
        l= Locale.getDefault().getLanguage();
       if (getArguments()!=null){
           ShowOllDetilseRepireArgs args = ShowOllDetilseRepireArgs.fromBundle(getArguments());
           unit= args.getUnit();
           location= args.getLocation();
           id=args.getId();
           userid=args.getUserid();
           start= args.getStart();
           end= args.getEnd();
           binding.number.setText(""+unit);
           binding.location.setText(""+location);
           binding.ContractBeginning.setText(""+start);
           binding.ContractEnding.setText(""+end);
       }
    binding.cont.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pos=1;
            binding.l1.setBackground(getResources().getDrawable(R.drawable.item_backgruond_maintenance));
            binding.l2.setBackground(null);

        }
    });
       binding.cont2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               pos=2;
               binding.l2.setBackground(getResources().getDrawable(R.drawable.item_backgruond_maintenance));
               binding.l1.setBackground(null);

           }
       });
       binding.loginone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (pos==0){
                   Toast.makeText(getContext(), "Please chose ", Toast.LENGTH_SHORT).show();
                   return;

               }
               if (pos==1){
                    Resalte();
                   return;
               }
               if (pos==2){
                   ShowOllDetilseRepireDirections.ActionShowOllDetilseRepireToCreateRepire repire=ShowOllDetilseRepireDirections.actionShowOllDetilseRepireToCreateRepire();
                   repire.setNuitnumber(unit);
                   repire.setLocation(location);
                   repire.setId(id);
                   repire.setUserid(userid);

                   Navigation.findNavController(binding.getRoot()).navigate(repire);
                   return;
               }else{

               }
           }
       });
       return binding.getRoot();
    }

    public void Resalte () {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> listid = new ArrayList<>();

        model.sbiscriber("application/json",l);
        Dialog myDialog;

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item);
        Spinner spinner=myDialog.findViewById(R.id.spinner);
        TextView textView= myDialog.findViewById(R.id.textnumber);
        TextView send= myDialog.findViewById(R.id.send);

        textView.setText(unit);
        model.newSebiscribe.observe(getViewLifecycleOwner(), new Observer<DataNewSebiscribe>() {
            @Override
            public void onChanged(DataNewSebiscribe dataNewSebiscribe) {
                list.clear();
                listid.clear();
                for (int i=0; i<dataNewSebiscribe.getData().size();i++){
                    list.add(dataNewSebiscribe.getData().get(i).getName());
                    listid.add(dataNewSebiscribe.getData().get(i).getDuration());
                }
                ArrayAdapter<String> selectedExprince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,list);
                spinner.setAdapter(selectedExprince);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 ie=String.valueOf(listid.get(position));
                Toast.makeText(getContext(), ""+ie, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (ie.isEmpty()){
               return;
              }
              if (id.isEmpty()){
                return;
              }else{
                  SendDatanew datanew = new SendDatanew();
                  datanew.setBuilding_id(id);
                  datanew.setContract_duration_id(ie);
                  model.newbin("Bearer"+token,datanew);
              }
            }
        });
        model.dataNewbin.observe(getViewLifecycleOwner(), new Observer<DataNewbin>() {
            @Override
            public void onChanged(DataNewbin dataNewbin) {

                if (dataNewbin.getStatus()==200){
                    Toast.makeText(getContext(), ""+dataNewbin.getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog.dismiss();
                    dataNewbin.setStatus(20000);
                    return;
                }
                if (dataNewbin.getStatus()==20000){

                }
                else {
                    Toast.makeText(getContext(), ""+dataNewbin.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
}