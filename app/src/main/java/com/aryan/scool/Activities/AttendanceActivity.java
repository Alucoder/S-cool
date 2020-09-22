package com.aryan.scool.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aryan.scool.Interfaces.AttendanceAPI;
import com.aryan.scool.Adapters.AttendanceAdapter;
import com.aryan.scool.Models.AttendanceModel;
import com.aryan.scool.R;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.TakeAttendance;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity implements TakeAttendance {

    Button btnAttendanceDone, btnView;
    RecyclerView rvAttendance;
    List<UserModel> students;
    int totalStudents = 0;
    public static ArrayList<AttendanceModel> attendanceList;
    String teacherAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        teacherAttendance = getIntent().getStringExtra("teacherAttendance");

        rvAttendance = findViewById(R.id.attendanceRV);
        btnAttendanceDone = findViewById(R.id.btnAttendanceDone);
        btnView = findViewById(R.id.btnAttendanceView);
        getStudents();
        attendanceList = new ArrayList<>();


        btnAttendanceDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeAttendance();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttendanceActivity.this, ShowAttendanceActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getStudents() {
        UserAPI studentList = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<List<UserModel>> studentListCall = studentList.getSelectedClassStudent(RetrofitUrl.token, teacherAttendance);

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
//            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", Date.valueOf(dtf.format(now)), true));
            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", dtf.format(now), true));
        }
        else if(status.equals("Absent")){
//            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", Date.valueOf(dtf.format(now)), false));
            attendanceList.add(new AttendanceModel(id, "5eed9d7fe368a57f6471b45b", "5eed8730275fa4e4f73b8e78", dtf.format(now), false));
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
