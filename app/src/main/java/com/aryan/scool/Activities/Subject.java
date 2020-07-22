package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.aryan.scool.Adapters.SubjectAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.SubjectAPI;
import com.aryan.scool.Models.SubjectModel;
import com.aryan.scool.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Subject extends AppCompatActivity {
    RecyclerView rvSubjects;
    StudentDashboard studentDashboard;
    List<SubjectModel> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        rvSubjects = findViewById(R.id.rv_subject_list);
        getMySubjects();

    }

    private void getMySubjects() {
        SubjectAPI subjectAPI = RetrofitUrl.getInstance().create(SubjectAPI.class);
        Call<List<SubjectModel>> listCallSubject = subjectAPI.getMySubject(RetrofitUrl.token, studentDashboard.user.getClassroom());

       listCallSubject.enqueue(new Callback<List<SubjectModel>>() {
           @Override
           public void onResponse(Call<List<SubjectModel>> call, Response<List<SubjectModel>> response) {
               if(!response.isSuccessful()){
                   Toast.makeText(Subject.this, "Error loading subjects!!", Toast.LENGTH_SHORT).show();
                   return;
               }
               subjects = response.body();
               SubjectAdapter subjectAdapter = new SubjectAdapter(subjects, Subject.this);
               rvSubjects.setAdapter(subjectAdapter);
               rvSubjects.setLayoutManager(new LinearLayoutManager(Subject.this));
           }

           @Override
           public void onFailure(Call<List<SubjectModel>> call, Throwable t) {
               Toast.makeText(Subject.this, "Error loading subjects!!", Toast.LENGTH_SHORT).show();
           }
       });
    }
}