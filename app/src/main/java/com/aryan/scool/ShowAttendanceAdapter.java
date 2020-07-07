package com.aryan.scool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(@NonNull final ShowAttendanceViewHolder holder, int position) {
        final UserModel student = studentList.get(position);

        holder.tvSname.setText(student.getName());

        AttendanceAPI attendanceListAPI = RetrofitUrl.getInstance().create(AttendanceAPI.class);
        Call<List<AttendanceModel>> attendanceListCall = attendanceListAPI.getTotalAttendance(RetrofitUrl.token, student.get_id());
        attendanceListCall.enqueue(new Callback<List<AttendanceModel>>() {
            @Override
            public void onResponse(Call<List<AttendanceModel>> call, Response<List<AttendanceModel>> response) {
                if(response.body() != null) {
                    List<AttendanceModel> attendanceList = response.body();
                    holder.tvSTotalDays.setText(attendanceList.size() + "");

                    List<AttendanceModel> presentList = new ArrayList<>();
                    for(AttendanceModel att: attendanceList){
                        if(att.getStatus()){
                            presentList.add(att);
                        }
                    }

                    if(presentList.size() > 0)
                    {
                        holder.tvSAttendDays.setText(presentList.size() + "");
                        holder.tvSAttendPer.setText(presentList.size() / attendanceList.size() * 100 + "%");
                    }

                    else{
                        holder.tvSAttendPer.setText("0");
                        holder.tvSAttendDays.setText("0");
                    }

                }

                else{
                    holder.tvSTotalDays.setText("0");
                    holder.tvSAttendDays.setText("0");
                    holder.tvSAttendPer.setText("0%");
                }
            }

            @Override
            public void onFailure(Call<List<AttendanceModel>> call, Throwable t) {

            }
        });

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
