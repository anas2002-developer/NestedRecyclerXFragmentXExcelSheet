package com.anas.fragxnavxrecyclerxexcel;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.anas.fragxnavxrecyclerxexcel.Fragments.HelpFragment;
import com.anas.fragxnavxrecyclerxexcel.Fragments.HomeFragment;
import com.anas.fragxnavxrecyclerxexcel.Fragments.ProfileFragment;
import com.anas.fragxnavxrecyclerxexcel.Fragments.SearchFragment;
import com.anas.fragxnavxrecyclerxexcel.Fragments.UtilitiesFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout layDL;
    NavigationView vNV;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        layDL = findViewById(R.id.layDL);
        vNV = findViewById(R.id.vNV);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vivobook");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layDL, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);

        layDL.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layFL, new HomeFragment())
                    .commit();
            vNV.setCheckedItem(R.id.home_item);
        }

        NavClick();
}

    private void NavClick() {
        vNV.setNavigationItemSelectedListener(item -> {
            Fragment frag=null;
            switch (item.getItemId()) {

                case R.id.home_item:
                    frag = new HomeFragment();
                    break;
                case R.id.profile_item:
                    frag = new ProfileFragment();
                    break;
                case R.id.search_item:
                    frag = new SearchFragment();
                    break;
                case R.id.utilites_item:
                    frag = new UtilitiesFragment();
                    break;
                case R.id.help_item:
                    frag = new HelpFragment();
                    break;
            }
            layDL.closeDrawer(GravityCompat.START);

            if (frag!=null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layFL, frag)
                        .commit();
            }

            return true;
        });
    }


    @Override
    public void onBackPressed() {
        Fragment currFrag = getSupportFragmentManager().findFragmentById(R.id.layFL);
        HomeFragment homeFragment=new HomeFragment();
        if (currFrag!=new HomeFragment()){
            getSupportFragmentManager().beginTransaction().replace(R.id.layFL,new HomeFragment());
        } else if (layDL.isDrawerOpen(GravityCompat.START)) {
            layDL.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}