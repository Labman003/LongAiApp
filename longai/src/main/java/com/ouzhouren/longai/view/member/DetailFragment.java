package com.ouzhouren.longai.view.member;


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
import com.ouzhouren.longai.model.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private MyLogger logger = MyLogger.benLog();
    private User user;
    private int position;
    private int size;


    View rootView;
    ImageView placeHolderImage;
    TextView progressTV;
    TextView nameTV;
    TextView nicknameTV;
    TextView emailTV;
    TextView nicknameTitleTv;
    TextView phoneTitleTv;
    TextView phoneTv;
    TextView biographyTitleTv;
    TextView biographyTv;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSize(int size) {
        this.size = size;
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
        progressTV = (TextView) rootView.findViewById(R.id.detail_tv_progress);
        nameTV = (TextView) rootView.findViewById(R.id.detail_tv_name);
        emailTV = (TextView) rootView.findViewById(R.id.detail_tv_mail);
        nicknameTitleTv = (TextView) rootView.findViewById(R.id.detail_tv_nickname_title);
        nicknameTV = ((TextView) rootView.findViewById(R.id.nickname));
        phoneTitleTv = (TextView) rootView.findViewById(R.id.detail_tv_phone_title);
       phoneTv =  ((TextView) rootView.findViewById(R.id.detail_tv_phone));
        biographyTitleTv = (TextView) rootView.findViewById(R.id.android_tv_biography_title);
        biographyTv = (TextView) rootView.findViewById(R.id.detail_tv_biography);
        //  placeHolderImage.setViewName("photo"+getIntent().getIntExtra("position",0));
        logger.i("2");
        ImageLoader.getInstance().displayImage(user.getProfilepic(), placeHolderImage);
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
        bitmap=ImageLoader.getInstance().loadImageSync(user.getProfilepic(),options);
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
        Palette.Swatch lightVibrant = palette.getLightVibrantSwatch();//亮强调
        Palette.Swatch lightMuted = palette.getLightMutedSwatch();//亮轻柔
        Palette.Swatch DarkVibrant = palette.getDarkVibrantSwatch();//暗强调色
        Palette.Swatch DarkMuted = palette.getDarkMutedSwatch();//亮强调色
        Palette.Swatch muted = palette.getMutedSwatch();//轻柔色
        Palette.Swatch vibrant = palette.getVibrantSwatch();//强调色
        int pSize = availablePaleteItems.size();



        progressTV.setText(String.format("%s/%s",String.valueOf(++position),String.valueOf(size)));
        emailTV.setText(selectedUser.getEmail());
        nameTV.setText(selectedUser.getName());
       phoneTv.setText(selectedUser.getPhone());
       nicknameTV.setText(selectedUser.getNickname());

//设置颜色
        //设置窗口背景为黑调色
//        getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkVibrantSwatch().getRgb()));
        rootView.setBackgroundDrawable(new ColorDrawable(muted.getRgb()));

//        if (lightVibrant != null)
//            nameTV.setTextColor(lightVibrant.getRgb());

        if (DarkMuted != null){
            int rgb = DarkMuted.getRgb();
            emailTV.setTextColor(rgb);
            nicknameTitleTv.setTextColor(rgb);
            phoneTitleTv.setTextColor(rgb);
            biographyTitleTv.setTextColor(rgb);
            nameTV.setTextColor(rgb);
            phoneTv.setTextColor(rgb);
            nicknameTV.setTextColor(rgb);
            biographyTv.setTextColor(rgb);
        }


    }


}
