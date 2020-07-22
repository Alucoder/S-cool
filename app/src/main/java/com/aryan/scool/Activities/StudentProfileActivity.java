package com.aryan.scool.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.AttendanceAPI;
import com.aryan.scool.Interfaces.UserAPI;
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

    ImageView imgProfile;
    String userid;
    CompactCalendarView compactCalendarView;
    TextView userName, userID, userEmail, userPhone, txtMonth, txtYear;
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

        txtMonth = findViewById(R.id.txtCalMonth);
        txtYear = findViewById(R.id.txtCalYear);

        Intent intent = getIntent();
        userid = intent.getStringExtra("studentid");

        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);

        getMyProfile();
        getMyAttendance();


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

    public void MyProfile() {
        userName.setText(StudentDashboard.user.getFname());
        userID.setText(StudentDashboard.user.getUserid());
        userEmail.setText(StudentDashboard.user.getEmail());
        userPhone.setText("" + StudentDashboard.user.getPhone());
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
                userPhone.setText("" + userModel.getPhone());

                String imgPath = RetrofitUrl.imagePath + response.body().getProfile();

                ImageView profile = findViewById(R.id.img_StudentImage);
                try{
                    Picasso.get().load(imgPath) .into(profile);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.usericon).into(profile);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

}
