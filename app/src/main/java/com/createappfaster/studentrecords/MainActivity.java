package com.createappfaster.studentrecords;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.createappfaster.dbhandler.datasource.StudentDataSource;
import com.createappfaster.model.Student;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends Activity {

    private   List<Student> studentList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentDataSource studentDataSource = new StudentDataSource(this);
        Student student = new Student();
        student.setName("Arpit");
        student.setRollNumber(1);
        student.setMarks(100);
        Student student1 = new Student();
        student1.setName("Shiv");
        student1.setRollNumber(2);
        student1.setMarks(001);

        try {
            studentDataSource.openConnection();
            studentDataSource.addStudent(student);
            studentDataSource.addStudent(student1);
            studentList =  studentDataSource.getAllStudents();

            for(Student students: studentList){
                Log.d("MainActivity", "---  "+ students + "  ---");
            }
            student1.setMarks(10);
            studentDataSource.updateStudentRecords(student1);
            studentList =  studentDataSource.getAllStudents();

            for(Student students: studentList){
                Log.d("MainActivity", "-after update--  "+ students + "  ---");
            }

            studentDataSource.deleteStudent(2);
            studentList =  studentDataSource.getAllStudents();

            for(Student students: studentList){
                Log.d("MainActivity", "-after delete--  "+ students + "  ---");
            }
         //   studentDataSource.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
