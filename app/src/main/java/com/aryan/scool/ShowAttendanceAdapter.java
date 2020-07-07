package com.aryan.scool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowAttendanceAdapter extends RecyclerView.Adapter<ShowAttendanceAdapter.ShowAttendanceViewHolder>{

    List<UserModel> studentList;

    public ShowAttendanceAdapter(List<UserModel> studentList) {
        this.studentList = studentList;

    }
    @NonNull
    @Override
    public ShowAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_attendance_show_layout, parent, false);

        return new ShowAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAttendanceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ShowAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView tvSname, tvSTotalDays, tvSAttendDays, tvSAttendPer;

        public ShowAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSname = itemView.findViewById(R.id.tvSname);
            tvSTotalDays = itemView.findViewById(R.id.tvSTotalDays);
            tvSAttendPer = itemView.findViewById(R.id.tvSAttendPer);
            tvSAttendDays = itemView.findViewById(R.id.tvSAttendDays);
        }
    }
}
