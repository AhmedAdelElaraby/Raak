package com.teraninja.raak;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.raak.Model.DataDetilseHome;
import com.teraninja.raak.Model.DataHome;
import com.teraninja.raak.Model.Datagetnumber;
import com.teraninja.raak.Model.ModelData;
import com.teraninja.raak.Model.SendDataOfeer;
import com.teraninja.raak.databinding.FragmentShowDetilseHomeBinding;
import com.teraninja.raak.ui.main.MoveViewModel;


public class ShowDetilseHome extends Fragment {
FragmentShowDetilseHomeBinding binding;
int id;
MoveViewModel model;
    public ShowDetilseHome() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_detilse_home, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);

       if (getArguments()!=null){
           ShowDetilseHomeArgs args=ShowDetilseHomeArgs.fromBundle(getArguments());
            id=args.getId();
           Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
           model.getDetailse(id);

       }
    model.getDitalse.observe(getViewLifecycleOwner(), new Observer<DataDetilseHome>() {
        @Override
        public void onChanged(DataDetilseHome dataHome) {
            Picasso.get().load(dataHome.getData().getImage()).fit().into(binding.image);
            binding.detilseDescrption.setText(""+dataHome.getData().getBuilding_type().getDescription());
            binding.location.setText(""+dataHome.getData().getAddress());
            binding.textsize.setText(""+dataHome.getData().getSize());
            binding.textroomtoulte.setText(""+dataHome.getData().getBath_rooms());
            binding.room.setText(""+dataHome.getData().getBed_rooms());
            binding.textwoter.setText(""+dataHome.getData().getSwimming_pool());
            binding.wifi.setText(""+dataHome.getData().getCar_parking());

            Toast.makeText(getContext(), ""+dataHome.getData().getArea().getName(), Toast.LENGTH_SHORT).show();
        }
    });




    binding.loginone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showAddressesPopup ();

        }
    });
       return binding.getRoot();
    }
    public void showAddressesPopup () {
        Dialog myDialog;

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.itemdaloge);
        EditText name;
        EditText number;
        EditText message;
        TextView send;
            name=myDialog.findViewById(R.id.name);
            number=myDialog.findViewById(R.id.number);
            message=myDialog.findViewById(R.id.message);
            send=myDialog.findViewById(R.id.send);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String getname=name.getText().toString().trim();
                    String getnumber=number.getText().toString().trim();
                    String getmessage=message.getText().toString().trim();
                    if (getname.isEmpty()){
                        name.setError("Please enter your name");
                        return;
                    }
                    if (getnumber.isEmpty()){
                        number.setError(getString(R.string.Erorrphone));
                        return;
                    }
                    if (getmessage.isEmpty()){
                        message.setError(getString(R.string.message));
                        return;
                    }
                    if (getnumber.length()<=10){
                        number.setError(getString(R.string.erorrphone_length));
                        return;
                    }
                    else{
                        SendDataOfeer dataOfeer = new SendDataOfeer();
                        dataOfeer.setMessage(getmessage);
                        dataOfeer.setName(getname);
                        dataOfeer.setPhone(getnumber);
                    model.getOffer(dataOfeer);
                    }

                }
            });
        model.getoffer.observe(getViewLifecycleOwner(), new Observer<ModelData>() {
            @Override
            public void onChanged(ModelData modelData) {
                Toast.makeText(getContext(), ""+modelData.getMessage(), Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
                Resalte();
            }
        });


        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
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