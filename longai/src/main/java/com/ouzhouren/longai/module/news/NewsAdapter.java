package com.ouzhouren.longai.module.news;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ouzhouren.longai.R;
import com.ouzhouren.longai.entity.News;

import java.util.List;
import java.util.Map;

/**
 * Created by 郭泽锋 on 2015/8/13.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private  List<News> newsList;
    private ImageLoader imageLoader;

    public final class NewsItemView {
        public ImageView imageView;
        public TextView title;
        public TextView author;
    }

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        if (newsList == null)
            return 0;
        else
            return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        if (newsList != null)
            return newsList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (newsList != null)
            return position;
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItemView newsItemView = new NewsItemView();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_listitem, null);

            newsItemView.author = (TextView) convertView.findViewById(R.id.news_listItem_tv_author);
            newsItemView.imageView = (ImageView) convertView.findViewById(R.id.news_listItem_iv_icon);
            newsItemView.title = (TextView) convertView.findViewById(R.id.news_listItem_tv_title);

            News listItem = (News) newsList.get(position);
            String url = (String) listItem.getImgUrl();
            String currentTitle = (String) listItem.getTitle();
            String currentAuthor = (String) listItem.getAuthor();
            // listItemView.imageView.setImageDrawable(null);

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    //  .showImageOnLoading(R.drawable.ic_stub)
                    //  .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            newsItemView.author.setText(currentAuthor);
            newsItemView.title.setText(currentTitle);
            imageLoader.displayImage(url, newsItemView.imageView, options);
           // convertView.setTag(listItemView);
        }
        return convertView;
    }
}
