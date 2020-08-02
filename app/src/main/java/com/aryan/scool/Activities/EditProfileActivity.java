package com.aryan.scool.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    EditText name, email, phone;
    Button done;
    ImageView profile;
    String imagePath;
    UserModel user = StudentDashboard.user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.tvNameUpd);
        email = findViewById(R.id.tvEmailUpd);
        phone = findViewById(R.id.tvPhoneNumberUpd);
        profile = findViewById(R.id.ImgProfileChange);
        done = findViewById(R.id.btnDoneEdit);

        getProfile();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void getProfile() {

        name.setText(user.getFname());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        String imgPath = RetrofitUrl.imagePath + user.getProfile();
        Picasso.get().load(imgPath).into(profile);
    }

//    private void browseImage(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, 0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            if (data == null) {
//                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else{ return;}
//        Uri uri = data.getData();
//        profile.setImageURI(uri);
//        imagePath = getRealPathFromUri(uri);
//    }
//
//    private String getRealPathFromUri(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        CursorLoader loader = new CursorLoader(getApplicationContext(),
//                uri, projection, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(colIndex);
//        cursor.close();
//        return result;
//    }
//
//    private void saveImageOnly() {
//        File file = new File(imagePath);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
//                file.getName(), requestBody);
//
//
//        UserAPI userAPI = RetrofitUrl.getInstance().create(UserAPI.class);
//        Call<ImageResponse> responseBodyCall = userAPI.uploadProfile(body);
//
//        ImgStrictMode.ImgMode();
//
//        try {
//            Response<ImageResponse> imageResponse = responseBodyCall.execute();
//            assert imageResponse.body() != null;
//            imageName = imageResponse.body().getFileName();
//        } catch (IOException e) {
//            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//        if(imageName.equals("")){
//            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }

    private void updateProfile(){
        UserModel userChanged = new UserModel(name.getText().toString(), email.getText().toString(), phone.getText().toString());

        UserAPI usersAPI = RetrofitUrl.getInstance().create(UserAPI.class);
        Call<UserModel> updateCall = usersAPI.updateProfile(RetrofitUrl.token, userChanged);

        updateCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(EditProfileActivity.this, "Error updating profile" , Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(EditProfileActivity.this, "Profile Updated" , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error updating profile" , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
