package com.aryan.scool.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Adapters.StudentListAdapter;
import com.aryan.scool.Adapters.TeacherInfoAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity {

    RecyclerView rvStudents;
    List<UserModel> studentsList;
    String teacherStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        rvStudents = findViewById(R.id.rv_students_list);
        teacherStudent = getIntent().getStringExtra("teacherStudent");

        getStudents();
    }

    public void getStudents(){
        UserAPI students = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<List<UserModel>> studentsCall = students.getSelectedClassStudent(RetrofitUrl.token, teacherStudent);

        studentsCall.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StudentListActivity.this, "Error !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                studentsList = response.body();
                StudentListAdapter adapter = new StudentListAdapter(studentsList, StudentListActivity.this);
                rvStudents.setAdapter(adapter);
                rvStudents.setLayoutManager(new LinearLayoutManager(StudentListActivity.this));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(StudentListActivity.this , "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
