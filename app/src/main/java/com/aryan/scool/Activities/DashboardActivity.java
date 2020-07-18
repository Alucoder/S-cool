package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    public static UserModel profile;
    TextView txtDashName;
    Button btnNavigateAttendance, btnNavigateResult, btnNavigateStudents;
    RecyclerView rvNoticeDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnNavigateAttendance = findViewById(R.id.btnNavigateAttendance);
        btnNavigateResult = findViewById(R.id.btnNavigateResult);
        btnNavigateStudents = findViewById(R.id.btnNavigateStudents);
        txtDashName = findViewById(R.id.txtDashName);
        rvNoticeDash = findViewById(R.id.rvNotice);

        btnNavigateAttendance.setOnClickListener(this);
        btnNavigateResult.setOnClickListener(this);
        btnNavigateStudents.setOnClickListener(this);
        getProfile();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btnNavigateAttendance:
                intent = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(intent);
                break;
            case R.id.btnNavigateResult:
                intent = new Intent(DashboardActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
//            case R.id.btnNavigateStudents:
//                intent = new Intent(DashboardActivity.this, StudentsActivity.class);
//                startActivity(intent);
//                break;
        }
    }

    public void getProfile(){
        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<UserModel> usersCall = userAPI.getUserProfile(RetrofitUrl.token);

        usersCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Error loading profile!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                profile = response.body();
                setProfile(profile);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error loading profile...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setProfile(UserModel profile) {
        txtDashName.setText(profile.getName());
    }
}
