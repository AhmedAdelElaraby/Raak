package com.teraninja.raak;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.raak.DataClinet.RetrofitAPI;
import com.teraninja.raak.Model.DataRequsetcode;
import com.teraninja.raak.Model.SendDataCode;
import com.teraninja.raak.databinding.FragmentForgetPasswordOneBinding;
import com.teraninja.raak.ui.main.MoveViewModel;

import io.reactivex.Observable;


public class ForgetPasswordOne extends Fragment {
FragmentForgetPasswordOneBinding binding;
MoveViewModel model;
    String phone;
    public ForgetPasswordOne() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_forget_password_one, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
        binding.loginone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              phone= binding.numberphone.getText().toString().trim();


              if (phone.isEmpty()){
                  binding.numberphone.setError(getString(R.string.Erorrphone));
                    return;
              }
              if (phone.length()<=10){
                  binding.numberphone.setError(getString(R.string.erorrphone_length));

              }
              else {
                  SendDataCode code = new SendDataCode();
                  code.setPhone(phone);
                  model.getcode("application/json",code);
                  binding.progress.setVisibility(View.VISIBLE);
              }

            }
        });
    model.Sendphone.observe(getViewLifecycleOwner(), new Observer<DataRequsetcode>() {
        @Override
        public void onChanged(DataRequsetcode dataRequsetcode) {
            if (dataRequsetcode.getStatus()==200){
                Toast.makeText(getContext(), ""+dataRequsetcode.getMessage(), Toast.LENGTH_SHORT).show();
                ForgetPasswordOneDirections.ActionForgetPasswordOneToForgetPasswordByCode one=ForgetPasswordOneDirections.actionForgetPasswordOneToForgetPasswordByCode();
                one.setPhone(phone);
                one.setCode(String.valueOf(dataRequsetcode.getData().getCode()));
                Navigation.findNavController(binding.getRoot()).navigate(one);


            }else{
                Toast.makeText(getContext(), ""+dataRequsetcode.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), ""+dataRequsetcode.getErrors(), Toast.LENGTH_SHORT).show();
            }

        }
    });
       return binding.getRoot();
    }
}