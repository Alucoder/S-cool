package com.aryan.scool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aryan.scool.BLL.LoginBLL;
import com.aryan.scool.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if (!token.equals("empty")){
            RetrofitUrl.token = token;
            startActivity(new Intent( LoginActivity.this, AttendanceActivity.class));
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkValidation()) {
//                    loginUser(id.getText().toString(), password.getText().toString());
                    loginUser();
//                }
            }
        });
    }

//    public void loginUser(String id, String password) {
//
//        UserModel user = new UserModel(id, password);
//        UserAPI usersAPI = RetrofitUrl.getInstance().create(UserAPI.class);
//        Call<TokenResponse> usersCall = usersAPI.login(user);
//
//        usersCall.enqueue(new Callback<TokenResponse>() {
//            @Override
//            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
//                if (response.isSuccessful() && response.body().getStatus().equals("Login success!")) {
//
//                    RetrofitUrl.token += response.body().getToken();
//                    Intent intent = new Intent(LoginActivity.this, AttendanceActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TokenResponse> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Wrong input", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

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
                startActivity(new Intent(LoginActivity.this, AttendanceActivity.class));
                finish();
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
