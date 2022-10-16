package com.teraninja.raak;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.raak.Model.DataLogin;
import com.teraninja.raak.Model.SendDataLogin;
import com.teraninja.raak.databinding.FragmentLoginBinding;
import com.teraninja.raak.ui.main.MoveViewModel;


public class Login extends Fragment {
FragmentLoginBinding binding;
MoveViewModel model;
    SharedPreferences preferences;
    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false);
      model = ViewModelProviders.of(this).get(MoveViewModel.class);
      preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        binding.forgetpassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_to_forgetPasswordOne);
           }
       });

       //onclick login phone and password
    binding.loginone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           // get value from ui
            String numberphone=binding.numberphone.getText().toString().trim();
            String password=binding.password.getText().toString().trim();

            if (numberphone.isEmpty()){
                binding.numberphone.setError(getString(R.string.Erorrphone));
                return;
            }
            if (numberphone.length()<=10){
                binding.numberphone.setError(getString(R.string.erorrphone_length));
                return;
            }
            if (password.isEmpty()){
                binding.password.setError(getString(R.string.passworderorr));
                return;
            }
            if (!binding.yes.isChecked()){
                binding.yes.setError(getString(R.string.Agree));
                return;
            }
            else {
                SendDataLogin login = new SendDataLogin();
                login.setPhone(numberphone);
                login.setPassword(password);
                model.getlogin(login);
            }


        }
    });

    model.getDataLogin.observe(getViewLifecycleOwner(), new Observer<DataLogin>() {
        @Override
        public void onChanged(DataLogin dataLogin) {
            if (dataLogin.getStatus()==0){
                Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
                savedata("case","Login Phone and password");
                savedata("token",dataLogin.getData().getAccess_token());
                NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.login, true).build();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_to_home2,savedInstanceState,builder);


            }
            else {
                Toast.makeText(getContext(), ""+dataLogin.getErrors(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });

       return binding.getRoot();
    }
    public  void savedata(String key,String val){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,val);
        editor.commit();

    }
}