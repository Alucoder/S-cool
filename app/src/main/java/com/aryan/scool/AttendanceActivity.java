package com.aryan.scool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity implements TakeAttendance {

    RecyclerView rvAttendance;
    List<UserModel> students;
    List<AttendanceModel> attendanceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        rvAttendance = findViewById(R.id.attendanceRV);
        getStudents();

    }

    public void getStudents() {
        UserAPI studentList = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<List<UserModel>> studentListCall = studentList.getStudentList();

        studentListCall.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                students = response.body();
                AttendanceAdapter adapter = new AttendanceAdapter(students, AttendanceActivity.this);
                rvAttendance.setAdapter(adapter);
                rvAttendance.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

    }


    @Override
    public void attendance(String id, String status) {
        if(status.equals("Present")){
            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", Date.valueOf("2077-10-20"), true));
        }
        else if(status.equals("Absent")){
            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", Date.valueOf("2077-10-20"), false));
        }
    }
}
