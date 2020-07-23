package com.aryan.scool.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Adapters.AchievementAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.AchievementAPI;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.Achievements;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStudentProfileActivity extends AppCompatActivity {

    ImageView image;
    TextView userName, userID, userEmail, userPhone, tvNoAchievements;
    String stud_id;
    RecyclerView rvViewAllAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_profile);

        Intent intent = getIntent();
        stud_id = intent.getStringExtra("viewStudentProfile");
        userName = findViewById(R.id.tvViewStudentName);
        userID = findViewById(R.id.tvViewStudentId);
        userEmail = findViewById(R.id.tvViewStudentEmail);
        userPhone = findViewById(R.id.tvViewStudentPhoneNumber);
        image = findViewById(R.id.imgViewStudProfile);
        tvNoAchievements = findViewById(R.id.tvNoAchievements);
        rvViewAllAchievements = findViewById(R.id.rvAchievements);

        getMyProfile();
        loadStudentAchievements();
        loadAchievements();

    }

    public void getMyProfile() {
        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        final Call<UserModel> userModelCall = userAPI.getSelectedStudent(RetrofitUrl.token, stud_id);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                userName.setText(userModel.getFname());
                userID.setText(userModel.getUserid());
                userEmail.setText(userModel .getEmail());
                userPhone.setText("" + userModel.getPhone());

                String imgPath = RetrofitUrl.imagePath + response.body().getProfile();

                try{
                    Picasso.get().load(imgPath) .into(image);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.usericon).into(image);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    public void loadStudentAchievements(){
        AchievementAPI achievementAPI = RetrofitUrl.getInstance().create(AchievementAPI.class);
        Call<List<Achievements>> achievementCall = achievementAPI.getMyAchievements(RetrofitUrl.token, stud_id);

        achievementCall.enqueue(new Callback<List<Achievements>>() {
            @Override
            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
                if(response.body() != null) {
                    List<Achievements> achievements = response.body();
                    AchievementAdapter adapter = new AchievementAdapter(achievements);
                    rvViewAllAchievements.setAdapter(adapter);
                    rvViewAllAchievements.setLayoutManager(new GridLayoutManager(ViewStudentProfileActivity.this, 3));
                }
                else{
                    tvNoAchievements.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Achievements>> call, Throwable t) {

            }
        });
    }

    public void loadAchievements(){
        AchievementAPI achievementAPI = RetrofitUrl.getInstance().create(AchievementAPI.class);
        Call<List<Achievements>> achievementCall = achievementAPI.getTotalAchievements(RetrofitUrl.token);

        achievementCall.enqueue(new Callback<List<Achievements>>() {
            @Override
            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
                List<Achievements> achievements = response.body();
                AchievementAdapter adapter = new AchievementAdapter(achievements);
                rvViewAllAchievements.setAdapter(adapter);
                rvViewAllAchievements.setLayoutManager(new GridLayoutManager(ViewStudentProfileActivity.this, 3));

            }

            @Override
            public void onFailure(Call<List<Achievements>> call, Throwable t) {

            }
        });
    }
}