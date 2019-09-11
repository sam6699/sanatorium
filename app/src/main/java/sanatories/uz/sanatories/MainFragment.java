package sanatories.uz.sanatories;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.entity.Main;
import sanatories.uz.sanatories.rest.RestConnector;

public class MainFragment extends Fragment {
    final String LOG_TAG = "myLogs";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment1 onCreateView");

        View v = inflater.inflate(R.layout.content_main_fragment,null);
        final TextView tv = v.findViewById(R.id.content_text);

        RestConnector.getInstance().getRestBasicApi().getMainId(CompanyActivity.instance.main).enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                Main main =response.body();
                if (main.getMain_img_id().getPath()!=null){
                    byte[] img = Base64.decode(main.getMain_img_id().getPath(),Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
                    Bitmap b = Bitmap.createScaledBitmap(bitmap,900,250,true);
                    CompanyActivity.instance.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(getResources(),b));

                }

                System.out.println(main.getTitle_text());
                tv.setText(main.getText());
                CompanyActivity.instance.getSupportActionBar().setTitle(main.getTitle_text());

            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {
                                    t.printStackTrace();
            }
        });




        return v;
    }




    public void init(){



    }
}

class HttpRequest extends AsyncTask<Void,Void,Main> {


    @Override
    protected Main doInBackground(Void... voids) {
        Main test;
        String s = "http://192.168.1.104:8080/company/main?id=1";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        test = restTemplate.getForObject(s,Main.class);



        return test;


    }

    @Override
    protected void onPostExecute(Main main) {
        super.onPostExecute(main);
        System.out.println("from rest main "+main.getText());
    }
}
