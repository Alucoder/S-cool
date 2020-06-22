package com.aryan.scool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(id.getText().toString(), password.getText().toString());
            }
        });
    }

    public void loginUser(String id, String password) {

        UserModel user = new UserModel(id, password);
        UserAPI usersAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<TokenResponse> usersCall = usersAPI.login(user);

        usersCall.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("Login success!")) {

                    RetrofitUrl.token += response.body().getToken();
                    Intent intent = new Intent(LoginActivity.this, AttendanceActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Wrong input", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
