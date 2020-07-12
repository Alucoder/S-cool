package com.aryan.scool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Activities.ResultActivity;
import com.aryan.scool.Interfaces.TakeResult;
import com.aryan.scool.Models.UserModel;
import com.aryan.scool.R;

import org.w3c.dom.Text;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    List<UserModel> studentList;
    TakeResult takeResult;

    public ResultAdapter(List<UserModel> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_result_layout, parent, false);

        return new ResultAdapter.ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResultViewHolder holder, int position) {
        final UserModel student = studentList.get(position);

        holder.tvName.setText(student.getName());
        holder.etMarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && holder.etMarks.getText().toString().trim() != null){
                    takeResult.result(student, Float.parseFloat(holder.etMarks.getText().toString()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{

        EditText etMarks;
        TextView tvName;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            etMarks = itemView.findViewById(R.id.etMarks);
            tvName = itemView.findViewById(R.id.tvSname);
            takeResult = new ResultActivity();
        }
    }
}
