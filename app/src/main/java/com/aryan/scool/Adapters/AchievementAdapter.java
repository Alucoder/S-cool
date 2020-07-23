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
import com.aryan.scool.Models.Achievements;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {

    List<Achievements> achievements;

    public AchievementAdapter(List<Achievements> achievements) {
        this.achievements = achievements;
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
        Achievements achievement = achievements.get(position);
        holder.tvTitle.setText(achievement.getAchievement());
        Mode();
        String imagePathProfile = RetrofitUrl.imagePath + achievement.getBadge();
        Picasso.get().load(imagePathProfile).into(holder.img);

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
