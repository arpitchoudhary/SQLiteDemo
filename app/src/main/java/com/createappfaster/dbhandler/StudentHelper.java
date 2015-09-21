package com.createappfaster.dbhandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.createappfaster.dbcontract.StudentContract;


public class StudentHelper extends SQLiteOpenHelper {

    private final String TAG = StudentHelper.class.getSimpleName();

    public StudentHelper(Context context){
        super(context, StudentContract.DATABASE_NAME,null, StudentContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(StudentContract.StudentTable.CREATE_TABLE);
        Log.d(TAG,StudentContract.StudentTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(StudentContract.StudentTable.DELETE_TABLE);
        onCreate(sqLiteDatabase);
        Log.d(TAG, StudentContract.StudentTable.DELETE_TABLE);
    }
}