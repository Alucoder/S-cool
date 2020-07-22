package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashboard extends AppCompatActivity implements View.OnClickListener {
    CardView cv_sd_subject, cv_stdDash_teacher;
    ImageView imgProfile;
    TextView txtDashName;
    public static UserModel user;
    ImageView iv;

    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        cv_sd_subject = findViewById(R.id.cv_sd_subject);
        cv_stdDash_teacher = findViewById(R.id.cv_stdDash_teacher);
        iv = findViewById(R.id.logout);
        txtDashName = findViewById(R.id.txtDashName);
        imgProfile = findViewById(R.id.img_dashboard_student_profile);

        getProfile();
        imgProfile.setOnClickListener(this);
        cv_sd_subject.setOnClickListener(this);
        cv_stdDash_teacher.setOnClickListener(this);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.img_dashboard_student_profile:
                Intent intent = new Intent(StudentDashboard.this, StudentProfileActivity.class);
                intent.putExtra("studentid", user.get_id());
                startActivity(intent);
                break;
            case R.id.cv_sd_subject:
                startActivity(new Intent(StudentDashboard.this, Subject.class));
                break;
            case R.id.cv_stdDash_teacher:
                startActivity(new Intent(StudentDashboard.this, TeacherInfo.class));
                break;
            case R.id.logout:
                deleteSavedUser();
                RetrofitUrl.token = "Bearer ";
                startActivity(new Intent(StudentDashboard.this, LoginActivity.class));
                finish();
        }
    }

    public void getProfile(){
        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<UserModel> usersCall = userAPI.getUserProfile(RetrofitUrl.token);

        usersCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(StudentDashboard.this, "Error loading profile!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                user = response.body();
                String imgPath = RetrofitUrl.imagePath + response.body().getProfile();
                Picasso.get().load(imgPath).into(imgProfile);
                txtDashName.setText(user.getName());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(StudentDashboard.this, "Error loading profile...", Toast.LENGTH_SHORT).show();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, StudentProfileActivity.class));
            }
        });
    }

    public void deleteSavedUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("Scool", 0);
        sharedPreferences.edit().clear().commit();
    }


}