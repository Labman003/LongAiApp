package com.ouzhouren.longai.view.talk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ouzhouren.longai.R;

/**
 * Created by BenPC on 2015/8/18.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    public ContactsAdapter() {
    }

    //...
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
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
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContactsAdapter.ViewHolder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public ImageView avatarIv;
        public TextView msgTv;
        public TextView timeTv;
        public TextView unreadTv;

        public ViewHolder(View itemView) {
            super(itemView);
            avatarIv = (ImageView) itemView.findViewById(R.id.event_card_iv_image);
            nameTv = (TextView) itemView.findViewById(R.id.event_card_tv_title);
            msgTv = (TextView) itemView.findViewById(R.id.event_card_tv_title);
            timeTv = (TextView) itemView.findViewById(R.id.event_card_tv_title);
            unreadTv = (TextView) itemView.findViewById(R.id.event_card_tv_title);
        }
    }

}
