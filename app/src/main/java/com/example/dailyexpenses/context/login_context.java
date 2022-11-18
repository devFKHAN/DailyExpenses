package com.example.dailyexpenses.context;

import android.provider.BaseColumns;

public final class login_context {
    login_context(){

    }
    public static final class login_detail implements BaseColumns{
        public final static String TABLE_NAME="login_detail";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_NAME="Name";
        public final static String COLUMN_USERNAME="UserName";
        public final static String COLUMN_GENDER="gender";
        public final static String COLUMN_BUDGET="Budget";
        public final static String COLUMN_TIME="time";
        public final static String COLUMN_IMAGE_RESOURCE_ID="img";
    }

}
