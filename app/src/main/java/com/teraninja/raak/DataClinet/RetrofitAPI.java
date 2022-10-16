package com.teraninja.raak.DataClinet;

import com.teraninja.raak.Model.DataChengePassword;
import com.teraninja.raak.Model.DataComplaints;
import com.teraninja.raak.Model.DataDamage;
import com.teraninja.raak.Model.DataDamageLocation;
import com.teraninja.raak.Model.DataDetilseHome;
import com.teraninja.raak.Model.DataHome;
import com.teraninja.raak.Model.DataLogOut;
import com.teraninja.raak.Model.DataLogin;
import com.teraninja.raak.Model.DataMaintence;
import com.teraninja.raak.Model.DataNewSebiscribe;
import com.teraninja.raak.Model.DataNewbin;
import com.teraninja.raak.Model.DataNotifications;
import com.teraninja.raak.Model.DataOrder;
import com.teraninja.raak.Model.DataProfile;
import com.teraninja.raak.Model.DataRequsetcode;
import com.teraninja.raak.Model.DataServeisREquste;
import com.teraninja.raak.Model.DataServesOrder;
import com.teraninja.raak.Model.DataVerifyPhone;
import com.teraninja.raak.Model.Datagetnumber;
import com.teraninja.raak.Model.ModelData;
import com.teraninja.raak.Model.SendDataChengePassword;
import com.teraninja.raak.Model.SendDataCode;
import com.teraninja.raak.Model.SendDataHome;
import com.teraninja.raak.Model.SendDataLogin;
import com.teraninja.raak.Model.SendDataOfeer;
import com.teraninja.raak.Model.SendDataVerifyPhone;
import com.teraninja.raak.Model.SendDatanew;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    InterfaceAPI interFaceAPI;
    private static final String BASE_URL="https://raak.teraninjadev.com/";
    public static RetrofitAPI instans;


    public RetrofitAPI() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        interFaceAPI=retrofit.create(InterfaceAPI.class);

    }




    public static RetrofitAPI getInstans() {
        if (null==instans){
            instans=new RetrofitAPI();
        }
        return instans;
    }

    public Observable<DataLogin> getLogin(SendDataLogin dataLogin){

        return interFaceAPI.getLogin(dataLogin);
    }

    public Observable<DataRequsetcode> Sendphone(String Accept,SendDataCode code){

        return interFaceAPI.sendphone(Accept,code);

    }
    public Observable<DataVerifyPhone> VerifyPhone(SendDataVerifyPhone phone){

        return interFaceAPI.VerifyPhone(phone);

    }

    public Observable<DataChengePassword> ChengePassword(SendDataChengePassword password){

        return interFaceAPI.ChengePassword(password);

    }
   public Observable<DataHome> Home(SendDataHome home){
        return interFaceAPI.HomePager(home);
   }

    public Observable<DataOrder> Order(String Token){
        return interFaceAPI.Order(Token);
    }

    public Observable<DataProfile> profile(String Token){

        return interFaceAPI.Profile(Token);
    }
    public Observable<DataMaintence> Maintence(String Token){

        return interFaceAPI.Maintence(Token);
    }
    public Observable<DataDetilseHome> getDitilse(int id){

        return interFaceAPI.getDetilse(id);
    }
    public Observable<ModelData> getoffer(SendDataOfeer dataOfeer){

        return interFaceAPI.getOffer(dataOfeer);
    }
    public Observable<DataServesOrder> getorders(String token){

        return interFaceAPI.getOrders(token);
    }
    public Observable<Datagetnumber> getnumbers(String Accept,String lang){

        return interFaceAPI.getnumber(Accept,lang);
    }
    public Observable<DataNotifications> Notifications(String Token){

        return interFaceAPI.Notifications(Token);
    }
    public Observable<DataLogOut> logout(String Token){

        return interFaceAPI.Logout(Token);
    }
    public Observable<DataNewSebiscribe> newsbiscribe(String Accept,String lang){

        return interFaceAPI.newsbiscribe(Accept,lang);
    }
    public Observable<DataNewbin> newbin(String Accept, SendDatanew datanew){

        return interFaceAPI.newbin(Accept,datanew);
    }
    public Observable<DataDamage> DamageType(String Accept, String lang){

        return interFaceAPI.DamageType(Accept,lang);
    }
    public Observable<DataDamageLocation> DamageLocation(String Accept, String lang){

        return interFaceAPI.Damagelocation(Accept,lang);
    }
    public Observable<DataServeisREquste> send(String token,String Accept, String lang, RequestBody requestBody){

        return interFaceAPI.send(token,Accept,lang,requestBody);
    }
    public Observable<DataComplaints> complaints(String token, String Accept, String lang, RequestBody requestBody){

        return interFaceAPI.complaints(token,Accept,lang,requestBody);
    }

}


