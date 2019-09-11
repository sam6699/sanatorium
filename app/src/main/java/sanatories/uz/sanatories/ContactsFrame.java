package sanatories.uz.sanatories;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.entity.Company;
import sanatories.uz.sanatories.entity.Contacts;
import sanatories.uz.sanatories.entity.Image;
import sanatories.uz.sanatories.rest.RestConnector;

@SuppressLint("ValidFragment")
public class ContactsFrame extends Fragment {
    TextView adress,phone,mail;
    int company;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_contacts_fragment, null);
        adress = v.findViewById(R.id.adress);
        phone = v.findViewById(R.id.numbers);
        mail = v.findViewById(R.id.mail);
        company = CompanyActivity.instance.company;
        RestConnector.getInstance().getRestBasicApi().getConstacts(company).enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(Call<Contacts> call, Response<Contacts> response) {
                Contacts c = response.body();
                adress.setText(c.getAdress());
                phone.setText(c.getPhone());
                mail.setText(c.getMail());
            }

            @Override
            public void onFailure(Call<Contacts> call, Throwable t) {

            }
        });





        return v;
    }

}
