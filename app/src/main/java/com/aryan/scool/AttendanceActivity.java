package com.aryan.scool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    RecyclerView rvAttendance;
    List<UserModel> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        rvAttendance = findViewById(R.id.attendanceRV);
        getStudents();

        AttendanceAdapter adapter = new AttendanceAdapter(students);
        rvAttendance.setAdapter(adapter);
        rvAttendance.setLayoutManager(new LinearLayoutManager(this));

    }

    public void getStudents(){

    }
}
