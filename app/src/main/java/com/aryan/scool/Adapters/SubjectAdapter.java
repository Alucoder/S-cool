package com.aryan.scool.Adapters;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Models.SubjectModel;
import com.aryan.scool.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>  {

    List<SubjectModel> subjectList;
    Context mContext;

    public SubjectAdapter(List<SubjectModel> subjectList, Context mContext) {
        this.subjectList = subjectList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_info, parent, false);

        return new SubjectAdapter.SubjectViewHolder(view, mContext);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position) {
        final SubjectModel subjects = subjectList.get(position);
        Mode();
        holder.sub_titile.setText(subjects.getSubName());
        holder.sub_details.setText(subjects.getSubjectDetail());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        CardView cv_subject;
        TextView sub_titile, sub_details;

        public SubjectViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);

            cv_subject = itemView.findViewById(R.id.cv_subject_list);
            sub_titile = itemView.findViewById(R.id.tv_subject_title);
            sub_details = itemView.findViewById(R.id.tv_subject_description);

        }
    }

}
