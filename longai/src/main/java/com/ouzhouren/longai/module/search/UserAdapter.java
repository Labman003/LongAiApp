package com.ouzhouren.longai.module.search;

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
import com.ouzhouren.longai.entity.User;

import java.util.List;

class UserAdapter extends BaseAdapter {
    private Context mContext;
    private List<User> users;
    private ImageLoader imageLoader;

    public UserAdapter(Context ctx, List<User> users) {
        mContext = ctx;
        imageLoader = ImageLoader.getInstance(); // Get singleton instance
        this.users = users;
    }

    class ViewHolder {
        TextView userTitle;
        ImageView userImage;
    }

    @Override
    public int getCount() {
//        if (users == null) {
//            return 0;
//        } else
//            return users.size();
        return 20;
    }

    @Override
    public Object getItem(int i) {
        if (users == null) {
            return null;
        } else
            return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        if (users == null) {
            return -1;
        } else
            return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {

            // inflate the layout
            convertView = LayoutInflater.from(mContext).inflate(R.layout.user_card, null);

            // Set up the view holder
            viewHolder = new ViewHolder();
            viewHolder.userTitle = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.userImage = (ImageView) convertView.findViewById(R.id.user_image);

            // store the viewholder with the view
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User currentUser = users.get(position);
        viewHolder.userTitle.setText(currentUser.getName());

        // Load the user image asynchronously
        viewHolder.userImage.setImageDrawable(null);
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
              //  .showImageOnLoading(R.drawable.ic_stub)
              //  .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(currentUser.getPicture(), viewHolder.userImage,options);
        // Set the proper view name to get the transition well managed
        // convertView.setViewName("photo" + position);
        return convertView;
    }
}