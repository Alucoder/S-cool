package com.aryan.scool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.R;
import com.aryan.scool.model.UserModel;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    List<UserModel> studentList;

    public AttendanceAdapter(List<UserModel> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_attendance_layout, parent, false);

        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        final UserModel student = studentList.get(position);

        holder.tvSname.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView tvSname, tvSrank;
        RadioGroup rgAttendance;
        RadioButton rdoAbsent, rdoPresent;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSrank = itemView.findViewById(R.id.tvSrank);
            tvSname = itemView.findViewById(R.id.tvSname);
            rgAttendance = itemView.findViewById(R.id.rgAttendance);
            rdoPresent = itemView.findViewById(R.id.rdoPresent);
            rdoAbsent = itemView.findViewById(R.id.rdoAbsent);
        }
    }
}
