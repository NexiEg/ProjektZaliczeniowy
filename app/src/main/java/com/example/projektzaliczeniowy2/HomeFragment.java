package com.example.projektzaliczeniowy2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.MailTo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.slider.Slider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    Button kup;
    FeedReaderDbHelper feedReaderDbHelper;

    ArrayList<String> items = new ArrayList<String>();
    ArrayList<Integer> images = new ArrayList<Integer>();

    ArrayList<String> items2 = new ArrayList<String>();
    ArrayList<Integer> images2 = new ArrayList<Integer>();

    ArrayList<String> items3 = new ArrayList<String>();
    ArrayList<Integer> images3 = new ArrayList<Integer>();
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    ViewGroup viewGroup;

    CheckBox checkBox;
    CheckBox checkBox2;

    TextView cena;
    Slider slider;

    Integer cena1 = 0;
    Integer cena2 = 0;
    Integer cena3 = 0;

    Integer iloscZamowien = 0;

    EditText phone;
    EditText imie;

    SmsManager smsManager;

    public void setSpinner(){
        items = feedReaderDbHelper.show("komputery", "nazwy_komputerow");
        images.add(R.drawable.komputer1);
        images.add(R.drawable.komputer2);
        images.add(R.drawable.komputer3);
//        for (int i = 0; i < items.size(); i++) {
//            images.add(R.drawable.zdj1);
//        }
        spinner = viewGroup.findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        ItemAdapter itemAdapter = new ItemAdapter(getContext(), images, items);
        spinner.setAdapter(itemAdapter);



        items2 = feedReaderDbHelper.show("myszki", "nazwy_myszek");
        images2.add(R.drawable.mysz1);
        images2.add(R.drawable.mysz2);
        images2.add(R.drawable.mysz3);
//        for (int i = 0; i < items2.size(); i++) {
//            images2.add(R.drawable.zdj1);
//        }
        spinner2 = viewGroup.findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        ItemAdapter itemAdapter2 = new ItemAdapter(getContext(), images2, items2);
        spinner2.setAdapter(itemAdapter2);



        items3 = feedReaderDbHelper.show("klawiatury", "nazwy_klawiatur");
        images3.add(R.drawable.klawiatura1);
        images3.add(R.drawable.klawiatura2);
        images3.add(R.drawable.klawiatura3);
//        for (int i = 0; i < items3.size(); i++) {
//            images3.add(R.drawable.zdj1);
//        }
        spinner3 = viewGroup.findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);

        ItemAdapter itemAdapter3 = new ItemAdapter(getContext(), images3, items3);
        spinner3.setAdapter(itemAdapter3);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.home_fragment,container,false);
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_fragment, null);
//        textView = (TextView) viewGroup.findViewById(R.id.home);
//        textView.setText("test");

//        button = viewGroup.findViewById(R.id.button1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
//            }
//        });
//        if(HomeFragment2.sharedPreferences.getBoolean("zalogowany", false)) {
//            HomeFragment2.toolbar.setVisibility(View.VISIBLE);
//        }

        phone = viewGroup.findViewById(R.id.SMSEDITTEXT);
        imie = viewGroup.findViewById(R.id.imie);

        cena = viewGroup.findViewById(R.id.cena);
        slider = viewGroup.findViewById(R.id.slider);

        kup = viewGroup.findViewById(R.id.przycisk_kup);

//        FeedReaderContract.FeedReaderDbHelper dbHelper = new FeedReaderContract.FeedReaderDbHelper(getContext());
        feedReaderDbHelper = new FeedReaderDbHelper(getContext());



//        feedReaderDbHelper.dropTable();
//

        setSpinner();

        checkBox = viewGroup.findViewById(R.id.checkbox_myszki);
        checkBox2 = viewGroup.findViewById(R.id.checkbox_klawiatury);

        spinner2.setVisibility(View.GONE);
        spinner3.setVisibility(View.GONE);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    spinner2.setVisibility(View.VISIBLE);
                    zmienCene();
                }
                else{
                    spinner2.setVisibility(View.GONE);
                    zmienCene();
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked()){
                    spinner3.setVisibility(View.VISIBLE);
                    zmienCene();
                }
                else{
                    spinner3.setVisibility(View.GONE);
                    zmienCene();
                }
            }
        });

//        slider.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                cena.setText((cena1*Math.round(slider.getValue())+cena2+cena3)+" zł");
//            }
//        });

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                cena.setText((cena1*Math.round(slider.getValue())+cena2+cena3)+" zł");
            }
        });

        kup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean czyZakupiono = false;

                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    if(slider.getValue()>0 || checkBox.isChecked() || checkBox2.isChecked()){
                        if(!phone.getText().toString().isEmpty() && !imie.getText().toString().isEmpty()){
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            String data_teraz = dtf.format(now);

                            if(slider.getValue()>0) {
                                String komputer = feedReaderDbHelper.show(FeedReaderDbHelper.FeedEntry.TABLE_NAME, FeedReaderDbHelper.FeedEntry.COLUMN_NAME_TITLE).get(spinner.getSelectedItemPosition());

                                feedReaderDbHelper.wstawZamowienie(komputer, cena1 * Math.round(slider.getValue()) + "",
                                        Math.round(slider.getValue()) + "", HomeFragment2.nazwa.getText().toString(), data_teraz);
//                                Toast.makeText(getContext(), HomeFragment2.nazwa.getText().toString(), Toast.LENGTH_SHORT).show();
                                czyZakupiono = true;
                                iloscZamowien++;
//                        feedReaderDbHelper.getShoppings("Natan");
                            }

                            if(checkBox.isChecked()){
                                String mysz = feedReaderDbHelper.show(FeedReaderDbHelper.FeedEntry.TABLE_NAME_2,
                                        FeedReaderDbHelper.FeedEntry.COLUMN_NAME_TITLE_2).get(spinner2.getSelectedItemPosition());
                                feedReaderDbHelper.wstawZamowienie(mysz, cena2+"", "1",
                                        HomeFragment2.nazwa.getText().toString(), data_teraz);
                                czyZakupiono = true;
                                iloscZamowien++;
                            }
                            if(checkBox2.isChecked()){
                                String klawiatura = feedReaderDbHelper.show(FeedReaderDbHelper.FeedEntry.TABLE_NAME_3,
                                        FeedReaderDbHelper.FeedEntry.COLUMN_NAME_TITLE_3).get(spinner3.getSelectedItemPosition());
                                feedReaderDbHelper.wstawZamowienie(klawiatura, cena3+"", "1",
                                        HomeFragment2.nazwa.getText().toString(), data_teraz);
//                               HomeFragment2.nazwa.getText().toString()
                                czyZakupiono = true;
                                iloscZamowien++;
                            }
                            if(czyZakupiono){
                                Toast.makeText(getContext(), getResources().getString(R.string.Dziękujemy), Toast.LENGTH_SHORT).show();
                                smsManager = SmsManager.getDefault();

                                String destinationAddress = phone.getText().toString();

                                ArrayList<String> dane = feedReaderDbHelper.getShoppings(HomeFragment2.nazwa.getText().toString());

                                String text = "";


                                Toast.makeText(getContext(), getResources().getString(R.string.IloscZamowien)+iloscZamowien, Toast.LENGTH_SHORT).show();

                                for (int i = iloscZamowien; i > 0; i--) {
                                    text = getResources().getString(R.string.Witaj) +" "+ imie.getText() +", "+ getResources().getString(R.string.Dziekujemy_za_zakup) +
                                            dane.get(dane.size() - i);
                                    Log.i("TAG", text);
                                    smsManager.sendTextMessage(
                                            destinationAddress,
                                            null,
                                            text,
                                            null,
                                            null
                                    );
                                }
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment()).commit();
                            }
                        }
                        else{
                            Toast.makeText(getContext(), getResources().getString(R.string.Podaj_wymagane_dane), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getContext(), getResources().getString(R.string.Wybierz_coś), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 0);
                    Toast.makeText(getContext(), getResources().getString(R.string.Nie_masz_uprawnień), Toast.LENGTH_SHORT).show();
                }


            }
        });


        return viewGroup;
    }

    public void zmienCene(){
        if(spinner.getSelectedItemPosition()==0){
            Log.i("TAG", "wpisuje id 1 do komputerow");
            cena1 = feedReaderDbHelper.getCena(1, "komputery", "cena_komputerow");
        }
        else if(spinner.getSelectedItemPosition()==1){
            Log.i("TAG","wpisuje id 2 do komputerow");
            cena1 = feedReaderDbHelper.getCena(2, "komputery", "cena_komputerow");
        }
        else if(spinner.getSelectedItemPosition()==2){
            Log.i("TAG", "wpisuje id 3 do komputerow");
            cena1 = feedReaderDbHelper.getCena(3, "komputery", "cena_komputerow");
        }


        if(checkBox.isChecked()){
            if(spinner2.getSelectedItemPosition()==0){
                Log.i("TAG", "wpisuje id 1 do myszki");
                cena2 = feedReaderDbHelper.getCena(1, "myszki", "cena_myszek");
            }
            else if(spinner2.getSelectedItemPosition()==1){
                Log.i("TAG", "wpisuje id 2 do myszki");
                cena2 = feedReaderDbHelper.getCena(2, "myszki", "cena_myszek");
            }
            else if(spinner2.getSelectedItemPosition()==2){
                Log.i("TAG", "wpisuje id 3 do myszki");
                cena2 = feedReaderDbHelper.getCena(3, "myszki", "cena_myszek");
            }
        }else {
            cena2 = 0;
        }


        if(checkBox2.isChecked()){
            if(spinner3.getSelectedItemPosition()==0){
                Log.i("TAG","Wpisuje id 1 do klawiatury");
                cena3 = feedReaderDbHelper.getCena(1, "klawiatury", "cena_klawiatur");
            }
            else if(spinner3.getSelectedItemPosition()==1){
                Log.i("TAG","Wpisuje id 2 do klawiatury");
                cena3 = feedReaderDbHelper.getCena(2, "klawiatury", "cena_klawiatur");
            }
            else if(spinner3.getSelectedItemPosition()==2){
                Log.i("TAG","Wpisuje id 3 do klawiatury");
                cena3 = feedReaderDbHelper.getCena(3, "klawiatury", "cena_klawiatur");
            }
        }
        else {
            cena3 = 0;
        }
        cena.setText((cena1*Math.round(slider.getValue())+cena2+cena3)+" zł");
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        zmienCene();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}