package com.aryan.scool.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aryan.scool.R;

public class StudentDashboard extends AppCompatActivity {
    CardView cv_stdDash_teacher;
    ImageView img_profile;

    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        cv_stdDash_teacher = findViewById(R.id.cv_stdDash_teacher);
        img_profile = findViewById(R.id.img_dashboard_student_profile);

//        if (ContextCompat.checkSelfPermission(StudentDashboard.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(StudentDashboard.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
//        }
//        if(ContextCompat.checkSelfPermission(StudentDashboard.this, Manifest.permission.CALL_PHONE) !=
//                PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions( StudentDashboard.this,
//                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
//        }


        cv_stdDash_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, TeacherInfo.class));
            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, StudentProfileActivity.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissions();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void permissions() {

            if (ContextCompat.checkSelfPermission(StudentDashboard.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(StudentDashboard.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
    }
}