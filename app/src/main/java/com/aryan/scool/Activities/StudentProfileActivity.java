package com.aryan.scool.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.AchievementAPI;
import com.aryan.scool.Interfaces.AttendanceAPI;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.Achievements;
import com.aryan.scool.Models.AttendanceModel;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class StudentProfileActivity extends AppCompatActivity {

    ImageView profile, rvRecAchievementsImg, settings;
    String userid;
    CompactCalendarView compactCalendarView;
    TextView userName, userID, userEmail, userPhone, txtMonth, txtYear, tvRecTitleProfile;
    public static UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        compactCalendarView = findViewById(R.id.compactcalendar_view);

        userName = findViewById(R.id.tvStudentName);
        userID = findViewById(R.id.txtStudentId);
        userEmail = findViewById(R.id.txtStudentEmail);
        userPhone = findViewById(R.id.txtStudentPhoneNumber);
        profile = findViewById(R.id.img_StudentImage);
        settings = findViewById(R.id.settingSProfile);

        txtMonth = findViewById(R.id.txtCalMonth);
        txtYear = findViewById(R.id.txtCalYear);

        rvRecAchievementsImg = findViewById(R.id.rvRecAchievementsImg);
        tvRecTitleProfile = findViewById(R.id.tvRecTitleProfile);

        Intent intent = getIntent();
        userid = intent.getStringExtra("studentid");

        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);

        getMyProfile();
        getMyAttendance();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentProfileActivity.this, SettingsActivity.class));
            }
        });

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
//                List<Event> events = compactCalendarView.getEvents(dateClicked);
//                Toast.makeText(StudentProfileActivity.this, "Day was clicked: " + dateClicked + " with events " + events, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                SimpleDateFormat formatter = new SimpleDateFormat("MMM");
                String strDate = formatter.format(firstDayOfNewMonth);
                txtMonth.setText("Month:" + strDate);

                formatter = new SimpleDateFormat("yyyy");
                strDate = formatter.format(firstDayOfNewMonth);
                txtYear.setText("Year:" + strDate);
//                Toast.makeText(StudentProfileActivity.this, "Month was scrolled to: " + firstDayOfNewMonth, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getMyAttendance(){
        AttendanceAPI attendanceListAPI = RetrofitUrl.getInstance().create(AttendanceAPI.class);
        Call<List<AttendanceModel>> attendanceListCall = attendanceListAPI.getTotalAttendance(RetrofitUrl.token, userid);
        attendanceListCall.enqueue(new Callback<List<AttendanceModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<AttendanceModel>> call, Response<List<AttendanceModel>> response) {
                if(response.body() != null) {
                    List<AttendanceModel> attendanceList = response.body();

                    for (AttendanceModel att : attendanceList) {
                        if (att.getStatus()) {
                            String strDate = att.getDate();
                            long millisSinceEpoch = LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()
                                    .toEpochMilli();

                            Event ev1 = new Event(Color.GREEN, millisSinceEpoch, "Present that day");
                            compactCalendarView.addEvent(ev1);
                        }
                        else if (!att.getStatus()) {
                            String strDate = att.getDate();
                            long millisSinceEpoch = LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()
                                    .toEpochMilli();

                            Event ev1 = new Event(Color.RED, millisSinceEpoch, "Absent that day");
                            compactCalendarView.addEvent(ev1);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AttendanceModel>> call, Throwable t) {

            }
        });
    }

    public void getMyProfile() {
        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        final Call<UserModel> userModelCall = userAPI.getUserProfile(RetrofitUrl.token);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                userModel = response.body();
                userName.setText(userModel.getFname());
                userID.setText(userModel.getUserid());
                userEmail.setText(userModel .getEmail());
                userPhone.setText(userModel.getPhone());

                String imgPath = RetrofitUrl.imagePath + response.body().getProfile();

                try{
                    Picasso.get().load(imgPath) .into(profile);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.usericon).into(profile);
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
                Picasso.get().load(imgPath) .into(rvRecAchievementsImg);
                tvRecTitleProfile.setText(response.body().getAchievement());
            }

            @Override
            public void onFailure(Call<Achievements> call, Throwable t) {

            }
        });
    }

}
