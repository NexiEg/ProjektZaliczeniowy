package com.example.projektzaliczeniowy2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FeedReaderDbHelper feedReaderDbHelper;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
//        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment2()).commit();


//        FeedReaderContract.FeedReaderDbHelper dbHelper = new FeedReaderContract.FeedReaderDbHelper(getApplicationContext());
        feedReaderDbHelper = new FeedReaderDbHelper(getApplicationContext());

        feedReaderDbHelper.dropTable();
        feedReaderDbHelper.insertData();


        drawerLayout = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment2()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment2());
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment2()).commit();
////            navigationView.setCheckedItem(R.id.item2);
//        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.setCheckedItem(item.getItemId());
        switch (item.getItemId()) {
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment()).commit();
                break;
            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment2()).commit();
                break;
            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment3()).commit();
                break;
            case R.id.item4:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment4()).commit();
                break;
            case R.id.autor:
                showAlert();
                break;
            case R.id.wyloguj:
                navigationView.setCheckedItem(R.id.item2);
                toolbar.setVisibility(View.GONE);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                drawerLayout= null;
//                toolbar = null;
//                HomeFragment2.drawerLayout = null;
//                HomeFragment2.toolbar = null;
//                toolbar.setVisibility(View.GONE);
//                toggle.syncState();
//                drawerLayout.close();
//                toolbar = null;
//                HomeFragment2.drawerLayout.close();
//                HomeFragment2.toolbar = null;
//                HomeFragment2.navigationView = null;
//                HomeFragment2.toggle = null;
//                HomeFragment2.toggle.syncState();
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment2()).commit();
                HomeFragment2.zalogowany = false;
                Toast.makeText(getApplicationContext(), "Wylogowano", Toast.LENGTH_SHORT).show();
                break;
        }
//        if(drawerLayout != null) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    private void showAlert() {
        AlertDialog builder=new AlertDialog.Builder(this)
                .setMessage(getString(R.string.Autor))
                .setTitle(getString(R.string.Oautorze))
                .create();
        builder.show();
    }
}