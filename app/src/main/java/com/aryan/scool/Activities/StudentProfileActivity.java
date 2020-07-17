package com.aryan.scool.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.AttendanceAPI;
import com.aryan.scool.Models.AttendanceModel;
import com.aryan.scool.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

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

public class StudentProfileActivity extends AppCompatActivity {

    String userid;
    CompactCalendarView compactCalendarView;
    TextView txtMonth, txtYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        txtMonth = findViewById(R.id.txtCalMonth);
        txtYear = findViewById(R.id.txtCalYear);

        Intent intent = getIntent();
        userid = intent.getStringExtra("studentid");

        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);

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
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AttendanceModel>> call, Throwable t) {

            }
        });
    }

}
