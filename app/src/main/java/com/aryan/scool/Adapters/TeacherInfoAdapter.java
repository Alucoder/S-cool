package com.aryan.scool.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Activities.AttendanceActivity;
import com.aryan.scool.Activities.StudentDashboard;
import com.aryan.scool.Activities.TeacherInfo;
import com.aryan.scool.Dialog.Dialog_Teaceher;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeacherInfoAdapter extends RecyclerView.Adapter<TeacherInfoAdapter.TeacherInfoViewHolder> {

    List<UserModel> teachersList;
    Context mContext;

    public TeacherInfoAdapter(List<UserModel> teachersList, Context mContext) {
        this.teachersList = teachersList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public TeacherInfoAdapter.TeacherInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_info_layout, parent, false);

        return new TeacherInfoAdapter.TeacherInfoViewHolder(view, mContext);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onBindViewHolder(@NonNull TeacherInfoViewHolder holder, int position) {
        final UserModel teachers = teachersList.get(position);
        Mode();
        holder.teacher_name.setText(teachers.getFname());
        String imagePathProfile = RetrofitUrl.imagePath + teachers.getProfile();
        Picasso.get().load(imagePathProfile).into(holder.teacher_profile);

        holder.cardTeaceherLIst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeacehrsDialog();
            }

            private void openTeacehrsDialog() {
                Dialog_Teaceher dialog_teaceher = new Dialog_Teaceher();
                dialog_teaceher.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "challenge dialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    public class TeacherInfoViewHolder extends RecyclerView.ViewHolder{

        CardView cardTeaceherLIst;
        ImageView teacher_profile;
        TextView teacher_name;

        public TeacherInfoViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);

            cardTeaceherLIst = itemView.findViewById(R.id.cv_teacher_list);
            teacher_profile = itemView.findViewById(R.id.img_details_ch_image);
            teacher_name = itemView.findViewById(R.id.tv_teacher_name);

        }
    }
}
