package com.aryan.scool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Models.ResultViewModel;
import com.aryan.scool.R;

import java.util.List;

public class ResultViewAdapter extends RecyclerView.Adapter<ResultViewAdapter.ResultViewHolder> {

    List<ResultViewModel> resultList;

    public ResultViewAdapter(List<ResultViewModel> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_view_result_layout, parent, false);

        return new ResultViewAdapter.ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResultViewHolder holder, int position) {
        final ResultViewModel result = resultList.get(position);

        if(result.getSubject() != null) {
            holder.tvSubName.setText(result.getSubject().getSubName());
        }
        holder.tvSubExamType.setText(result.getExamType());
        holder.tvSubTotalMarks.setText(result.getFullMarks() + "");
        holder.tvSubMarks.setText(result.getMarks() + "");
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{

        TextView tvSubName, tvSubExamType, tvSubMarks, tvSubTotalMarks;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubName = itemView.findViewById(R.id.tvSubName);
            tvSubExamType = itemView.findViewById(R.id.tvSubExamType);
            tvSubMarks = itemView.findViewById(R.id.tvSubMarks);
            tvSubTotalMarks = itemView.findViewById(R.id.tvSubTotalMarks);
        }
    }
}
