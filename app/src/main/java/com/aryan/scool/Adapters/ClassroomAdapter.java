package com.aryan.scool.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Activities.TeacherTaskActivity;
import com.aryan.scool.Models.ClassModel;
import com.aryan.scool.R;

import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ClassroomViewHolder>  {

    List<ClassModel> classList;
    Context mContext;

    public ClassroomAdapter(List<ClassModel> classList, Context mContext) {
        this.classList = classList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ClassroomAdapter.ClassroomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_info, parent, false);

        return new ClassroomAdapter.ClassroomViewHolder(view);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassroomAdapter.ClassroomViewHolder holder, int position) {
        final ClassModel classes = classList.get(position);
        Mode();
        holder.sub_titile.setText(classes.getClassroom());
//        if(subjects.getSection() != null) {
            holder.sub_details.setText("");
//        }
        holder.cv_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TeacherTaskActivity.class);
                intent.putExtra("clickedClass", classes.get_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public class ClassroomViewHolder extends RecyclerView.ViewHolder {
        CardView cv_subject;
        TextView sub_titile, sub_details;

        public ClassroomViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_subject = itemView.findViewById(R.id.cv_subject_list);
            sub_titile = itemView.findViewById(R.id.tv_subject_title);
            sub_details = itemView.findViewById(R.id.tv_subject_description);
        }
    }

}
