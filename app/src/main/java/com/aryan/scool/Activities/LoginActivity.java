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
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.loginId);
        password = findViewById(R.id.loginPass);
        login = findViewById(R.id.btnLogin);

        id.requestFocus();

        sharedPreferences = getSharedPreferences( "Scool", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "Bearer ");
        if (!token.equals("Bearer ")){
            RetrofitUrl.token = token;
//            loginUser(sharedPreferences.getString("userID", ""), sharedPreferences.getString("userPass", ""));
            if(sharedPreferences.getString("userType", "").equals("user"))
            {
                startActivity(new Intent(LoginActivity.this, StudentDashboard.class));
                finish();
            }
            else if(sharedPreferences.getString("userType", "").equals("teacher")) {
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            }
            else{
                Toast.makeText(this, "Error login. Please try reopening the app", Toast.LENGTH_SHORT).show();
            }
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(id.getText().toString(), password.getText().toString());
            }
        });
    }

    private void loginUser(String userID, String userPass) {
        if(checkValidation()) {

            LoginBLL lBll = new LoginBLL();
            StrictModeClass.StrictMode();

            if (lBll.checkUser(userID, userPass)) {

                sharedPreferences = getSharedPreferences("Scool", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userType", lBll.usertype);
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

    public void deleteSavedUser(){
        sharedPreferences = getSharedPreferences("Scool", 0);
        sharedPreferences.edit().clear().commit();
    }
}
