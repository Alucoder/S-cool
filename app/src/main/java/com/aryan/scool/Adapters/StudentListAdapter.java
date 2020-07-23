package com.aryan.scool.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Activities.ViewStudentProfileActivity;
import com.aryan.scool.Dialog.Dialog_Teaceher;
import com.aryan.scool.Helper.RetrofitUrl;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListViewHolder> {

    List<UserModel> studList;
    Context mContext;

    public StudentListAdapter(List<UserModel> studList, Context mContext) {
        this.studList = studList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public StudentListAdapter.StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_info_layout, parent, false);

        return new StudentListAdapter.StudentListViewHolder(view, mContext);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onBindViewHolder(@NonNull final StudentListViewHolder holder, int position) {
        final UserModel stud = studList.get(position);
        Mode();
        holder.stud_name.setText(stud.getFname());
        String imagePathProfile = RetrofitUrl.imagePath + stud.getProfile();
        Picasso.get().load(imagePathProfile).into(holder.stud_profile);

        holder.cardStudList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewStudentProfileActivity.class);
                intent.putExtra("viewStudentProfile", stud.get_id());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studList.size();
    }

    public class StudentListViewHolder extends RecyclerView.ViewHolder{

        CardView cardStudList;
        ImageView stud_profile;
        TextView stud_name;

        public StudentListViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);

            cardStudList = itemView.findViewById(R.id.cv_teacher_list);
            stud_profile = itemView.findViewById(R.id.img_details_ch_image);
            stud_name = itemView.findViewById(R.id.tv_teacher_name);

        }
    }
}
