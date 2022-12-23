package com.example.projektzaliczeniowy2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class HomeFragment3 extends Fragment {
    ViewGroup viewGroup;
    FeedReaderDbHelper feedReaderDbHelper;
    ListView listView;
    public static ArrayList<String> dane = new ArrayList<String>();
    Button pokazDane;
    Button usunDane;
    public static Boolean czyPrzenosic = false;
    public static Boolean czyPrzenosic2 = false;

    public static Integer ID;

    public static ArrayList<String> daneID = new ArrayList<String>();

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home3_fragment,container,false);


//        if(HomeFragment2.sharedPreferences.getBoolean("zalogowany", false)) {
//            HomeFragment2.toolbar.setVisibility(View.VISIBLE);
//        }
        listView = viewGroup.findViewById(R.id.zakupioneRzeczy);

        pokazDane = viewGroup.findViewById(R.id.przycisk_pokaz_zamowienia);
        usunDane = viewGroup.findViewById(R.id.usun_z_koszyka);

        feedReaderDbHelper = new FeedReaderDbHelper(getContext());
//        if(HomeFragment2.nazwa.getText().toString().equals("")){
//            HomeFragment2.nazwa.setText(feedReaderDbHelper.getKlientLogin(HomeFragment2.email.getText().toString()));
//            HomeFragment2.sharedPreferences.edit().putString("nazwa", HomeFragment2.nazwa.getText().toString()).apply();
//        }

        dane = feedReaderDbHelper.getShoppings(HomeFragment2.nazwa.getText().toString());
        daneID = feedReaderDbHelper.getShoppingsID(HomeFragment2.nazwa.getText().toString());
        if(dane.size() == 0){
            dane.add(getString(R.string.Brak_zakupionych_rzeczy));
            czyPrzenosic = false;
        }
        else {
            czyPrzenosic = true;
        }
        MainAdapter adapter = new MainAdapter(getContext(), dane);
        listView.setAdapter(adapter);

//        listView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.row, dane));

        pokazDane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dane = feedReaderDbHelper.getShoppings(HomeFragment2.nazwa.getText().toString());
                daneID = feedReaderDbHelper.getShoppingsID(HomeFragment2.nazwa.getText().toString());
                for(int i = 0; i < dane.size(); i++){
                    Log.i("TAG", dane.get(i));
                }
                if(dane.size() == 0){
                    dane.add(getString(R.string.Brak_zakupionych_rzeczy));
                    czyPrzenosic = false;
                }
                else {
                    czyPrzenosic = true;
                }
                MainAdapter adapter = new MainAdapter(getContext(), dane);
                listView.setAdapter(adapter);
//                listView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.row, dane));
//                Toast.makeText(getContext(), HomeFragment2.nazwa.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        usunDane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedReaderDbHelper.deleteShoppings(HomeFragment2.nazwa.getText().toString());
                dane = feedReaderDbHelper.getShoppings(HomeFragment2.nazwa.getText().toString());
                daneID = feedReaderDbHelper.getShoppingsID(HomeFragment2.nazwa.getText().toString());
                if(dane.size() == 0){
                    dane.add(getString(R.string.Brak_zakupionych_rzeczy));
                    czyPrzenosic = false;
                }

//                listView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.row, dane));
                MainAdapter adapter = new MainAdapter(getContext(), dane);
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), getString(R.string.Wybrano) + dane.get(position), Toast.LENGTH_SHORT).show();
                Log.i("TAG", getString(R.string.Wybrano) + dane.get(position));
                // open new activity with more info's about selected item

                if(czyPrzenosic){
                    ID = position;
                    czyPrzenosic2 = true;
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment4()).commit();
                }

            }
        });

        return viewGroup;
    }
}