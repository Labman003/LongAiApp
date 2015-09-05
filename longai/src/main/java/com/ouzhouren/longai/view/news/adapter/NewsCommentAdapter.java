package com.ouzhouren.longai.view.news.adapter;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.NewsComment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 郭泽锋 on 2015/8/19.
 */
public class NewsCommentAdapter extends RecyclerView.Adapter<NewsCommentAdapter.ViewHolder> {
    private Context context;
    private List<NewsComment> commentList;


    //设置接口
    public static interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public NewsCommentAdapter(Context context, List<NewsComment> commentList) {
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.userName.setText(commentList.get(position).getNickname());
        holder.commentContent.setText(commentList.get(position).getCommentContent());
        long date = commentList.get(position).getPubtime();
//        String hours = date.getHours() >= 10 ? (date.getHours() + "") : ("0" + date.getHours());
//        String minutes = date.getMinutes() > 10 ? (date.getMinutes() + "") : ("0" + date.getMinutes());
//        String time = (date.getMonth() + 1) + "-" + date.getDate() + " " + hours + ":" + minutes;
        holder.commentTime.setText(date+"");
//        holder.zanNum.setText(commentList.get(position).getZanNumb() + "");

        //内容没超过8行自动隐藏展开按钮
        holder.commentContent.post(new Runnable() {
            @Override
            public void run() {
                if (holder.commentContent.getLineCount() < 8) {
                    holder.showMore.setVisibility(View.INVISIBLE);
                }
            }
        });

        //设置点击评论弹出对话框事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setItems(holder.dialogContent, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int zanNumb = Integer.parseInt(holder.zanNum.getText().toString(), 10);
                                switch (which) {
                                    case 0: //点击了 赞同 按钮
                                        if (!holder.isZan) {   //点击了赞同按钮
                                            holder.dialogContent[0] = "取消赞同";
                                            holder.isZan = true;
                                            holder.commentZan.setImageResource(R.drawable.icon_zan_blue);
                                            zanNumb++;
                                            holder.zanNum.setText(zanNumb+"");
                                            Toast.makeText(context, "赞+1", Toast.LENGTH_SHORT).show();
//                                            holder.zanNum.setText(getItem(position).getZanNumb()+"");
                                            //notifyItemChanged(position);
                                        } else {
                                            holder.dialogContent[0] = "赞同";
                                            holder.isZan = false;
                                            holder.commentZan.setImageResource(R.drawable.icon_zan_grey);
                                            zanNumb--;
                                            holder.zanNum.setText(zanNumb +"");
//                                            getItem(position).setZanNumb(zanNumb);
                                            Toast.makeText(context, "赞-1", Toast.LENGTH_SHORT).show();
//                                            holder.zanNum.setText(getItem(position).getZanNumb() + "");
//                                            notifyItemChanged(position);
                                        }
                                        break;
                                    case 1: //点击了复制按钮
                                        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                        clip.setText(getItem(position).getCommentContent().trim()); // 复制
                                        Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }

                            }
                        })
                        .show();
            }
        });

        holder.showMore.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (holder.commentContent.getMaxLines() == 8) {
                    holder.commentContent.setMaxLines(100);
                    holder.showMore.setText("收起");
                } else {
                    holder.commentContent.setMaxLines(8);
                    holder.showMore.setText("展开");
                }
            }
        });
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public NewsComment getItem(int position) {
        return commentList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView userIcon;
        public TextView userName, zanNum, commentContent, commentTime;
        public Button showMore;
        public ImageView commentZan;
        public String[] dialogContent = new String[]{"赞同", "复制"};
        public boolean isZan = false;


        public ViewHolder(View v) {
            super(v);
            userIcon = (CircleImageView) v.findViewById(R.id.comment_user_icon);
            userName = (TextView) v.findViewById(R.id.comment_user_name);
            zanNum = (TextView) v.findViewById(R.id.comment_zanNum);
            commentContent = (TextView) v.findViewById(R.id.comment_content);
            commentTime = (TextView) v.findViewById(R.id.comment_time);
            showMore = (Button) v.findViewById(R.id.comment_item_show);
            commentZan = (ImageView) v.findViewById(R.id.comment_zan);
        }
    }
}


