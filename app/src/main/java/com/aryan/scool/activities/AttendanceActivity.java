package com.aryan.scool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aryan.scool.R;
import com.aryan.scool.model.UserModel;

import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    RecyclerView rvAttendance;
    List<UserModel> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        rvAttendance = findViewById(R.id.attendanceRV);
//        getStudents();
//
//        AttendanceAdapter adapter = new AttendanceAdapter(students);
//        rvAttendance.setAdapter(adapter);
//        rvAttendance.setLayoutManager(new LinearLayoutManager(this));

    }

    public void getStudents(){
//        AttendanceModel attendanceModel = new AttendanceModel();
//        AttendanceAPI attendanceAPI = RetrofitUrl.getInstance().create(AttendanceAPI.class);
//        Call<Void> attendanceCall = attendanceAPI.takeAttendance()
    }
}