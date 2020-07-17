package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aryan.scool.R;

public class StudentDashboard extends AppCompatActivity {
    CardView cv_stdDash_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        cv_stdDash_teacher = findViewById(R.id.cv_stdDash_teacher);

        cv_stdDash_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, TeacherInfo.class));
            }
        });
    }
}