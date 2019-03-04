package com.example.helthyme;

import android.provider.BaseColumns;

public final class Data {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Data(){}

    /* Inner class that defines the table contents */
    public static class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_COUNT = "count";
    }


}
