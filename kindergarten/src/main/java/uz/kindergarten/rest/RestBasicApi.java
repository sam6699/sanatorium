package uz.kindergarten.rest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import uz.kindergarten.entity.Company;
import uz.kindergarten.entity.Contacts;
import uz.kindergarten.entity.Main;
import uz.kindergarten.entity.Reserve;
import uz.kindergarten.entity.Room;
import uz.kindergarten.entity.RoomImage;

public interface RestBasicApi {

    @GET("company")
    Call<ArrayList<Company>> getAllCompanies();

    @GET("company/main")
    Call<Main> getMainId(@Query("id") Integer id);


    @GET("company/contacts")
    Call<Contacts> getConstacts(@Query("company_id") int id);

    @GET("company/rooms")
    Call<ArrayList<Room>> getRooms(@Query("company_id") int id);

    @GET("company/roomsimage")
    Call<ArrayList<RoomImage>> getRoomsImage(@Query("company_id") int id);

    @POST("reserve")
    Call<Reserve> addReserve(@Body Reserve data);


}
