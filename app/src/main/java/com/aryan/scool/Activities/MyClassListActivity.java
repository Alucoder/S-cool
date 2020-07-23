package com.aryan.scool.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Adapters.ClassroomAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.ClassroomAPI;
import com.aryan.scool.Models.ClassModel;
import com.aryan.scool.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyClassListActivity extends AppCompatActivity {
    RecyclerView rvClasses;
    DashboardActivity dashboardActivity;
    List<ClassModel> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_list);

        rvClasses = findViewById(R.id.rvClassList);
        getMyClasses();
    }

    private void getMyClasses() {
        ClassroomAPI classroomAPI = RetrofitUrl.getInstance().create(ClassroomAPI.class);
        Call<List<ClassModel>> listCallClass = classroomAPI.getMyClasses(RetrofitUrl.token, dashboardActivity.profile.get_id());

        listCallClass.enqueue(new Callback<List<ClassModel>>() {
            @Override
            public void onResponse(Call<List<ClassModel>> call, Response<List<ClassModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MyClassListActivity.this, "Error loading subjects!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                classes = response.body();
                ClassroomAdapter classroomAdapter = new ClassroomAdapter(classes, MyClassListActivity.this);
                rvClasses.setAdapter(classroomAdapter);
                rvClasses.setLayoutManager(new GridLayoutManager(MyClassListActivity.this, 3));
            }

            @Override
            public void onFailure(Call<List<ClassModel>> call, Throwable t) {
                Toast.makeText(MyClassListActivity.this, "Error loading subjects..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
