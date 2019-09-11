package sanatories.uz.sanatories.service;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.entity.Main;
import sanatories.uz.sanatories.rest.RestConnector;

public class MainService {

    public Main getMainByCompany(int id){
        final Main[] m = {null};
        RestConnector.getInstance().getRestBasicApi().getMainId(1).enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {

                m[1]=response.body();


            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {

            }
        });

        return m[1];
    }


}





