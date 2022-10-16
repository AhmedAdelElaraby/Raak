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

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.teraninja.raak.Model.DataVerifyPhone;
import com.teraninja.raak.Model.SendDataVerifyPhone;
import com.teraninja.raak.databinding.FragmentForgetPasswordByCodeBinding;
import com.teraninja.raak.ui.main.MoveViewModel;

public class ForgetPasswordByCode extends Fragment {
FragmentForgetPasswordByCodeBinding binding;
String phone;
String code;
MoveViewModel model;
    public ForgetPasswordByCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_forget_password_by_code, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
      if (getArguments()!=null){
          ForgetPasswordByCodeArgs args = ForgetPasswordByCodeArgs.fromBundle(getArguments());
         code= args.getCode();
         phone= args.getPhone();
          Toast.makeText(getContext(), ""+code, Toast.LENGTH_SHORT).show();
      }
        binding.txtPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {

                if (str.toString().equals(code)) {
                    SendDataVerifyPhone phoneopp = new SendDataVerifyPhone();
                    phoneopp.setPhone(phone);
                    phoneopp.setCode(code);
                    model.VerifyPhone(phoneopp);
                   // Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();


                } else {
                    SendDataVerifyPhone phoneopp = new SendDataVerifyPhone();
                    phoneopp.setPhone(phone);
                    phoneopp.setCode("4654");
                    model.VerifyPhone(phoneopp);
                   // Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                    binding.txtPinEntry.setText(null);
                }
            }
        });
    model.VerifyPhone.observe(getViewLifecycleOwner(), new Observer<DataVerifyPhone>() {
        @Override
        public void onChanged(DataVerifyPhone dataVerifyPhone) {
        if (dataVerifyPhone.getStatus()==200){
            Toast.makeText(getContext(), ""+dataVerifyPhone.getMessage(), Toast.LENGTH_SHORT).show();
            ForgetPasswordByCodeDirections.ActionForgetPasswordByCodeToConfarmPassword byCodeDirections = ForgetPasswordByCodeDirections.actionForgetPasswordByCodeToConfarmPassword();
           byCodeDirections.setPhone(phone);
           byCodeDirections.setCode(code);
            Navigation.findNavController(binding.getRoot()).navigate(byCodeDirections);
           // dataVerifyPhone.setStatus(500);

        }else{
            Toast.makeText(getContext(), ""+dataVerifyPhone.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), ""+dataVerifyPhone.getErrors(), Toast.LENGTH_SHORT).show();

        }


        }
    });



        return binding.getRoot();

    }
}