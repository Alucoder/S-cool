package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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


        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
//        Event ev2 = new Event(Color.GREEN, 1433704251000L);
//        compactCalendarView.addEvent(ev2);

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
//        List<Event> events = compactCalendarView.getEvents(1594815018449L); // can also take a Date object

        // events has size 2 with the 2 events inserted previously
//        Toast.makeText(this, "Events: " + events, Toast.LENGTH_SHORT).show();


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
            @Override
            public void onResponse(Call<List<AttendanceModel>> call, Response<List<AttendanceModel>> response) {
                if(response.body() != null) {
                    List<AttendanceModel> attendanceList = response.body();
                    List<AttendanceModel> presentList = new ArrayList<>();

                    for (AttendanceModel att : attendanceList) {
                        if (att.getStatus()) {
                            String strDate = att.getDate();
                            Date date = null;
                            try {
                                date = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH).parse(strDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if(date != null)
                            {
                                long milseconds = date.getTime();
                                // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
                                Event ev1 = new Event(Color.GREEN, milseconds, "Present that day");
                                compactCalendarView.addEvent(ev1);
                            }



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
