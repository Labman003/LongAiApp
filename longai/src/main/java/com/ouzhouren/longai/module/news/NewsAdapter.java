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

import java.util.List;
import java.util.Map;

/**
 * Created by 郭泽锋 on 2015/8/13.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> listItems;
    private ImageLoader imageLoader;

    public final class ListItemView {
        public ImageView imageView;
        public TextView title;
        public TextView author;
    }

    public NewsAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        if (listItems == null)
            return 0;
        else
            return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        if(listItems!=null)
            return listItems.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(listItems!=null)
            return position;
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = new ListItemView();
        if(convertView == null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.news_listitem, null);

            listItemView.author= (TextView) convertView.findViewById(R.id.news_listItem_tv_author);
            listItemView.imageView= (ImageView) convertView.findViewById(R.id.news_listItem_iv_icon);
            listItemView.title = (TextView) convertView.findViewById(R.id.news_listItem_tv_title);

            convertView.setTag(listItemView);

        }
        Map<String, Object> listItem = (Map<String, Object>) listItems.get(position);
        String url = (String) listItem.get("icon");
        String currentTitle = (String) listItem.get("title");
        String currentAuthor = (String) listItem.get("author");
        listItemView.imageView.setImageDrawable(null);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //  .showImageOnLoading(R.drawable.ic_stub)
                //  .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        listItemView.author.setText(currentAuthor);
        listItemView.title.setText(currentTitle);
        imageLoader.displayImage(url, listItemView.imageView,options);

        return convertView;
    }
}
