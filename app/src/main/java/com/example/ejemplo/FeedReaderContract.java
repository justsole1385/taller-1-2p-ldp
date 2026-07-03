package com.example.ejemplo;

import android.provider.BaseColumns;
public class FeedReaderContract {

    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String name_table = "persona";
        public static final String column1 = "nombre";
        public static final String column2 = "apellido";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.name_table + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FeedEntry.column1 + " TEXT, " +
                    FeedEntry.column2 + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.name_table;
}
