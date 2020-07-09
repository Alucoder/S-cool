package com.aryan.scool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Activities.AttendanceActivity;
import com.aryan.scool.R;
import com.aryan.scool.Interfaces.TakeAttendance;
import com.aryan.scool.Models.UserModel;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    List<UserModel> studentList;
    Context mContext;
    TakeAttendance takeAttendance;

    public AttendanceAdapter(List<UserModel> studentList, Context mContext) {
        this.studentList = studentList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_attendance_layout, parent, false);

        return new AttendanceViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull final AttendanceViewHolder holder, final int position) {
        final UserModel student = studentList.get(position);

        holder.tvSname.setText(student.getName());
        holder.rgAttendance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioId = holder.rgAttendance.getCheckedRadioButtonId();
                RadioButton rdoStatus = holder.itemView.findViewById(radioId);
                takeAttendance.attendance(student.get_id(), rdoStatus.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView tvSname, tvSrank;
        RadioGroup rgAttendance;

        public AttendanceViewHolder(@NonNull final View itemView, Context mContext) {
            super(itemView);

            tvSrank = itemView.findViewById(R.id.tvSrank);
            tvSname = itemView.findViewById(R.id.tvSname);
            rgAttendance = itemView.findViewById(R.id.rgAttendance);
            takeAttendance = new AttendanceActivity();
        }

    }
}
