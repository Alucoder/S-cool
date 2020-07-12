package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aryan.scool.Adapters.ResultAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.ResultModel;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    Button btnResultDone, btnView;
    EditText etExamType;
    RecyclerView rvResult;
    List<UserModel> students;
    int totalStudents = 0;
    public static ArrayList<ResultModel> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        rvResult = findViewById(R.id.rvResult);
        btnResultDone = findViewById(R.id.btnResultDone);
        btnView = findViewById(R.id.btnResultView);
        getStudents();
        resultList = new ArrayList<>();


        btnResultDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                completeResult();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ShowAttendanceActivity.class);
                startActivity(intent);
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
                ResultAdapter adapter = new ResultAdapter(students);
                rvResult.setAdapter(adapter);
                rvResult.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

    }
}
