package com.ouzhouren.longai.view.events;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cengalabs.flatui.views.FlatButton;
import com.ouzhouren.longai.R;

/**
 * Created by BenPC on 2015/8/18.
 */
public class EventsFragmentAdapter extends RecyclerView.Adapter<EventsFragmentAdapter.ViewHolder>{
    public EventsFragmentAdapter() {
    }

    //...
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public interface OnStateButtonClickLitener
    {
        void onStateButtonClick(View view, int position);
    }

    private OnStateButtonClickLitener mOnStateButtonClickLitener;
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public void setOnStateButtonClickLitener(OnStateButtonClickLitener mOnStateButtonClickLitener)
    {
        this.mOnStateButtonClickLitener = mOnStateButtonClickLitener;
    }
    @Override
    public EventsFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final EventsFragmentAdapter.ViewHolder holder, int position) {
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
        if (mOnStateButtonClickLitener != null)
        {
            holder.state.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnStateButtonClickLitener.onStateButtonClick(holder.state, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventTitleTv;
        public ImageView eventImageIv;
        public FlatButton state;

        public ViewHolder(View itemView) {
            super(itemView);
            eventImageIv = (ImageView) itemView.findViewById(R.id.event_card_iv_image);
            eventTitleTv = (TextView) itemView.findViewById(R.id.event_card_tv_title);
            state= (FlatButton) itemView.findViewById(R.id.event_card_fb_state);
        }
    }

}
