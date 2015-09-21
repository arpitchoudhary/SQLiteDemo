package com.createappfaster.dbcontract;

import android.provider.BaseColumns;

public class StudentContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "student.db";
    public static final String TEXT_TYPE = " TEXT ";
    public static final String INTEGER_TYPE = " INTEGER ";
    public static final String COMMA_SEP = " , ";

    private StudentContract(){}

    public static class StudentTable implements BaseColumns{
        public static final String TABLE_NAME       = "Student";
        public static final String COLUMN_ROLL_NO = "ROLLNUMBER";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_MARKS = "MARKS";

        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP +
                COLUMN_ROLL_NO + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_MARKS + INTEGER_TYPE + " );";


        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " +TABLE_NAME;

    }

}