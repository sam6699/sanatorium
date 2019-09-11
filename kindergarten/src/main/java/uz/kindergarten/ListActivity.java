package uz.kindergarten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import uz.kindergarten.adapters.ListItemAdapter;
import uz.kindergarten.adapters.RecyclerItemClickListener;
import uz.kindergarten.entity.Company;
import uz.kindergarten.service.CompanyService;

public class ListActivity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerItemClickListener nest;
    ArrayList<Company> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.companies);

        init();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sign) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ListItemAdapter la = new ListItemAdapter(new ArrayList<Company>());
        rv.setAdapter(la);
        rv.getAdapter().notifyDataSetChanged();
        new CompanyService().getAllCompanies(la);
        rv.removeOnItemTouchListener(nest);
        System.out.println("start");
        nest = new RecyclerItemClickListener(this, rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                redirect(list.get(position).getId(),list.get(position).getMainId().getId());

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        rv.addOnItemTouchListener(nest);


    }

    private void redirect(int company_id,int main){
//        Intent intent = new Intent(this,CompanyActivity.class);
//        intent.putExtra("company",company_id);
//        intent.putExtra("main",main);
//        startActivity(intent);


    }

}
