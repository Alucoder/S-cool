package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aryan.scool.BLL.LoginBLL;
import com.aryan.scool.R;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {

    EditText id, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.loginId);
        password = findViewById(R.id.loginPass);
        login = findViewById(R.id.btnLogin);

        id.requestFocus();

        SharedPreferences sharedPreferences = getSharedPreferences( "Scool", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "empty");
//        if (!token.equals("empty")){
////            RetrofitUrl.token = token;
////            startActivity(new Intent( LoginActivity.this, ShowAttendanceActivity.class));
////        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        if(checkValidation()) {
            String userID = id.getText().toString().trim();
            String userPass = password.getText().toString().trim();

            LoginBLL lBll = new LoginBLL();
            StrictModeClass.StrictMode();

            if (lBll.checkUser(userID, userPass)) {
                SharedPreferences sharedPreferences = getSharedPreferences("Scool", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", RetrofitUrl.token);
                editor.commit();
                if(lBll.usertype.equals("user"))
                {
                    startActivity(new Intent(LoginActivity.this, StudentDashboard.class));
                    finish();
                }
                else if(lBll.usertype.equals("teacher")) {
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(this, "Error login. Please try reopening the app", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error!! incorrect username or password", Toast.LENGTH_SHORT).show();
                id.requestFocus();
            }
        }
    }

    private boolean checkValidation() {
        if(id.getText().toString().trim().isEmpty() || id.length() != 6) {
            id.setError("User ID length did not match");
            return false;
        } if (password.getText().toString().trim().isEmpty()) {
            password.setError("Password is require");
            return false;
        }
        return true;
    }

}
