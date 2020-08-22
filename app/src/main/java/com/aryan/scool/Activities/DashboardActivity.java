package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Adapters.ClassroomAdapter;
import com.aryan.scool.Adapters.NoticeAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.ClassroomAPI;
import com.aryan.scool.Interfaces.NoticeAPI;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.ClassModel;
import com.aryan.scool.Models.NoticeModel;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    public static UserModel profile;
    TextView txtDashName;
    RecyclerView rvNoticeDash;
    ImageView img, userimage;

    RecyclerView rvClasses;
    List<ClassModel> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtDashName = findViewById(R.id.txtDashName);
        userimage = findViewById(R.id.userimage);
        rvNoticeDash = findViewById(R.id.rvNoticeDash);

        rvClasses = findViewById(R.id.rvClassList);

        img = findViewById(R.id.settingD);
        img.setOnClickListener(this);
        getProfile();
        getNotices();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.settingD:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
//                deleteSavedUser();
//                RetrofitUrl.token = "Bearer ";
//                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
//                finish();

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
                getMyClasses(profile);

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error loading profile...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void setProfile(UserModel profile) {
        txtDashName.setText(profile.getName());
        Mode();
        if(profile.getProfile() != null) {
            String imagePathProfile = RetrofitUrl.imagePath + profile.getProfile();
            Picasso.get().load(imagePathProfile).into(userimage);
        }
    }

    public void getNotices(){
        NoticeAPI noticeAPI = RetrofitUrl.getInstance().create(NoticeAPI.class);
        Call<List<NoticeModel>> noticeModelCall = noticeAPI.getNotice();

        noticeModelCall.enqueue(new Callback<List<NoticeModel>>() {
            @Override
            public void onResponse(Call<List<NoticeModel>> call, Response<List<NoticeModel>> response) {
                if(response.body() != null) {
                    List<NoticeModel> notices = response.body();
                    NoticeAdapter adapter = new NoticeAdapter(notices);
                    rvNoticeDash.setAdapter(adapter);
                    rvNoticeDash.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));

                }
                else{
                    Toast.makeText(DashboardActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<NoticeModel>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "error:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteSavedUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("Scool", 0);
        sharedPreferences.edit().clear().commit();
    }

    private void getMyClasses(UserModel Userprofile) {
        ClassroomAPI classroomAPI = RetrofitUrl.getInstance().create(ClassroomAPI.class);
        Call<List<ClassModel>> listCallClass = classroomAPI.getMyClasses(RetrofitUrl.token, Userprofile.get_id());

        listCallClass.enqueue(new Callback<List<ClassModel>>() {
            @Override
            public void onResponse(Call<List<ClassModel>> call, Response<List<ClassModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Error loading subjects!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                classes = response.body();
                ClassroomAdapter classroomAdapter = new ClassroomAdapter(classes, DashboardActivity.this);
                rvClasses.setAdapter(classroomAdapter);
                rvClasses.setLayoutManager(new GridLayoutManager(DashboardActivity.this, 3));
            }

            @Override
            public void onFailure(Call<List<ClassModel>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error loading subjects..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
