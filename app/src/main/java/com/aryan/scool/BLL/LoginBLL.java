package com.aryan.scool.BLL;

import com.aryan.scool.RetrofitUrl;
import com.aryan.scool.TokenResponse;
import com.aryan.scool.UserAPI;
import com.aryan.scool.UserModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String userId, String password) {
        UserModel userModel = new UserModel(userId, password);
        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<TokenResponse> signUpResponseCall =userAPI.login(userModel);

        try {
            Response<TokenResponse> loginResponse = signUpResponseCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                RetrofitUrl.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
