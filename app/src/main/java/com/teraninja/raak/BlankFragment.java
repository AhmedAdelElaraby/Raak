package com.teraninja.raak;

import static android.app.Activity.RESULT_OK;

import static com.teraninja.raak.ui.main.ImagePickerHelper.getPathFromUri;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.raak.Model.DataComplaints;
import com.teraninja.raak.Model.DataServeisREquste;
import com.teraninja.raak.Model.SendDataComiop;
import com.teraninja.raak.Model.SendDataServise;
import com.teraninja.raak.databinding.FragmentBlankBinding;
import com.teraninja.raak.ui.main.MoveViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class BlankFragment extends Fragment {
FragmentBlankBinding binding;
MoveViewModel model;
    String time;
    String number;
    String cases;
    int id;
    Dialog myDialogprossing;
    SharedPreferences preferences;
    String token;
    String l;
    Uri uri2;
    MultipartBody.Part part2 = null;
    ArrayList<MultipartBody.Part> images= new ArrayList<>();


    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_blank, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        token = preferences.getString("token","no");
        l= Locale.getDefault().getLanguage();

        if (getArguments()!=null){
            BlankFragmentArgs args =BlankFragmentArgs.fromBundle(getArguments());
              time=args.getTime();
              number=args.getNumber();
             cases=args.getCases();
             id=args.getId();
            Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
            binding.texttime.setText(""+time);
            binding.number.setText(""+number);
            binding.location.setText(""+cases);
        }
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        ImagePicker.Companion.with(BlankFragment.this).crop().start(1);


                    }
                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        // Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }
                };
                TedPermission.create()
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .check();
                // call();
            }
        });
        binding.loginone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=binding.text.getText().toString().trim();
                if (message.isEmpty()){
                    binding.text.setError(""+getString(R.string.description_of_the_damage));
                    return;
                }
                if (part2==null){
                    Toast.makeText(getContext(), ""+getString(R.string.upload_photo), Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    Toast.makeText(getContext(), ""+images.size(), Toast.LENGTH_SHORT).show();
                    ShowProssing();
                    SendDataComiop servise = new SendDataComiop();
                    servise.setMaintenance_service_id(String.valueOf(id));
                    servise.setMessage(message);
                    //Toast.makeText(getContext(), ""+servise.getDay_to(), Toast.LENGTH_SHORT).show();
                    servise.setImages(images);
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("maintenance_service_id",servise.getMaintenance_service_id())
                            .addFormDataPart("message",servise.getMessage())
                            .addPart(servise.getImages().get(0));
                    RequestBody requestBody = builder.build();
                    Log.i("ahmed",""+servise.getImages().get(0));
                model.complaints("Bearer"+token,"application/json",l,requestBody);


                }
            }
        });

        model.comino.observe(getViewLifecycleOwner(), new Observer<DataComplaints>() {
            @Override
            public void onChanged(DataComplaints dataServeisREquste) {
                if (dataServeisREquste.getStatus()==200){
                    myDialogprossing.dismiss();
                    secsesssend(""+dataServeisREquste.getMessage());
                }
                else {
                    Toast.makeText(getContext(), "" + dataServeisREquste.getMessage()+""+dataServeisREquste.getErrors(), Toast.LENGTH_SHORT).show();
                }
            }
        });



        return binding.getRoot();
    }

    public void ShowProssing () {
        myDialogprossing = new Dialog(getContext());
        myDialogprossing.setContentView(R.layout.item_diloge_prosessing);
        Window window = myDialogprossing.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        myDialogprossing.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogprossing.create();
        myDialogprossing.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK) {
                uri2 = data.getData();
                saveImagecamara();
                binding.image.setText(getString(R.string.selected_okay));
            }
        }else{

        }
//        switch (resultCode) {
//            case RESULT_OK:
//                switch (requestCode) {
//                    case 1:
//                        uri2=data.getData();
//                        saveImagecamara();
//                        binding.image.setText(getString(R.string.selected_okay));
//                        break;
//                    case 2:
//                        uri2=data.getData();
//                        saveImageGalrey();
//                        binding.image.setText(getString(R.string.selected_okay));
//                        break;
//
//
//
//                }
//                break;
//            case RESULT_CANCELED:
//                break;
//
//        }
    }
    private void saveImagecamara() {
        RequestBody fileReqBody = null;

        File file = new File(getPathFromUri(getContext(),uri2));
        // Create a request body with file and image media type
        fileReqBody = RequestBody.create( file, MediaType.parse("image/*"));
        // Create MultipartBody.Part using file request-body,file name and part name
        part2 = MultipartBody.Part.createFormData("images", file.getName(), fileReqBody);
        images.add(part2);
    }
    public void secsesssend (String text) {
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_diloge_send_seccses);
        TextView formtime=myDialog.findViewById(R.id.textmessage);
        formtime.setText(text+"\n"+getString(R.string.meassagecallthis) +getString(R.string.you));

        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }


}