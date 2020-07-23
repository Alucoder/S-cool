package com.aryan.scool.Adapters;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Interfaces.UserAPI;
import com.aryan.scool.Models.Achievements;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {

    List<Achievements> achievements;
    String studentId;

    public AchievementAdapter(List<Achievements> achievements, String studentId) {
        this.achievements = achievements;
        this.studentId = studentId;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievement_layout, parent, false);

        return new AchievementAdapter.AchievementViewHolder(view);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        final Achievements achievement = achievements.get(position);
        holder.tvTitle.setText(achievement.getAchievement());
        Mode();
        String imagePathProfile = RetrofitUrl.imagePath + achievement.getBadge();
        Picasso.get().load(imagePathProfile).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel updUser = new UserModel(achievement.get_id());

                UserAPI user = RetrofitUrl.getInstance().create(UserAPI.class);
                Call<UserModel> userCall = user.updateAchievements(RetrofitUrl.token, studentId, updUser);

                userCall.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });

            }
        });

    }


    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView img;
        public AchievementViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.achTitle);
            img = itemView.findViewById(R.id.achImage);
        }
    }

}
