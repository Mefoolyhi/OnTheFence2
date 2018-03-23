package gabdorahmanova.onthefence.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Fragments.FavouritesFragment;
import gabdorahmanova.onthefence.Fragments.TheatresFragment;
import gabdorahmanova.onthefence.Fragments.NewsFragment;
import gabdorahmanova.onthefence.Helpers.OnSwipeTouchListener;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChoosingActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = NewsFragment.newInstance();


                                break;
                            case R.id.action_item2:

                                selectedFragment = TheatresFragment.newInstance();

                                break;
                            case R.id.action_item3:
                                selectedFragment = FavouritesFragment.newInstance();
                                break;
                        }

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment, selectedFragment);
                        transaction.commit();

                        return true;
                    }
                });

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, NewsFragment.newInstance());
        transaction.commit();


        bottomNavigationView.getMenu().getItem(0).setChecked(true);


        SharedPreferences sp = getSharedPreferences("hasVisited",
                Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {



            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();

        }
    }

public void changeTheBottom(int i){

    BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

    bottomNavigationView.getMenu().getItem(i).setChecked(true);

}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_show) {
            // Handle the camera action
        } else if (id == R.id.nav_concert) {

        } else if (id == R.id.nav_perfomance) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_myPerfomance) {

        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



