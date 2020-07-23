package com.aryan.scool.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    ImageView rvRecAchievements;
    TextView tvRecTitle, tvRecAchievementDesc;

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

        rvRecAchievements = findViewById(R.id.rvRecAchievements);
        tvRecTitle = findViewById(R.id.tvRecTitle);
        tvRecAchievementDesc = findViewById(R.id.tvRecAchievementDesc);

        getMyProfile();
        loadAchievements();
        refresh(1000);

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

                if(userModel.getAchievements() != null){
                    getAchievement(userModel);

                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    public void getAchievement(UserModel user){
        AchievementAPI achievementAPI = RetrofitUrl.getInstance().create(AchievementAPI.class);
        Call<Achievements> achievementCall = achievementAPI.getMyAchievements(RetrofitUrl.token, user.getAchievements());

        achievementCall.enqueue(new Callback<Achievements>() {
            @Override
            public void onResponse(Call<Achievements> call, Response<Achievements> response) {
                String imgPath = RetrofitUrl.imagePath + response.body().getBadge();
                Picasso.get().load(imgPath) .into(rvRecAchievements);
                tvRecTitle.setText(response.body().getAchievement());
                tvRecAchievementDesc.setText(response.body().getRemarks());
            }

            @Override
            public void onFailure(Call<Achievements> call, Throwable t) {

            }
        });
    }

//    public void loadStudentAchievements(){
//        AchievementAPI achievementAPI = RetrofitUrl.getInstance().create(AchievementAPI.class);
//        Call<List<Achievements>> achievementCall = achievementAPI.getMyAchievements(RetrofitUrl.token, stud_id);
//
//        achievementCall.enqueue(new Callback<List<Achievements>>() {
//            @Override
//            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
//                if(response.body() != null) {
//                    List<Achievements> achievements = response.body();
//                    AchievementAdapter adapter = new AchievementAdapter(achievements, stud_id);
//                    rvViewAllAchievements.setAdapter(adapter);
//                    rvViewAllAchievements.setLayoutManager(new GridLayoutManager(ViewStudentProfileActivity.this, 3));
//                }
//                else{
//                    tvNoAchievements.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Achievements>> call, Throwable t) {
//
//            }
//        });
//    }

    public void loadAchievements(){
        AchievementAPI achievementAPI = RetrofitUrl.getInstance().create(AchievementAPI.class);
        Call<List<Achievements>> achievementCall = achievementAPI.getTotalAchievements(RetrofitUrl.token);

        achievementCall.enqueue(new Callback<List<Achievements>>() {
            @Override
            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
                List<Achievements> achievements = response.body();
                AchievementAdapter adapter = new AchievementAdapter(achievements, stud_id);
                rvViewAllAchievements.setAdapter(adapter);
                rvViewAllAchievements.setLayoutManager(new GridLayoutManager(ViewStudentProfileActivity.this, 3));

            }

            @Override
            public void onFailure(Call<List<Achievements>> call, Throwable t) {

            }
        });
    }

    public void refresh(int milliseconds){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getMyProfile();
            }
        };
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        handler.postDelayed(runnable, milliseconds);
    }
}
