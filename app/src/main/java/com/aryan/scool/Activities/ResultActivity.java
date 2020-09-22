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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Adapters.ResultAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.ResultAPI;
import com.aryan.scool.Interfaces.TakeResult;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.ResultModel;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity implements TakeResult {
    Button btnResultDone, btnView;
    public static EditText etExamType, etFullMarks;
    RecyclerView rvResult;
    List<UserModel> students;
    int totalStudents = 0;
    public static ArrayList<ResultModel> resultList;
    String teacherResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        teacherResult = getIntent().getStringExtra("teacherResult");

        rvResult = findViewById(R.id.rvResult);
        btnResultDone = findViewById(R.id.btnResultDone);
        btnView = findViewById(R.id.btnResultView);
        etExamType = findViewById(R.id.etExamType);
        etFullMarks = findViewById(R.id.etFullMarks);
        getStudents();
        resultList = new ArrayList<>();


        btnResultDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFullMarks.requestFocus();
                completeResult();
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
        Call<List<UserModel>> studentListCall = studentList.getSelectedClassStudent(RetrofitUrl.token, teacherResult);

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

    @Override
    public void result(UserModel student, float marks) {
//        if(checkList(resultList, student))
        resultList.add(new ResultModel(etExamType.getText().toString(), "5eed8730275fa4e4f73b8e78", Integer.parseInt(etFullMarks.getText().toString()), 1, marks, student));
    }

//    public boolean checkList(List<ResultModel> lista, UserModel std){
//        if(lista != null){
//            if(lista.stream().map(std::getName).anyMatch(std.getName()::equals))
//        }
//    }

    public void completeResult(){
        if(resultList.size() != totalStudents){
            return;
        }
        else {
            for (int i = 0; i < resultList.size(); i++) {
                ResultAPI resultAPI = RetrofitUrl.getInstance().create(ResultAPI.class);
                Call<Void> resultCall = resultAPI.postResult(RetrofitUrl.token, resultList.get(i));

                resultCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(ResultActivity.this, "Done Result", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        }
    }
}

