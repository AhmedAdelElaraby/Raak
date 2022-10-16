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
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceAPI {
    @POST("api/auth/login")
    Observable<DataLogin> getLogin(@Body SendDataLogin login);
    @POST("api/sendCode")
    Observable<DataRequsetcode> sendphone(@Header ("Accept") String Accept,@Body SendDataCode code);
    @POST("api/verifyPhone")
    Observable<DataVerifyPhone> VerifyPhone(@Body SendDataVerifyPhone phone);
    @POST("api/forgetPassword")
    Observable<DataChengePassword> ChengePassword(@Body SendDataChengePassword password);
    @POST("api/get-buildings")
    Observable<DataHome> HomePager (@Body SendDataHome home);
    @GET("api/maintenance-contracts")
    Observable<DataOrder> Order (@Header("Authorization") String Token);
    @GET("api/auth/user-profile")
    Observable<DataProfile> Profile (@Header("Authorization") String Token);
    @GET("api/get-user-buildings")
    Observable<DataMaintence> Maintence(@Header("Authorization") String Token);
    @GET("api/buildings/{buidling-id}")
    Observable<DataDetilseHome> getDetilse(@Path("buidling-id") int id);
    @POST("api/contact-us")
    Observable<ModelData> getOffer(@Body SendDataOfeer dataOfeer);
    @GET("api/maintenance-services")
    Observable<DataServesOrder> getOrders(@Header("Authorization") String Token);
    @GET("api/get-phone-number")
    Observable<Datagetnumber> getnumber(@Header("Accept") String Accept,@Header("lang") String lang);
    @GET("api/get-user-notifications")
    Observable<DataNotifications> Notifications(@Header("Authorization") String Token);
    @POST("api/auth/logout")
    Observable<DataLogOut> Logout(@Header("Authorization") String Token);
    @GET("api/contract-durations")
    Observable<DataNewSebiscribe> newsbiscribe(@Header("Accept") String Accept,@Header("lang") String lang);
    @POST("api/renew-contract")
    Observable<DataNewbin> newbin(@Header("Authorization") String Token, @Body SendDatanew datanew);
    @GET("api/damage-types")
    Observable<DataDamage> DamageType(@Header("Accept") String Accept,@Header("lang") String lang);
    @GET("api/damage-locations")
    Observable<DataDamageLocation> Damagelocation(@Header("Accept") String Accept, @Header("lang") String lang);
    @POST("api/maintenance-services")
    Observable<DataServeisREquste> send(@Header("Authorization") String Token,
                                        @Header("Accept") String Accept, @Header("lang") String AcceptLanguage , @Body RequestBody sendCompany);
    @POST("api/complaints")
    Observable<DataComplaints> complaints(@Header("Authorization") String Token
            , @Header("Accept") String Accept, @Header("lang") String AcceptLanguage , @Body RequestBody complaints);

}
