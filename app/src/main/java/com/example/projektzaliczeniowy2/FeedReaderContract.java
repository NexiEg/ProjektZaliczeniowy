//package com.example.projektzaliczeniowy2;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.provider.BaseColumns;
//
//public final class FeedReaderContract {
//    public FeedReaderContract() {
//    }
//    public static class FeedEntry implements BaseColumns{
//        public static final String TABLE_NAME = "komputery";
//        public static final String TABLE_NAME_2 = "myszki";
//        public static final String TABLE_NAME_3 = "klawiatury";
//
//        public static final String COLUMN_NAME_TITLE = "nazwy_komputerow";
//        public static final String COLUMN_NAME_TITLE_CENA = "cena_komputerow";
//
//        public static final String COLUMN_NAME_TITLE_2 = "nazwy_myszek";
//        public static final String COLUMN_NAME_TITLE_CENA_2 = "cena_myszek";
//
//        public static final String COLUMN_NAME_TITLE_3 = "nazwy_klawiatur";
//        public static final String COLUMN_NAME_TITLE_CENA_3 = "cena_klawiatur";
//
//
//    }
//    private static final String SQL_CREATE_ENTRIES =
//            "CREATE TABLE " + FeedEntry.TABLE_NAME + " ("+
//                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                    FeedEntry.COLUMN_NAME_TITLE + " TEXT, "+FeedEntry.COLUMN_NAME_TITLE_CENA +" TEXT)";
//
//    private static final String SQL_CREATE_ENTRIES_2 =
//            "CREATE TABLE " + FeedEntry.TABLE_NAME_2 + " ("+
//                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                   FeedEntry.COLUMN_NAME_TITLE_2 + " TEXT, "+FeedEntry.COLUMN_NAME_TITLE_CENA_2 +" TEXT)";
//
//    private static final String SQL_CREATE_ENTRIES_3 =
//            "CREATE TABLE " + FeedEntry.TABLE_NAME_3 + " ("+
//                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                   FeedEntry.COLUMN_NAME_TITLE_3 + " TEXT, "+FeedEntry.COLUMN_NAME_TITLE_CENA_3 +" TEXT)";
//
//    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
//    private static final String SQL_DELETE_ENTRIES_2 = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_2;
//    private static final String SQL_DELETE_ENTRIES_3 = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_3;
//
//
//    public static class FeedReaderDbHelper extends SQLiteOpenHelper {
//        public static final int DATABASE_VERSION = 1;
//        public static final String DATABASE_NAME = "KomputeryDane.db";
//
//        public FeedReaderDbHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase sqLiteDatabase) {
//            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_2);
//            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
//            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_3);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//            sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_2);
//            sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
//            sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_3);
//            onCreate(sqLiteDatabase);
//        }
//    }
//}
