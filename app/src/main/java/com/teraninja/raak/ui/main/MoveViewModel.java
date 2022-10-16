package com.teraninja.raak.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teraninja.raak.DataClinet.RetrofitAPI;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MoveViewModel extends ViewModel {
    public MutableLiveData<DataLogin> getDataLogin = new MutableLiveData<>();
    public MutableLiveData<DataRequsetcode> Sendphone = new MutableLiveData<>();
    public MutableLiveData<DataVerifyPhone> VerifyPhone = new MutableLiveData<>();
    public MutableLiveData<DataChengePassword> chengePassword= new MutableLiveData<>();
    public MutableLiveData<DataOrder> dataOrder= new MutableLiveData<>();
    public MutableLiveData<DataHome> Home= new MutableLiveData<>();
    public MutableLiveData<DataProfile> Profile= new MutableLiveData<>();
    public MutableLiveData<DataMaintence> maintence1= new MutableLiveData<>();
    public MutableLiveData<DataDetilseHome> getDitalse= new MutableLiveData<>();
    public MutableLiveData<ModelData> getoffer= new MutableLiveData<>();
    public MutableLiveData<DataServesOrder> getOrder= new MutableLiveData<>();
    public MutableLiveData<Datagetnumber> getnumber= new MutableLiveData<>();
    public MutableLiveData<DataNotifications> Notificationsget= new MutableLiveData<>();
    public MutableLiveData<DataLogOut> logouttt= new MutableLiveData<>();
    public  MutableLiveData<DataNewSebiscribe> newSebiscribe= new MutableLiveData<>();
    public  MutableLiveData<DataNewbin> dataNewbin = new MutableLiveData<>();
    public MutableLiveData<DataDamage> dataDamage= new MutableLiveData<>();
    public MutableLiveData<DataDamageLocation> dataDamageLocation= new MutableLiveData<>();
    public  MutableLiveData<DataServeisREquste> serveisREquste = new MutableLiveData<>();
    public  MutableLiveData<DataComplaints> comino = new MutableLiveData<>();

    public void  getlogin( SendDataLogin dataLogin){
        Observable observable = RetrofitAPI.getInstans().getLogin(dataLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getDataLogin.setValue((DataLogin) o);
                Log.i("error data:", ""+((DataLogin) o).message);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error data:", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataLogin data = new DataLogin(object.getInt("status"),object.getString("message"));
                        getDataLogin.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void  getcode(String Accept, SendDataCode code){
        Observable observable = RetrofitAPI.getInstans().Sendphone(Accept,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Sendphone.setValue((DataRequsetcode) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataRequsetcode data = new DataRequsetcode(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        Sendphone.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void  VerifyPhone(SendDataVerifyPhone phone){
        Observable observable = RetrofitAPI.getInstans().VerifyPhone(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                VerifyPhone.setValue((DataVerifyPhone) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataVerifyPhone data = new DataVerifyPhone(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        VerifyPhone.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void chengePassword(SendDataChengePassword password){
        Observable observable =RetrofitAPI.getInstans().ChengePassword(password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                chengePassword.setValue((DataChengePassword) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataChengePassword data = new DataChengePassword(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        chengePassword.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Home(SendDataHome home){
        Observable observable =RetrofitAPI.getInstans().Home(home)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Home.setValue((DataHome) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataHome data = new DataHome(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        Home.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Order(String Token){
        Observable observable =RetrofitAPI.getInstans().Order(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataOrder.setValue((DataOrder) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOrder data = new DataOrder(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        dataOrder.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Profile(String Token){
        Observable observable =RetrofitAPI.getInstans().profile(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Profile.setValue((DataProfile) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataProfile data = new DataProfile(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        Profile.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void maintence(String Token){
        Observable observable =RetrofitAPI.getInstans().Maintence(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                maintence1.setValue((DataMaintence) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataMaintence data = new DataMaintence(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        maintence1.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getDetailse(int id){
        Observable observable =RetrofitAPI.getInstans().getDitilse(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getDitalse.setValue((DataDetilseHome) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDetilseHome data = new DataDetilseHome(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        getDitalse.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getOffer(SendDataOfeer dataOfeer){
        Observable observable =RetrofitAPI.getInstans().getoffer(dataOfeer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getoffer.setValue((ModelData) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        ModelData data = new ModelData(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        getoffer.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getOrders(String Token){
        Observable observable =RetrofitAPI.getInstans().getorders(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getOrder.setValue((DataServesOrder) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataServesOrder data = new DataServesOrder(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        getOrder.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getnumbers(String Accept,String lang){
        Observable observable =RetrofitAPI.getInstans().getnumbers(Accept,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getnumber.setValue((Datagetnumber) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Datagetnumber data = new Datagetnumber(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        getnumber.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Notifications(String Token){
        Observable observable =RetrofitAPI.getInstans().Notifications(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Notificationsget.setValue((DataNotifications) o);
                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataNotifications data = new DataNotifications(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        Notificationsget.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void logout(String Token){
        Observable observable =RetrofitAPI.getInstans().logout(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                logouttt.setValue((DataLogOut) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataLogOut data = new DataLogOut(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        logouttt.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void sbiscriber(String Accept,String lang){
        Observable observable =RetrofitAPI.getInstans().newsbiscribe(Accept,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                newSebiscribe.setValue((DataNewSebiscribe) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataNewSebiscribe data = new DataNewSebiscribe(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        newSebiscribe.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void newbin(String Accept, SendDatanew lang){
        Observable observable =RetrofitAPI.getInstans().newbin(Accept,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataNewbin.setValue((DataNewbin) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataNewbin data = new DataNewbin(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        dataNewbin.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void DamageType(String Accept, String lang){
        Observable observable =RetrofitAPI.getInstans().DamageType(Accept,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataDamage.setValue((DataDamage) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDamage data = new DataDamage(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        dataDamage.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Damagelocation(String Accept, String lang){
        Observable observable =RetrofitAPI.getInstans().DamageLocation(Accept,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataDamageLocation.setValue((DataDamageLocation) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    ResponseBody responseBody =((HttpException)e)
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDamageLocation data = new DataDamageLocation(object.getInt("status"),object.getString("errors"),object.getString("message"));
                        dataDamageLocation.setValue(data);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void send(String token,String Accept, String lang, RequestBody requestBody){
        Observable observable =RetrofitAPI.getInstans().send(token,Accept,lang,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                serveisREquste.setValue((DataServeisREquste) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataServeisREquste data = new DataServeisREquste(object.getInt("status"), object.getString("errors"), object.getString("message"));
                        serveisREquste.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void complaints(String token,String Accept, String lang, RequestBody requestBody){
        Observable observable =RetrofitAPI.getInstans().complaints(token,Accept,lang,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                comino.setValue((DataComplaints) o);
              Log.i("ahmed", ""+((DataComplaints) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataComplaints data = new DataComplaints(object.getInt("status"), object.getString("errors"), object.getString("message"));
                        comino.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

}
