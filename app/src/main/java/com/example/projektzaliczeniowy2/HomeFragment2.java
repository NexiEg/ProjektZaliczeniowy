package com.example.projektzaliczeniowy2;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.zip.Inflater;

public class HomeFragment2 extends Fragment {
    ViewGroup viewGroup;
    FeedReaderDbHelper feedReaderDbHelper;

    public static EditText nazwa;
    EditText haslo;
    Button zaloguj;
    Button zarejestruj;
    public static EditText email;

    public static Boolean zalogowany;

    public static SharedPreferences sharedPreferences;

    public static DrawerLayout drawerLayout;
    public static Toolbar toolbar;
    public static ActionBarDrawerToggle toggle;
    TextView nazwaUzytkownika;
    public static NavigationView navigationView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home2_fragment,container,false);

        sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);

        nazwa = viewGroup.findViewById(R.id.editTextTextPersonName);
        haslo = viewGroup.findViewById(R.id.editTextTextPassword);
        email = viewGroup.findViewById(R.id.editTextTextEmail);

        zaloguj = viewGroup.findViewById(R.id.button);
        zarejestruj = viewGroup.findViewById(R.id.button2);
        navigationView = getActivity().findViewById(R.id.navigation_view);

        drawerLayout = getActivity().findViewById(R.id.nav_view);
        toolbar = getActivity().findViewById(R.id.toolbar);


        feedReaderDbHelper = new FeedReaderDbHelper(getContext());

//        navigationView.setVisibility(View.GONE);
//        toolbar.setVisibility(View.GONE);
//        drawerLayout.setVisibility(View.GONE);


        nazwa.setText(sharedPreferences.getString("nazwa", ""));
        haslo.setText(sharedPreferences.getString("haslo", ""));
        email.setText(sharedPreferences.getString("email", ""));



        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zalogowany = feedReaderDbHelper.sprawdzCzyPoprawneDane(nazwa.getText().toString(), haslo.getText().toString(), email.getText().toString());
                if(zalogowany){
                    sharedPreferences.edit().putString("nazwa", nazwa.getText().toString()).apply();
                    sharedPreferences.edit().putString("email", email.getText().toString()).apply();
                    sharedPreferences.edit().putString("haslo", haslo.getText().toString()).apply();
                    Toast.makeText(getActivity(), getResources().getString(R.string.Zalogowano), Toast.LENGTH_SHORT).show();

                    toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                    drawerLayout.addDrawerListener(toggle);
                    toggle.syncState();




//                    toggle = new ActionBarDrawerToggle(, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

                    toolbar.setVisibility(View.VISIBLE);
                    if(nazwa.getText().toString().equals("")){
                        HomeFragment2.nazwa.setText(feedReaderDbHelper.getKlientLogin(HomeFragment2.email.getText().toString()));
                        sharedPreferences.edit().putString("nazwa", nazwa.getText().toString()).apply();
                    }
                    nazwaUzytkownika = getActivity().findViewById(R.id.nazwaUzytkownika);
                    nazwaUzytkownika.setText(getResources().getString(R.string.Witaj)+nazwa.getText().toString()+"!");

//                    nazwa.setText("");
//                    haslo.setText("");

//                    getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new HomeFragment()).commit();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    navigationView.setCheckedItem(R.id.item1);
                    fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment()).commit();
                }
                else{
                    zalogowany = false;
                    Toast.makeText(getActivity(), getResources().getString(R.string.Niepoprawne_dane), Toast.LENGTH_SHORT).show();
                    if(toolbar!=null){
                        toolbar.setVisibility(View.GONE);
                    }
                }
            }
        });

        zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nazwa.getText().length()>0 && haslo.getText().length()>0 && email.getText().length()>0){
                    if(feedReaderDbHelper.sprawdzCzyKlientIstnieje(nazwa.getText().toString(), email.getText().toString())){
                        Toast.makeText(getActivity(), getResources().getString(R.string.Uzytkownik_juz_istnieje), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        feedReaderDbHelper.dodajKlienta(nazwa.getText().toString(), haslo.getText().toString(), email.getText().toString());
                        Toast.makeText(getActivity(), getResources().getString(R.string.Zarejestrowano), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.Wypełnij_wszystkie_pola), Toast.LENGTH_SHORT).show();
                }
            }
        });



        return viewGroup;
    }

}