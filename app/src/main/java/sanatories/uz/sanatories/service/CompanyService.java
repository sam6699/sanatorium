package sanatories.uz.sanatories.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.adapters.ListItemAdapter;
import sanatories.uz.sanatories.entity.Company;
import sanatories.uz.sanatories.entity.Main;
import sanatories.uz.sanatories.entity.RoomImage;
import sanatories.uz.sanatories.rest.RestConnector;

public class CompanyService {

    private static ArrayList<Main> tt;

    public static ArrayList<Main> gettt() {
        RestConnector.getInstance().getRestBasicApi().getMainId(1).enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                System.out.println(response.body().getText());
            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {

            }
        });


        return tt;
    }

    public void getAllCompanies( ListItemAdapter la){
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

    public ArrayList<RoomImage> loader(int id) {
        final ArrayList<RoomImage> ls = new ArrayList<>();
        RestConnector.getInstance().getRestBasicApi().getRoomsImage(id).enqueue(new Callback<ArrayList<RoomImage>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomImage>> call, Response<ArrayList<RoomImage>> response) {
                ls.addAll(response.body());
                System.out.println("image list size "+ls.size());


            }

            @Override
            public void onFailure(Call<ArrayList<RoomImage>> call, Throwable t) {

            }
        });

                System.out.println("image list size ++++"+ls.size());
        return ls;

    }

}
