package com.example.projektzaliczeniowy2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KomputeryDane.db";

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "komputery";
        public static final String TABLE_NAME_2 = "myszki";
        public static final String TABLE_NAME_3 = "klawiatury";

        public static final String COLUMN_NAME_TITLE = "nazwy_komputerow";
        public static final String COLUMN_NAME_TITLE_CENA = "cena_komputerow";

        public static final String COLUMN_NAME_TITLE_2 = "nazwy_myszek";
        public static final String COLUMN_NAME_TITLE_CENA_2 = "cena_myszek";

        public static final String COLUMN_NAME_TITLE_3 = "nazwy_klawiatur";
        public static final String COLUMN_NAME_TITLE_CENA_3 = "cena_klawiatur";



        public static final String TABLE_NAME_4 = "klienci";
        public static final String COLUMN_NAME_TITLE_4 = "login";
        public static final String COLUMN_NAME_TITLE_5 = "haslo";
        public static final String COLUMN_NAME_TITLE_11 = "email";


        public static final String TABLE_NAME_5 = "koszyk";
        public static final String COLUMN_NAME_TITLE_6 = "nazwa";
        public static final String COLUMN_NAME_TITLE_7 = "cena";
        public static final String COLUMN_NAME_TITLE_8 = "ilosc";
        public static final String COLUMN_NAME_TITLE_9 = "id_klienta";
        public static final String COLUMN_NAME_TITLE_10 = "data_zamowienia";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " ("+
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + " TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_CENA +" TEXT)";

    private static final String SQL_CREATE_ENTRIES_2 =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_2 + " ("+
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE_2 + " TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_CENA_2 +" TEXT)";

    private static final String SQL_CREATE_ENTRIES_3 =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_3 + " ("+
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE_3 + " TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_CENA_3 +" TEXT)";

//    private static final String SQL_CREATE_ENTRIES_4 =
//            "CREATE TABLE " + FeedEntry.TABLE_NAME_4 + " ("+
//                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                    FeedEntry.COLUMN_NAME_TITLE_4 + " TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_5 +" TEXT)";
    private static final String SQL_CREATE_ENTRIES_4 =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_4 + " ("+
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE_4 + " TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_5 +" TEXT, "
                    + FeedEntry.COLUMN_NAME_TITLE_11 +" TEXT)";

    private static final String SQL_CREATE_ENTRIES_5 =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_5 + " ("+
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE_6 + " TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_7 +" TEXT, "
                    + FeedEntry.COLUMN_NAME_TITLE_8 +" TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_9 +" TEXT, "+ FeedEntry.COLUMN_NAME_TITLE_10 +" TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_2 = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_2;
    private static final String SQL_DELETE_ENTRIES_3 = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_3;
    private static final String SQL_DELETE_ENTRIES_4 = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_4;
    private static final String SQL_DELETE_ENTRIES_5 = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_5;

    private static final String SQL_COUNT_ENTRIES = "SELECT COUNT(*) FROM " + FeedEntry.TABLE_NAME;
    private static final String SQL_COUNT_ENTRIES_2 = "SELECT COUNT(*) FROM " + FeedEntry.TABLE_NAME_2;
    private static final String SQL_COUNT_ENTRIES_3 = "SELECT COUNT(*) FROM " + FeedEntry.TABLE_NAME_3;
    private static final String SQL_COUNT_ENTRIES_4 = "SELECT COUNT(*) FROM " + FeedEntry.TABLE_NAME_4;
    private static final String SQL_COUNT_ENTRIES_5 = "SELECT COUNT(*) FROM " + FeedEntry.TABLE_NAME_5;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Integer getCena(int id, String table, String column) {
        Integer dane = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE " + "_ID = " + id, null);
        Log.i("TAG", "odczytuje z tabeli: " + table + " kolumna: " + column + " id: " + id);
//        Cursor cursor = db.rawQuery("SELECT _ID FROM " + table, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                dane = cursor.getInt(0);
                Log.i("TAG", dane + "");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dane;
    }

//    public Integer countEntries(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(SQL_COUNT_ENTRIES, null);
//        cursor.moveToFirst();
//        int count = cursor.getInt(0);
//        cursor.close();
//        Log.i("TAG", "Ilosc danych w tabeli: "+count);
//
//        Cursor cursor2 = db.rawQuery(SQL_COUNT_ENTRIES_2, null);
//        cursor2.moveToFirst();
//        int count2 = cursor2.getInt(0);
//        cursor2.close();
//        Log.i("TAG", "Ilosc danych w tabeli: "+count2);
//
//        Cursor cursor3 = db.rawQuery(SQL_COUNT_ENTRIES_3, null);
//        cursor3.moveToFirst();
//        int count3 = cursor3.getInt(0);
//        cursor3.close();
//        Log.i("TAG", "Ilosc danych w tabeli: "+count3);
//        return count + count2 + count3;
//    }

    public void wstawZamowienie(String nazwa, String cena, String ilosc, String id_klienta, String data_zamowienia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE_6, nazwa);
        values.put(FeedEntry.COLUMN_NAME_TITLE_7, cena);
        values.put(FeedEntry.COLUMN_NAME_TITLE_8, ilosc);
        values.put(FeedEntry.COLUMN_NAME_TITLE_9, id_klienta);
        values.put(FeedEntry.COLUMN_NAME_TITLE_10, data_zamowienia);
        db.insert(FeedEntry.TABLE_NAME_5, null, values);
        db.close();
    }

    public void deleteShoppings(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME_5, FeedEntry.COLUMN_NAME_TITLE_9 + " = ?", new String[]{id});
        db.close();
    }

    public ArrayList<String> getShoppings(String id){
        ArrayList<String> dane = new ArrayList<>();
//        String a = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_5 + " WHERE " + FeedEntry.COLUMN_NAME_TITLE_9 + " = " + "'"+id+"'", null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
//                a+=cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " ";'
//                dane.add("Dane 0: "+cursor.getString(0));
                dane.add(cursor.getString(1));
//                dane.add("Cena: "+cursor.getString(2)+" zł");
//                dane.add("Ilosc: "+cursor.getString(3));
//                dane.add("Uzytkownik: "+cursor.getString(4));
//                dane.add("Data: "+cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
//        Log.i("TAG", a);
        return dane;
    }

    public ArrayList<String> getShoppingsID(String id){
        ArrayList<String> dane = new ArrayList<>();
//        String a = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_5 + " WHERE " + FeedEntry.COLUMN_NAME_TITLE_9 + " = " + "'"+id+"'", null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
//                a+=cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " ";'
                dane.add(cursor.getString(0));
//                dane.add(cursor.getString(1));
//                dane.add("Cena: "+cursor.getString(2)+" zł");
//                dane.add("Ilosc: "+cursor.getString(3));
//                dane.add("Uzytkownik: "+cursor.getString(4));
//                dane.add("Data: "+cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
//        Log.i("TAG", a);
        return dane;
    }

    public ArrayList<String> getShoppings2(String id, String id2){
        ArrayList<String> dane = new ArrayList<>();
//        String a = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_5 + " WHERE " + FeedEntry.COLUMN_NAME_TITLE_9 + " = " + "'"+id+"'" + " AND " + FeedEntry._ID + " = " + "'"+id2+"'", null);
//        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_5 + " WHERE " + FeedEntry.COLUMN_NAME_TITLE_9 + " = " + "'"+id+"'", null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
//                a+=cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " ";'
//                dane.add(cursor.getString(0));
                dane.add("Zakupiono: "+cursor.getString(1));
                dane.add("Cena: "+cursor.getString(2)+" zł");
                dane.add("Ilosc: "+cursor.getString(3));
                dane.add("Uzytkownik: "+cursor.getString(4));
                dane.add("Data: "+cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
//        Log.i("TAG", a);
        return dane;
    }


    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME, 1 + "=" + 1, null);
        db.delete(FeedEntry.TABLE_NAME_2, 1 + "=" + 1, null);
        db.delete(FeedEntry.TABLE_NAME_3, 1 + "=" + 1, null);
    }

    public void dodajKlienta(String login, String haslo, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE_4, login);
        values.put(FeedEntry.COLUMN_NAME_TITLE_5, haslo);
        values.put(FeedEntry.COLUMN_NAME_TITLE_11, email);
        db.insert(FeedEntry.TABLE_NAME_4, null, values);
        db.close();
    }

    public boolean sprawdzCzyKlientIstnieje(String login, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_4 + " WHERE " +
                FeedEntry.COLUMN_NAME_TITLE_4 + " = '" + login + "' OR " + FeedEntry.COLUMN_NAME_TITLE_11 + " = '" + email + "'", null);
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public String getKlientLogin(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_4 + " WHERE " +
                FeedEntry.COLUMN_NAME_TITLE_11 + " = '" + email + "'", null);
        cursor.moveToFirst();
        String login = cursor.getString(1);
        return login;
    }

    public boolean sprawdzCzyPoprawneDane(String login, String haslo, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME_4 + " WHERE " +
                FeedEntry.COLUMN_NAME_TITLE_4 + " = '" + login + "' AND " + FeedEntry.COLUMN_NAME_TITLE_5 + " = '" + haslo + "' OR "+
                FeedEntry.COLUMN_NAME_TITLE_11+" = '" + email + "' AND "+ FeedEntry.COLUMN_NAME_TITLE_5 + " = '" + haslo+"'", null);
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void insertData(){
        String[] dane = {"Intel Core i5 12400 16GB DDR4 HDD 1TB GTX 1050 Ti 4GB, cena 3024zl",
                "Intel Core i7 11700 16GB DDR4 SSD 500GB M.2 + GTX 1660 6GB, cena 5032zl",
                "RYZEN 9 3900X 32GB DDR4 SSD 500GB M.2 GTX 2060 6GB, cena 7764zł"};
        Integer[] cena = {3024, 5032, 7764};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < dane.length; i++) {
            values.put(FeedEntry.COLUMN_NAME_TITLE_CENA, cena[i]);
            values.put(FeedEntry.COLUMN_NAME_TITLE, dane[i]);
            db.insert(FeedEntry.TABLE_NAME, null, values);
        }

        String[] dane2 = {"Logitech G102 Prodigy, cena 99zl",
                "Logitech G203 Prodigy, cena 99zl",
                "Logitech G305 Lightspeed, cena 199zl"};
        Integer[] cena2 = {99, 99, 199};
        ContentValues values2 = new ContentValues();
        for (int i = 0; i < dane2.length; i++) {
            values2.put(FeedEntry.COLUMN_NAME_TITLE_CENA_2, cena2[i]);
            values2.put(FeedEntry.COLUMN_NAME_TITLE_2, dane2[i]);
            db.insert(FeedEntry.TABLE_NAME_2, null, values2);
        }

        // dane 3 z klawiaturami
        String[] dane3 = {"Logitech G213 Prodigy, cena 199zl",
                "Logitech G512 Carbon, cena 399zl",
                "Logitech G915 Lightspeed, cena 999zl"};
        Integer[] cena3 = {199, 399, 999};
        ContentValues values3 = new ContentValues();
        for (int i = 0; i < dane3.length; i++) {
            values3.put(FeedEntry.COLUMN_NAME_TITLE_CENA_3, cena3[i]);
            values3.put(FeedEntry.COLUMN_NAME_TITLE_3, dane3[i]);
            db.insert(FeedEntry.TABLE_NAME_3, null, values3);
        }
    }

    public ArrayList<String> show(String table, String column){
        ArrayList<String> items = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select " + column + " from " + table;
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do {
                String dane = c.getString(0);
                Log.i("TAG",dane+"");
                items.add(dane);
            } while(c.moveToNext());
        }
        c.close();
        db.close();

        return items;
    }

//    public ArrayList<String> show_myszki(){
//        ArrayList<String> items = new ArrayList<String>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "Select " + FeedEntry.COLUMN_NAME_TITLE_2 + " from " + FeedEntry.TABLE_NAME_2;
//        Cursor c = db.rawQuery(query, null);
//
//        if (c.moveToFirst()) {
//            do {
//                String imie = c.getString(0);
//                items.add(imie);
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//
//        return items;
//    }
//
//public ArrayList<String> show_klawiatury(){
//        ArrayList<String> items = new ArrayList<String>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "Select " + FeedEntry.COLUMN_NAME_TITLE_3 + " from " + FeedEntry.TABLE_NAME_3;
//        Cursor c = db.rawQuery(query, null);
//
//        if (c.moveToFirst()) {
//            do {
//                String imie = c.getString(0);
//                items.add(imie);
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//
//        return items;
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_2);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_3);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_4);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_2);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_3);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_4);
        onCreate(sqLiteDatabase);
    }
}

