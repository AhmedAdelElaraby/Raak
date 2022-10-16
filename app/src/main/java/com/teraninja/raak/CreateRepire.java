package com.teraninja.raak;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static com.teraninja.raak.ui.main.ImagePickerHelper.getPathFromUri;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.raak.DataClinet.OnClick_Id;
import com.teraninja.raak.Model.DataDamage;
import com.teraninja.raak.Model.DataDamageLocation;
import com.teraninja.raak.Model.DataServeisREquste;
import com.teraninja.raak.Model.SendDataServise;
import com.teraninja.raak.databinding.FragmentCreateRepireBinding;
import com.teraninja.raak.ui.main.AdapterDamage;
import com.teraninja.raak.ui.main.MoveViewModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class CreateRepire extends Fragment implements OnClick_Id {
FragmentCreateRepireBinding binding;
  String unitnumber;
  String location;
  String id;
  int Id;
    Dialog myDialogprossing;
String idtype ="";
  Uri uri2;
    DatePickerDialog setListenerone;
    DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog pickerDialog;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    DatePickerDialog setListenerone2;
    DatePickerDialog.OnDateSetListener setListener2;
    TimePickerDialog pickerDialog2;
    TimePickerDialog.OnTimeSetListener timeSetListener2;
    String Startdata ="";
    String starttime=" ";
    String enddate="";
    String Endtime="";
    MultipartBody.Part part2 = null;
    ArrayList<MultipartBody.Part> images= new ArrayList<>();
  MoveViewModel model;
  String l;
  String userid;
    SharedPreferences preferences;
    String token;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar cal= Calendar.getInstance();

    ArrayList<String> DamageLocationname= new ArrayList<>();
  ArrayList<Integer> DamageLocationId = new ArrayList<>();
    public CreateRepire() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_create_repire, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        token = preferences.getString("token","no");
        if (getArguments()!=null){
            CreateRepireArgs args =CreateRepireArgs.fromBundle(getArguments());
            unitnumber=args.getNuitnumber();
            location=args.getLocation();
            id=args.getId();
            userid=args.getUserid();
            binding.number.setText(""+unitnumber);
            binding.location.setText(""+location);
            model.DamageType("application/json",l);
            model.Damagelocation("application/json",l);
        }
        AdapterDamage damage= new AdapterDamage(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.rec.setAdapter(damage);
        model.dataDamage.observe(getViewLifecycleOwner(), new Observer<DataDamage>() {
        @Override
        public void onChanged(DataDamage dataDamage) {
            damage.getList(dataDamage.getData());
        }
    });
        model.dataDamageLocation.observe(getViewLifecycleOwner(), new Observer<DataDamageLocation>() {
            @Override
            public void onChanged(DataDamageLocation dataDamageLocation) {
                DamageLocationname.clear();
                DamageLocationId.clear();
                DamageLocationname.add(getString(R.string.text_chose_location_damage));
                DamageLocationId.add(0);
                for (int i=0;i<dataDamageLocation.getData().size();i++){
                    DamageLocationname.add(dataDamageLocation.getData().get(i).getName());
                    DamageLocationId.add(dataDamageLocation.getData().get(i).getId());

                }
                ArrayAdapter<String> selectedExprince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,DamageLocationname);
                binding.spinner.setAdapter(selectedExprince);
            }
        });
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              Id=DamageLocationId.get(i);
                Toast.makeText(getContext(), ""+Id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        ImagePicker.Companion.with(CreateRepire.this).crop().start(1);


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
                }
                if (Startdata.isEmpty()){
                    Toast.makeText(getContext(), ""+getString(R.string.select_date), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (starttime.isEmpty()){
                    Toast.makeText(getContext(), ""+getString(R.string.select_date), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (enddate.isEmpty()){
                    Toast.makeText(getContext(), ""+getString(R.string.select_date), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Endtime.isEmpty()){
                    Toast.makeText(getContext(), ""+getString(R.string.select_date), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (idtype.isEmpty()){
                    Toast.makeText(getContext(), ""+getString(R.string.chose_damage_type), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Id==0){
                    Toast.makeText(getContext(), ""+getString(R.string.text_chose_location_damage), Toast.LENGTH_SHORT).show();
                        return;
                 }else{
                    Toast.makeText(getContext(), ""+images.size(), Toast.LENGTH_SHORT).show();
                    ShowProssing();
                    SendDataServise servise = new SendDataServise();
                    servise.setBuilding_id(id);
                    servise.setUser_id(userid);
                    servise.setDamage_location_id(String.valueOf(Id));
                    servise.setDamage_type_id(idtype);
                    servise.setMessage(message);
                    servise.setDay_from(Startdata+" "+starttime);
                   // Toast.makeText(getContext(), ""+servise.getDay_from(), Toast.LENGTH_SHORT).show();
                    servise.setDay_to(enddate+" "+Endtime);
                    //Toast.makeText(getContext(), ""+servise.getDay_to(), Toast.LENGTH_SHORT).show();
                    servise.setImages(images);
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("building_id",servise.getBuilding_id())
                            .addFormDataPart("user_id", servise.getUser_id())
                            .addFormDataPart("damage_location_id", servise.getDamage_location_id())
                            .addFormDataPart("damage_type_id", servise.getDamage_type_id())
                            .addFormDataPart("message", servise.getMessage())
                            .addFormDataPart("day_from", servise.getDay_from())
                            .addFormDataPart("day_to", servise.getDay_to())
                           .addPart(servise.getImages().get(0));
                            RequestBody requestBody = builder.build();
                    Log.i("ahmed",servise.getDay_from());
                 Log.i("ahmed", ""+servise.getImages().get(0));

                    model.send("Bearer"+token,"application/json",l,requestBody);
                }
            }
        });
        //time
        //int hours=
        binding.datatime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtype_and_time();
            }
        });
    model.serveisREquste.observe(getViewLifecycleOwner(), new Observer<DataServeisREquste>() {
        @Override
        public void onChanged(DataServeisREquste dataServeisREquste) {
            if (dataServeisREquste.getStatus()==200){
                myDialogprossing.dismiss();
                secsesssend(""+dataServeisREquste.getMessage());

                 }
            else {
                Toast.makeText(getContext(), "" + dataServeisREquste.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
    });
        return binding.getRoot();
    }



    @Override
    public void getId(int id) {
        idtype=String.valueOf(id);
    }
    public void call () {
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_chose_photo);
        TextView camera=myDialog.findViewById(R.id.camera);
        TextView Gallrey=myDialog.findViewById(R.id.Gallrey);
        TextView cancel=myDialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);

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
            }
        });
        Gallrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),2);

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
            }
        });

        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
    public void showtype_and_time () {
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_time_diloge);
        TextView formtime=myDialog.findViewById(R.id.fromdate);
        TextView endtime=myDialog.findViewById(R.id.todate);
        TextView okay=myDialog.findViewById(R.id.okay);

        //type
        final int year=cal.get(Calendar.YEAR);
        final int month=cal.get(Calendar.MONTH);
        final int day=cal.get(Calendar.DAY_OF_MONTH);
        int hours=cal.get(Calendar.MILLISECOND);
        int minute=cal.get(Calendar.MINUTE);
        String sc="00";

        //int th=cal.get(Calendar.T)
        formtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog1 = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 =i1+1;
//
                        Startdata=i+"-"+i1+"-"+i2;
                        formtime.setText(Startdata);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                starttime=i+":"+i1+":"+sc;
                            formtime.setText(starttime+" "+Startdata);
                               // Toast.makeText(getContext(), ""+starttime+" "+Startdata, Toast.LENGTH_SHORT).show();
                            }
                        },hours,minute,true);
                        timePickerDialog.show();
                    }
                },year,month,day);
                pickerDialog1.show();
            }
        });

        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog1 = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 =i1+1;
//
                        enddate=i+"-"+i1+"-"+i2;
                        endtime.setText(enddate);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                Endtime=i+":"+i1+":"+sc;
                                endtime.setText(Endtime+" "+enddate);
                                //Toast.makeText(getContext(), ""+starttime+" "+Startdata, Toast.LENGTH_SHORT).show();
                            }
                        },hours,minute,true);
                        timePickerDialog.show();
                    }
                },year,month,day);
                pickerDialog1.show();
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.datatime.setText(starttime+" "+Startdata+" "+Endtime+" "+enddate);
                myDialog.dismiss();
            }
        });



        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
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
    private void saveImageGalrey() {
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