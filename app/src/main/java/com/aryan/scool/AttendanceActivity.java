package com.aryan.scool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity implements TakeAttendance {

    RecyclerView rvAttendance;
    Button btnAttendanceDone;
    List<UserModel> students;
    int totalStudents = 0;
    public static ArrayList<AttendanceModel> attendanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        rvAttendance = findViewById(R.id.attendanceRV);
        btnAttendanceDone = findViewById(R.id.btnAttendanceDone);
        getStudents();
        attendanceList = new ArrayList<>();


        btnAttendanceDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeAttendance();
            }
        });

    }

    public void getStudents() {
        UserAPI studentList = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<List<UserModel>> studentListCall = studentList.getStudentList();

        studentListCall.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                students = response.body();
                totalStudents = students.size();
                AttendanceAdapter adapter = new AttendanceAdapter(students, AttendanceActivity.this);
                rvAttendance.setAdapter(adapter);
                rvAttendance.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void attendance(String id, String status) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        if(status.equals("Present")){
            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", Date.valueOf(dtf.format(now)), true));
        }
        else if(status.equals("Absent")){
            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", Date.valueOf(dtf.format(now)), false));
        }

    }

    public void completeAttendance(){
        if(attendanceList.size() != totalStudents){
            return;
        }
        else {
            for (int i = 0; i < attendanceList.size(); i++) {
                AttendanceAPI attendanceAPI = RetrofitUrl.getInstance().create(AttendanceAPI.class);
                Call<Void> attendanceCall = attendanceAPI.takeAttendance(RetrofitUrl.token, attendanceList.get(i));
                attendanceCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(AttendanceActivity.this, "Done Attendance", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        }
    }
}
