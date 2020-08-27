package com.example.myapplication;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.Fragment.BarCodeFragment;
import com.example.myapplication.Fragment.GiaoDichFragment;
import com.example.myapplication.Fragment.TuDongFragment;
import com.google.android.material.navigation.NavigationView;

public class StayHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_home);
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TuDongFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_canhan);
        }
    }

    private void init() {
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_canhan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TuDongFragment()).commit();
                break;
            case R.id.nav_danhmuc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GiaoDichFragment()).commit();
                break;
            case R.id.nav_baohiem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BarCodeFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}