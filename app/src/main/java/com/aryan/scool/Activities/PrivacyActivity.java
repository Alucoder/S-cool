package com.aryan.scool.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyActivity extends AppCompatActivity {

    EditText etOld, etNewPass, etConfirm;
    Button btnChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        etOld = findViewById(R.id.etOldPassword);
        etNewPass = findViewById(R.id.etNewPassword);
        etConfirm = findViewById(R.id.etConfirmPassword);
        btnChangePass = findViewById(R.id.btnChangePassword);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndChangePassword();
            }
        });
    }

    public void validateAndChangePassword(){

        UserModel userPass = new UserModel(StudentDashboard.user.getUserid() ,etOld.getText().toString());
        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<Void> userCall = userAPI.checkPassword(RetrofitUrl.token, StudentDashboard.user.getUserid(), userPass);

        userCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(PrivacyActivity.this, "Old password validation failed!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etNewPass.getText().toString().matches(etConfirm.getText().toString())){
                    UserModel userNewPass = new UserModel(StudentDashboard.user.getUserid() ,etNewPass.getText().toString());

                    UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
                    Call<Void> userCall = userAPI.changePassword(RetrofitUrl.token, userNewPass);

                    userCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(PrivacyActivity.this, "Sorry! Error changing password", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(PrivacyActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                            Intent backtoSettings = new Intent(PrivacyActivity.this, SettingsActivity.class);
                            startActivity(backtoSettings);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(PrivacyActivity.this, "Sorry! Error changing password", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else{
                    Toast.makeText(PrivacyActivity.this, "Password confirmation did not match!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PrivacyActivity.this, "Old password validation failed!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
