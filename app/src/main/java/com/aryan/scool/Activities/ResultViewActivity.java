package com.aryan.scool.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.aryan.scool.Adapters.ResultViewAdapter;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.ResultAPI;
import com.aryan.scool.Models.ResultViewModel;
import com.aryan.scool.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultViewActivity extends AppCompatActivity {

    RecyclerView rvResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        rvResultView = findViewById(R.id.rvResultViewStudents);
        getResults();
    }

    public void getResults(){
        ResultAPI resultAPI = RetrofitUrl.getInstance().create(ResultAPI.class);
        Call<List<ResultViewModel>> resultCall = resultAPI.getResult(RetrofitUrl.token, StudentDashboard.user.get_id());

        resultCall.enqueue(new Callback<List<ResultViewModel>>() {
            @Override
            public void onResponse(Call<List<ResultViewModel>> call, Response<List<ResultViewModel>> response) {
                if(response.body() != null) {
                    List<ResultViewModel> results = response.body();
                    ResultViewAdapter adapter = new ResultViewAdapter(results);
                    rvResultView.setAdapter(adapter);
                    rvResultView.setLayoutManager(new LinearLayoutManager(ResultViewActivity.this));

                }
                else{
                    Toast.makeText(ResultViewActivity.this, "Result not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResultViewModel>> call, Throwable t) {
                Toast.makeText(ResultViewActivity.this, "error:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
