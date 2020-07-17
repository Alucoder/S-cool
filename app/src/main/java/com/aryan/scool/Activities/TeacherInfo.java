package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.aryan.scool.Adapters.AttendanceAdapter;
import com.aryan.scool.Adapters.TeacherInfoAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherInfo extends AppCompatActivity {

    RecyclerView rvTeacher;
    List<UserModel> teacehrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        rvTeacher = findViewById(R.id.rv_teachers_list);
        getTeachers();

    }

    public void getTeachers(){
        UserAPI teachersList = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<List<UserModel>> teacherListCall = teachersList.getStudentList();

        teacherListCall.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(TeacherInfo.this, "Error !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                teacehrs = response.body();
                TeacherInfoAdapter adapter = new TeacherInfoAdapter(teacehrs, TeacherInfo.this);
                rvTeacher.setAdapter(adapter);
                rvTeacher.setLayoutManager(new LinearLayoutManager(TeacherInfo.this));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(TeacherInfo.this , "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}