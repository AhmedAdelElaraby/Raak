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

import com.teraninja.raak.Model.DataChengePassword;
import com.teraninja.raak.Model.SendDataChengePassword;
import com.teraninja.raak.databinding.FragmentConfarmPasswordBinding;
import com.teraninja.raak.ui.main.MoveViewModel;


public class ConfarmPassword extends Fragment {
FragmentConfarmPasswordBinding binding;
MoveViewModel model;
String phone;
String code;
    public ConfarmPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_confarm_password, container, false);
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
       if (getArguments()!=null){
           ConfarmPasswordArgs args = ConfarmPasswordArgs.fromBundle(getArguments());
           phone=args.getPhone();
           code=args.getCode();
       }
    binding.Send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newpassword = binding.newpassword.getText().toString().trim();
            String confirmpassword= binding.confarmpassword.getText().toString().trim();
            if (newpassword.isEmpty()){
                binding.newpassword.setError(getString(R.string.passworderorr));
                return;
            }
            if (confirmpassword.isEmpty()){
                binding.confarmpassword.setError(getString(R.string.passworderorr));
                return;
            }
            if(!confirmpassword.equals(newpassword)){
                binding.confarmpassword.setError(getString(R.string.passworderorr));
                return;
            }
            else{
                SendDataChengePassword password = new SendDataChengePassword();
                password.setPhone(phone);
                password.setCode(code);
                password.setPassword(newpassword);
                password.setPassword_confirmation(confirmpassword);
                model.chengePassword(password);
            }
        }
    });
model.chengePassword.observe(getViewLifecycleOwner(), new Observer<DataChengePassword>() {
    @Override
    public void onChanged(DataChengePassword dataChengePassword) {
        if (dataChengePassword.getStatus()==200){
            Toast.makeText(getContext(), ""+dataChengePassword.getMessage(), Toast.LENGTH_SHORT).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_confarmPassword_to_login);
        }
        else {
            Toast.makeText(getContext(), ""+dataChengePassword.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), ""+dataChengePassword.getErrors(), Toast.LENGTH_SHORT).show();
        }
    }
});
       return binding.getRoot();

    }
}