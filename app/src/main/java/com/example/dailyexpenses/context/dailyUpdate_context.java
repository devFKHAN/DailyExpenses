package com.example.dailyexpenses.context;

import android.provider.BaseColumns;

public class dailyUpdate_context {
    public static final class dailyUpdate implements BaseColumns {
        public final static String TABLE_NAME="dailyUpdate";
        public final static String _ID1=BaseColumns._ID;
        public static final String COLUMN_DATE="Date";
        public final static String COLUMN_CATEGORIES="Categories";
        public final static String COLUMN_DESCRIPTION="Description";
        public final static String COLUMN_PRICE="Price";
        public final static String COLUMN_DAY="Day";
        public final static String COLUMN_MONTH="Month";
        public final static String COLUMN_YEAR="Year";
    }
}
