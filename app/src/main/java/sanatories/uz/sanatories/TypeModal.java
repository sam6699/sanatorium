package sanatories.uz.sanatories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import sanatories.uz.sanatories.adapters.RecyclerItemClickListener;
import sanatories.uz.sanatories.adapters.TypeAdapter;
import sanatories.uz.sanatories.entity.Reserve;
import sanatories.uz.sanatories.entity.RoomType;

public class TypeModal extends AppCompatActivity {
    RecyclerView rv;
    TypeAdapter ta;
    RecyclerItemClickListener nest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_modal);
        rv = findViewById(R.id.list);
//        setTitle("Xonalar");
        init();


    }


    public  void init(){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        RoomType roomType = new RoomType();
        roomType.setType("lux");
        roomType.setPrice(10000);

        ta = new TypeAdapter(ReserveFragment.rooms);
        rv.setAdapter(ta);
        rv.getAdapter().notifyDataSetChanged();
        rv.removeOnItemTouchListener(nest);
        System.out.println("start");
        nest = new RecyclerItemClickListener(this, rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println(position);
                Intent intent =new Intent();
                intent.putExtra("room_id",ReserveFragment.rooms.get(position).getId());
                intent.putExtra("name",ReserveFragment.rooms.get(position).getTypeId().getName());
                intent.putExtra("price", ReserveFragment.rooms.get(position).getPrice());
                setResult(RESULT_OK,intent);
                close();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        rv.addOnItemTouchListener(nest);
    }


    public void close(){
        this.finish();

    }


}
