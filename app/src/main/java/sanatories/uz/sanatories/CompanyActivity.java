package sanatories.uz.sanatories;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import sanatories.uz.sanatories.mygallery.GalleryFragment;

public class CompanyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static CompanyActivity instance;
    public  int company;
    public int main;

    MainFragment mf;
    public int days;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instance = this;
        Intent intent = getIntent();
        company = intent.getIntExtra("company",0);
        main = intent.getIntExtra("main",0);
        days = intent.getIntExtra("days",0);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        mf = new MainFragment();
        ft.replace(R.id.main_frame,mf);
        ft.commit();




    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(this,SanatoriesList.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.company, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        ReserveFragment rf = new ReserveFragment();
        GalleryFragment gf = new GalleryFragment();
        ContactsFrame cf = new ContactsFrame();
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            ftt.replace(R.id.main_frame,mf);
            System.out.println("main");
        } else if (id == R.id.nav_gallery) {
            ftt.replace(R.id.main_frame,rf);
            System.out.println("reserve");
        } else if (id == R.id.nav_slideshow) {
            System.out.println();
            ftt.replace(R.id.main_frame,gf);
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);



        } else if (id == R.id.nav_manage) {
            ftt.replace(R.id.main_frame,cf);

        }
//                ftt.addToBackStack(null);
                ftt.commit();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("activity stopped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("activity restarted");

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("activity start");

    }


    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("activity pause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("activity destroy");

    }
}
