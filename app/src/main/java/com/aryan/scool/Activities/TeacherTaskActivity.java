package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aryan.scool.R;

public class TeacherTaskActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNavigateAttendance, btnNavigateResult, btnNavigateStudents;
    String classid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_task);

        btnNavigateAttendance = findViewById(R.id.btnNavigateAttendance);
        btnNavigateResult = findViewById(R.id.btnNavigateResult);
        btnNavigateStudents = findViewById(R.id.btnNavigateStudents);
        classid = getIntent().getStringExtra("clickedClass");

        btnNavigateAttendance.setOnClickListener(this);
        btnNavigateResult.setOnClickListener(this);
        btnNavigateStudents.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnNavigateAttendance:
                intent = new Intent(TeacherTaskActivity.this, AttendanceActivity.class);
                intent.putExtra("teacherAttendance", classid);
                startActivity(intent);
                break;
            case R.id.btnNavigateResult:
                intent = new Intent(TeacherTaskActivity.this, ResultActivity.class);
                intent.putExtra("teacherResult", classid);
                startActivity(intent);
                break;
            case R.id.btnNavigateStudents:
                intent = new Intent(TeacherTaskActivity.this, StudentListActivity.class);
                intent.putExtra("teacherStudent", classid);
                startActivity(intent);
                break;
        }
    }
}
