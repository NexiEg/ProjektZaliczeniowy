package com.example.projektzaliczeniowy2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class HomeFragment4 extends Fragment {
    ViewGroup viewGroup;
    ImageButton imageButton;
    ArrayList<String> dane = new ArrayList<>();
    ArrayList<String> daneID = new ArrayList<>();
    Integer id;
    TextView zamowienie;
    TextView cenaZaZamowienie;
    TextView iloscZamowienia;
    TextView dataZamowienia;
    TextView uzytkownik;

    ImageView imageView;

    Button share;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home4_fragment,container,false);

//        if(HomeFragment2.sharedPreferences.getBoolean("zalogowany", false)) {
//            HomeFragment2.toolbar.setVisibility(View.VISIBLE);
//        }

        share = viewGroup.findViewById(R.id.share);

        if (!HomeFragment3.czyPrzenosic2){
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment3()).commit();
        }
        else {
            daneID = HomeFragment3.daneID;
            id = HomeFragment3.ID;
            if(daneID.size()>0){
                FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(getContext());
                dane = feedReaderDbHelper.getShoppings2(HomeFragment2.nazwa.getText().toString(), daneID.get(id));
            }
            else {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment3()).commit();
            }


            if(dane.size()>0){
                //        dane = HomeFragment3.dane;

                imageView = viewGroup.findViewById(R.id.zdjecieZakupu);


                //        Log.i("TAG", "DANE: " + dane+ "DANEID: " + daneID + "ID: " + id);


                Log.i("TAG", "DANE: " + dane + "DANEID: " + daneID + "ID: " + id);


                zamowienie = viewGroup.findViewById(R.id.zamowienie);
                cenaZaZamowienie = viewGroup.findViewById(R.id.cenaZaZamowienie);
                iloscZamowienia = viewGroup.findViewById(R.id.iloscZamowienia);
                dataZamowienia = viewGroup.findViewById(R.id.dataZamowienia);
                uzytkownik = viewGroup.findViewById(R.id.uzytkownik);

                zamowienie.setText(dane.get(0));
                cenaZaZamowienie.setText(dane.get(1));
                iloscZamowienia.setText(dane.get(2));
                dataZamowienia.setText(dane.get(4));
                uzytkownik.setText(dane.get(3));

                ustawZdjecie();

                imageButton = viewGroup.findViewById(R.id.imagebutton);

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment3()).commit();
                    }
                });
            }
            else {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fragment_container, new HomeFragment3()).commit();
            }
        }


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, dane.get(0) + "\n" + dane.get(1) + "\n" + dane.get(2) + "\n" + dane.get(3) + "\n" + dane.get(4));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share to:"));
            }
        });



        return viewGroup;
    }

    private void ustawZdjecie() {
//        String[] dane2 = {"Intel Core i5 12400 16GB DDR4 HDD 1TB GTX 1050 Ti 4GB, cena 3024zl",
//                "Intel Core i7 11700 16GB DDR4 SSD 500GB M.2 + GTX 1660 6GB, cena 5032zl",
//                "RYZEN 9 3900X 32GB DDR4 SSD 500GB M.2 GTX 2060 6GB, cena 7764zł"};
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(getContext());
        ArrayList<String> dane2 = feedReaderDbHelper.show(FeedReaderDbHelper.FeedEntry.TABLE_NAME, FeedReaderDbHelper.FeedEntry.COLUMN_NAME_TITLE);
        ArrayList<String> dane3 = feedReaderDbHelper.show(FeedReaderDbHelper.FeedEntry.TABLE_NAME_2, FeedReaderDbHelper.FeedEntry.COLUMN_NAME_TITLE_2);
        ArrayList<String> dane4 = feedReaderDbHelper.show(FeedReaderDbHelper.FeedEntry.TABLE_NAME_3, FeedReaderDbHelper.FeedEntry.COLUMN_NAME_TITLE_3);


        Log.i("TAG", "DANE z dane: " + dane.get(0));

        if (dane.get(0).equals("Zakupiono: "+dane2.get(0))) {
            imageView.setImageResource(R.drawable.komputer1);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane2.get(1))) {
            imageView.setImageResource(R.drawable.komputer2);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane2.get(2))) {
            imageView.setImageResource(R.drawable.komputer3);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane3.get(0))) {
            imageView.setImageResource(R.drawable.mysz1);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane3.get(1))) {
            imageView.setImageResource(R.drawable.mysz2);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane3.get(2))) {
            imageView.setImageResource(R.drawable.mysz3);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane4.get(0))) {
            imageView.setImageResource(R.drawable.klawiatura1);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane4.get(1))) {
            imageView.setImageResource(R.drawable.klawiatura2);
        }
        else if (dane.get(0).equals("Zakupiono: "+dane4.get(2))) {
            imageView.setImageResource(R.drawable.klawiatura3);
        }
        else {
            imageView.setImageResource(R.drawable.gradient);
        }

    }
}