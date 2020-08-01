package com.aryan.scool.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.R;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView privacy, logout, editP, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initSettingsLayout();

        privacy.setOnClickListener(this);
        logout.setOnClickListener(this);

    }


    public void initSettingsLayout(){

        privacy = findViewById(R.id.privacySettings);
        logout = findViewById(R.id.logoutSettings);
        editP = findViewById(R.id.editProfileSettings);
        about = findViewById(R.id.aboutSettings);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.privacySettings:
                Intent changePassword = new Intent(this, PrivacyActivity.class);
                startActivity(changePassword);
                break;

            case R.id.logoutSettings:
                deleteSavedUser();
                RetrofitUrl.token = "Bearer ";
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                finish();
                break;

        }
    }

    public void deleteSavedUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("Scool", 0);
        sharedPreferences.edit().clear().commit();
    }


}
