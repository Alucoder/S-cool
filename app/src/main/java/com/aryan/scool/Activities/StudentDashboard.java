package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.aryan.scool.R;

public class StudentDashboard extends AppCompatActivity {
    CardView cv_stdDash_teacher;
    private static final int REQUEST_PHONE_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        cv_stdDash_teacher = findViewById(R.id.cv_stdDash_teacher);

        if (ContextCompat.checkSelfPermission(StudentDashboard.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(StudentDashboard.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        }

        cv_stdDash_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, TeacherInfo.class));
            }
        });
    }
}