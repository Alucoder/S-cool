package com.aryan.scool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.scool.Models.NoticeModel;
import com.aryan.scool.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    List<NoticeModel> notices;

    public NoticeAdapter(List<NoticeModel> notices) {
        this.notices = notices;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notice_card, parent, false);

        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        final NoticeModel notice = notices.get(position);


        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        String strDate = formatter.format(notice.getDate());

        holder.noticeDate.setText(strDate);
        holder.noticeType.setText(notice.getNoticeType());
        holder.venue.setText(notice.getVenue());
        holder.noticeTitle.setText(notice.getTitle());
        holder.noticedDesc.setText(notice.getDesc());
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder{

        TextView noticeDate, noticeType, venue, noticeTitle, noticedDesc, noticeTime;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeDate = itemView.findViewById(R.id.noticeDate);
            noticeType = itemView.findViewById(R.id.noticeType);
            venue = itemView.findViewById(R.id.venue);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticedDesc = itemView.findViewById(R.id.noticedDesc);
            noticeTime = itemView.findViewById(R.id.noticeTime);
        }
    }
}
