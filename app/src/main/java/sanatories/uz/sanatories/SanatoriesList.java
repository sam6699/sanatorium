package sanatories.uz.sanatories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanatories.uz.sanatories.adapters.ListItemAdapter;
import sanatories.uz.sanatories.adapters.RecyclerItemClickListener;
import sanatories.uz.sanatories.entity.Company;
import sanatories.uz.sanatories.entity.Main;
import sanatories.uz.sanatories.rest.RestBasicApi;
import sanatories.uz.sanatories.rest.RestConnector;
import sanatories.uz.sanatories.service.CompanyService;

public class SanatoriesList extends AppCompatActivity {
    RecyclerView rv;
    RecyclerItemClickListener nest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanatories_list);
        rv = findViewById(R.id.com_list);

        init();

    }

    private void init() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

                final ListItemAdapter la = new ListItemAdapter(new ArrayList<Company>());
                rv.setAdapter(la);
                rv.getAdapter().notifyDataSetChanged();
        new CompanyService().getAllCompanies(la);
        rv.removeOnItemTouchListener(nest);
        System.out.println("start");
        nest = new RecyclerItemClickListener(this, rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                redirect(la.getLs().get(position).getId(),la.getLs().get(position).getMainId(),la.getLs().get(position).getDays());

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        rv.addOnItemTouchListener(nest);


    }



        private void redirect(int company_id,int main,int days){
            Intent intent = new Intent(this,CompanyActivity.class);
            intent.putExtra("company",company_id);
            intent.putExtra("main",main);
            intent.putExtra("days",days);
            startActivity(intent);


        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
