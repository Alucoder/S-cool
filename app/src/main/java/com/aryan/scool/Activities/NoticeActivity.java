package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.scool.Adapters.NoticeAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.NoticeAPI;
import com.aryan.scool.Models.NoticeModel;
import com.aryan.scool.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {

    RecyclerView rvNotice;
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        btn = findViewById(R.id.btnGotoLogin);
        rvNotice = findViewById(R.id.rvNotice);
        getNotices();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotices();
    }

    public void getNotices(){
        NoticeAPI noticeAPI = RetrofitUrl.getInstance().create(NoticeAPI.class);
        Call<List<NoticeModel>> noticeModelCall = noticeAPI.getNotice();

        noticeModelCall.enqueue(new Callback<List<NoticeModel>>() {
            @Override
            public void onResponse(Call<List<NoticeModel>> call, Response<List<NoticeModel>> response) {
                if(response.body() != null) {
                    List<NoticeModel> notices = response.body();
                    NoticeAdapter adapter = new NoticeAdapter(notices);
                    rvNotice.setAdapter(adapter);
                    rvNotice.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));

                }
                else{
                    Toast.makeText(NoticeActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<NoticeModel>> call, Throwable t) {
                Toast.makeText(NoticeActivity.this, "error:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
