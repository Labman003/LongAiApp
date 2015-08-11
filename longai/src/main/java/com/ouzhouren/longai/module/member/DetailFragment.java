package com.ouzhouren.longai.module.member;


import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.entity.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private MyLogger logger = MyLogger.benLog();
    private User user;

    View rootView;
    ImageView placeHolderImage;
    TextView nameTV;
    TextView nicknameTV;
    TextView emailTV;
    TextView nicknameTitleTv;
    TextView phoneTitleTv;
    TextView phoneTv;
    TextView biographyTitleTv;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        logger.i("1");
        //find & set text
        placeHolderImage = (ImageView) rootView.findViewById(R.id.detail_iv_user_photo);
        nameTV = (TextView) rootView.findViewById(R.id.detail_tv_name);
        emailTV = (TextView) rootView.findViewById(R.id.detail_tv_mail);
        nicknameTitleTv = (TextView) rootView.findViewById(R.id.detail_tv_nickname_title);
        nicknameTV = ((TextView) rootView.findViewById(R.id.nickname));
        phoneTitleTv = (TextView) rootView.findViewById(R.id.detail_tv_phone_title);
       phoneTv =  ((TextView) rootView.findViewById(R.id.detail_tv_phone));
        biographyTitleTv = (TextView) rootView.findViewById(R.id.android_tv_biography_title);
        //  placeHolderImage.setViewName("photo"+getIntent().getIntExtra("position",0));
        logger.i("2");
        ImageLoader.getInstance().displayImage(user.getPicture(), placeHolderImage);
        logger.i("3");
        // placeHolderImage.setImageBitmap(bitmap);
        Bitmap bitmap = null;
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //  .showImageOnLoading(R.drawable.ic_stub)
                //  .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        bitmap=ImageLoader.getInstance().loadImageSync(user.getPicture(),options);
        logger.i("4");
        if (bitmap != null) {
            // With palette you can get the main colors of the give bitmap
            Palette palette = Palette.generate(bitmap);
            setUITextAndColor(palette, user);
        }
        logger.i("5");
        return rootView;
    }

    public void setUITextAndColor(Palette palette, User selectedUser) {
        List<Palette.Swatch> availablePaleteItems = palette.getSwatches();
        Palette.Swatch lightVibrant = palette.getLightVibrantSwatch();//强调色
        Palette.Swatch lightMuted = palette.getLightMutedSwatch();//轻柔色
        int pSize = availablePaleteItems.size();

//设置窗口背景为黑调色
//        getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkVibrantSwatch().getRgb()));
        rootView.setBackgroundDrawable(new ColorDrawable(palette.getDarkVibrantSwatch().getRgb()));

        nameTV.setText(selectedUser.getName());
        emailTV.setText(selectedUser.getEmail());
       phoneTv.setText(selectedUser.getPhone());
       nicknameTV.setText(selectedUser.getUserName());

//设置颜色
        if (lightVibrant != null)
            nameTV.setTextColor(lightVibrant.getRgb());

        if (lightMuted != null)
            emailTV.setTextColor(lightMuted.getRgb());

        nicknameTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
        phoneTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
        biographyTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
    }


}
