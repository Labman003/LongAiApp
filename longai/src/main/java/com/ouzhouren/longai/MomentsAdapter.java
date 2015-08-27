package com.ouzhouren.longai;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ouzhouren.longai.entity.Moment;
import com.ouzhouren.longai.entity.Momentcomment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭泽锋 on 2015/8/26.
 */
public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder> {
    private Context context;
    private List<Moment> momentList;
    boolean isZaned = false;


    public MomentsAdapter(Context context, List<Moment> momentList) {
        this.context = context;
        this.momentList = momentList;
    }

    @Override
    public int getItemCount() {
        return momentList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context
        ).inflate(R.layout.moments_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.moment_content.setText(momentList.get(position).getMomentContent().toString());
        holder.moment_pubtime.setText(momentList.get(position).getMomentPubtime().toString());
        List<Momentcomment> momentcommentList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Momentcomment momentcomment = new Momentcomment();
            momentcomment.setNickName("psz");
            momentcomment.setMomentcommentcontent("i want to fuck yyt!");
            momentcommentList.add(momentcomment);
            View view = LayoutInflater.from(context).inflate(R.layout.moment_comment_list_item, null);
            TextView nickName = (TextView) view.findViewById(R.id.moment_comment_user);
            TextView content = (TextView) view.findViewById(R.id.moment_comment_content);
            nickName.setText("psz");
            content.setText("i want to fuck yyt!" + i);
            holder.layout.addView(view);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.moment_zan:
                        String text = holder.moment_zanNumb.getText().toString();
                        int numb = Integer.parseInt(text);
                        if (!isZaned) {
                            numb++;
                            holder.moment_zanNumb.setText(numb + "");
                            holder.moment_zan.setBackgroundResource(R.drawable.icon_zan_orange);
                            Toast.makeText(context,"赞+1",Toast.LENGTH_SHORT).show();
                            isZaned = true;
                        } else {
                            numb--;
                            holder.moment_zanNumb.setText(numb + "");
                            holder.moment_zan.setBackgroundResource(R.drawable.icon_zan_grey);
                            Toast.makeText(context,"赞-1",Toast.LENGTH_SHORT).show();
                            isZaned = false;
                        }
                        break;
                    case R.id.moment_comment_send:
                        View view = LayoutInflater.from(context).inflate(R.layout.moment_comment_list_item, null);
                        TextView nickName = (TextView) view.findViewById(R.id.moment_comment_user);
                        TextView content = (TextView) view.findViewById(R.id.moment_comment_content);
                        nickName.setText("psz");
                        content.setText(holder.moment_comment_content.getText().toString());
                        Toast.makeText(context,"评论成功！",Toast.LENGTH_SHORT).show();
                        holder.layout.addView(view);
                        break;
                    default:
                        break;
                }
            }
        };
        holder.moment_zan.setOnClickListener(listener);
        holder.moment_comment_send.setOnClickListener(listener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView user_icon;
        public TextView user_name, moment_pubtime, moment_content,moment_zanNumb;
        public LinearLayout layout;
        public EditText moment_comment_content;
        public Button moment_zan, moment_comment_send;

        public ViewHolder(View v) {
            super(v);
            user_icon = (ImageView) v.findViewById(R.id.moment_user_icon);
            user_name = (TextView) v.findViewById(R.id.moments_user_name);
            moment_pubtime = (TextView) v.findViewById(R.id.moments_pubtime);
            moment_content = (TextView) v.findViewById(R.id.moments_content);
            layout = (LinearLayout) v.findViewById(R.id.moment_comment_layout);
            moment_zan = (Button) v.findViewById(R.id.moment_zan);
            moment_comment_send = (Button) v.findViewById(R.id.moment_comment_send);
            moment_zanNumb = (TextView) v.findViewById(R.id.moment_zanNumb);
            moment_comment_content = (EditText) v.findViewById(R.id.moment_comment_content);
        }
    }
}
