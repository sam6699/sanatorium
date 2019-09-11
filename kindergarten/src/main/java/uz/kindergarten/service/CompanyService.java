package uz.kindergarten.service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.kindergarten.adapters.ListItemAdapter;
import uz.kindergarten.entity.Company;
import uz.kindergarten.rest.RestConnector;

public class CompanyService {



    public void getAllCompanies(ListItemAdapter la){
    final ArrayList<Company> list = new ArrayList<>();

        final ListItemAdapter finalLa = la;
        RestConnector.getInstance().getRestBasicApi().getAllCompanies().enqueue(new Callback<ArrayList<Company>>() {
        @Override
        public void onResponse(Call<ArrayList<Company>> call, Response<ArrayList<Company>> response) {
            list.addAll(response.body());
            System.out.println(list.size()+"---------------?????????????");
            finalLa.setList(list);
            finalLa.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<ArrayList<Company>> call, Throwable t) {
            t.printStackTrace();
        }
    });


}



}
