package sanatories.uz.sanatories.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sanatories.uz.sanatories.entity.Company;
import sanatories.uz.sanatories.entity.Image;

public class RestConnector {

    private static RestConnector instance;
    private Retrofit retrofit;
    private RestBasicApi restBasicApi;

    public static RestConnector getInstance(){
        if (instance==null)
            instance = new RestConnector();
        return instance;
    }

    private RestConnector(){
         retrofit= new Retrofit.Builder().baseUrl("http://192.168.43.248:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
         restBasicApi = retrofit.create(RestBasicApi.class);
    }

    public RestBasicApi getRestBasicApi(){
        return restBasicApi;
    }




    }


