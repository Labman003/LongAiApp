package com.ouzhouren.longai.view.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.Comment;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭泽锋 on 2015/8/19.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context
        ).inflate(R.layout.comment_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.userName.setText(commentList.get(position).getNickName());
        holder.commentContent.setText(commentList.get(position).getContent());
        Date date = commentList.get(position).getPubTime();
        String hours = date.getHours()>=10? (date.getHours()+""):("0"+date.getHours());
        String minutes = date.getMinutes()>10? (date.getMinutes()+""):("0"+date.getMinutes());
        String time = (date.getMonth()+1) + "-" + date.getDate() + " " + hours + ":" + minutes;
        holder.commentTime.setText(time);
    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView userIcon;
        public TextView userName;
        public TextView zanNum;
        public TextView commentContent;
        public TextView commentTime;

        public ViewHolder(View v) {
            super(v);
            userIcon = (CircleImageView) v.findViewById(R.id.comment_user_icon);
            userName = (TextView) v.findViewById(R.id.comment_user_name);
            zanNum = (TextView) v.findViewById(R.id.comment_zanNum);
            commentContent = (TextView) v.findViewById(R.id.comment_content);
            commentTime = (TextView) v.findViewById(R.id.comment_time);
        }
    }
}


