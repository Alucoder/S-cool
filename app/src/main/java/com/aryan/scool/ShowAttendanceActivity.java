package com.aryan.scool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAttendanceActivity extends AppCompatActivity {

    RecyclerView rvAttendanceResult;
    List<UserModel> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);
        rvAttendanceResult = findViewById(R.id.attendanceShowRV);
        getStudents();

    }

    public void getStudents() {
        UserAPI studentList = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<List<UserModel>> studentListCall = studentList.getStudentList();

        studentListCall.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                students = response.body();
                ShowAttendanceAdapter adapter = new ShowAttendanceAdapter(students);
                rvAttendanceResult.setAdapter(adapter);
                rvAttendanceResult.setLayoutManager(new LinearLayoutManager(ShowAttendanceActivity.this));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

    }
}
