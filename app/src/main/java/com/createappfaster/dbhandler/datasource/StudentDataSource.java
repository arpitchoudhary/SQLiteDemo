package com.createappfaster.dbhandler.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.createappfaster.dbcontract.StudentContract;
import com.createappfaster.dbhandler.StudentHelper;
import com.createappfaster.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StudentDataSource {

    private SQLiteDatabase sqLiteDatabase;
    private StudentHelper studentHelper = null;

   public StudentDataSource(Context context){
       studentHelper = new StudentHelper(context);
   }

    public void openConnection() throws SQLException {
        sqLiteDatabase = studentHelper.getWritableDatabase();
    }

    public void closeConnection() throws SQLException {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public void addStudent(Student student) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentContract.StudentTable.COLUMN_ROLL_NO, student.getRollNumber());
        contentValues.put(StudentContract.StudentTable.COLUMN_NAME, student.getName());
        contentValues.put(StudentContract.StudentTable.COLUMN_MARKS, student.getMarks());
        sqLiteDatabase.insertOrThrow(StudentContract.StudentTable.TABLE_NAME, null, contentValues);
    }

    public void deleteStudent(final int studentId) throws SQLException {
        sqLiteDatabase.delete(StudentContract.StudentTable.TABLE_NAME, StudentContract.StudentTable._ID + " = " + studentId, null);
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<Student>();

        String getAllStudentQuery = "Select * from " + StudentContract.StudentTable.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(getAllStudentQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentId(cursor.getInt(cursor.getColumnIndex(StudentContract.StudentTable._ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(StudentContract.StudentTable.COLUMN_NAME)));
                student.setMarks(cursor.getInt(cursor.getColumnIndex(StudentContract.StudentTable.COLUMN_MARKS)));
                student.setRollNumber(cursor.getInt(cursor.getColumnIndex(StudentContract.StudentTable.COLUMN_ROLL_NO)));
                students.add(student);
            } while (cursor.moveToNext());
        }
        return students;
    }

    public void updateStudentRecords(Student student) throws SQLException {
        ContentValues contentValues = new ContentValues();
        int studentId = student.getStudentId();
        int marks = student.getMarks();
        contentValues.put(StudentContract.StudentTable.COLUMN_MARKS, marks);
        sqLiteDatabase.update(StudentContract.StudentTable.TABLE_NAME, contentValues, StudentContract.StudentTable._ID + " = " + studentId, null);
    }
}
