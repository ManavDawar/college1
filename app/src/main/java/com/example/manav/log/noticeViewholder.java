package com.example.manav.log;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class noticeViewholder extends RecyclerView.ViewHolder  {

    private String date,content;
    private TextView dateText,contentText;

    public noticeViewholder(View itemView) {
        super(itemView);
        dateText=itemView.findViewById(R.id.notice_date);
        contentText=itemView.findViewById(R.id.myresultTextView);

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        dateText.setText(date);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        contentText.setText(content);
    }


}
