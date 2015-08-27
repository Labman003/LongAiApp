package com.ouzhouren.longai.view.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.News;

import java.util.List;

/**
 * Created by 郭泽锋 on 2015/8/13.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private Context context;
    private List<News> newsList;
    private ImageLoader imageLoader;

    public static interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        this.newsList = newsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context
        ).inflate(R.layout.news_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(newsList.get(position).getTitle());
        holder.author.setText(newsList.get(position).getAuthor());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.drawable.ic_stub)
                // .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
      //  String url = newsList.get(position).getImgUrl();
    //    imageLoader.displayImage(url, holder.imageView, options);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
//                    return false;
//                }
//            });
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView author;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.news_listItem_iv_icon);
            title = (TextView) v.findViewById(R.id.news_listItem_tv_title);
            author = (TextView) v.findViewById(R.id.news_listItem_tv_author);
        }
    }

}