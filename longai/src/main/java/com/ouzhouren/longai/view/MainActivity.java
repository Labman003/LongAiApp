package com.ouzhouren.longai.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.constant.ConstantServer;
import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.presenter.NavPresenter;
import com.ouzhouren.longai.presenter.NavViewInterface;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements NavViewInterface {
    private final int FRAGMENT_SEARCH = 0;
    private final int FRAGMENT_TALK = 1;
    private final int FRAGMENT_NEWS = 2;
    private final int FRAGMENT_EVENT = 3;

    Activity mAc;
    ImageView profileView;
    TextView nickNameTv;
    TextView userNameTv;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    ViewPager mViewPager;
    MainFragmentStatePagerAdapter pagerAdapter;
    Bitmap bitmap;
    ImageButton cameraIB;
    NavPresenter navPresenter;
    SweetAlertDialog sadlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAc = this;
        findView();
        init(mAc);
        cameraIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropPic();
            }
        });
    }


    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.welcomanim_title_login, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        navigation = (NavigationView) findViewById(R.id.navigation);
        View header = navigation.inflateHeaderView(R.layout.nav_header);
        profileView = (ImageView) header.findViewById(R.id.result);
        nickNameTv = (TextView) header.findViewById(R.id.nav_tv_nick_name);
        userNameTv = (TextView) header.findViewById(R.id.nav_tv_username);
        cameraIB = (ImageButton) header.findViewById(R.id.nav_ib_camera);
    }

    private void init(final Activity mAc) {
        sadlg = new SweetAlertDialog(mAc, SweetAlertDialog.SUCCESS_TYPE);
        navPresenter = new NavPresenter(this, this);
        navPresenter.initNav();
        navPresenter.locate(mAc);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
//                    case R.id.navItem_message:
//                        break;
                    case R.id.navItem_album:
                        goAlbum();
                        break;
                    case R.id.navItem_vip:
                        Snackbar.make(drawerLayout, "暂未开通VIP特别服务，敬请期待!", Snackbar.LENGTH_SHORT)
                                .setAction("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .show();
                        break;
                    case R.id.navItem_location:
                        navPresenter.locate(mAc);
                        break;
                    case R.id.navItem_setting:
                        goSettings();
                        break;
                    case R.id.navItem_off:
                        quitApplication();
                        break;
                }
                return false;
            }
        });
        /******************* Tab & ViewPager *******/
        // 设置ViewPager的数据等
        pagerAdapter = new MainFragmentStatePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());

        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        MyLogger logger = MyLogger.benLog();
        if (resultCode == RESULT_OK) {
            refreshProfilePic(Crop.getOutput(result));
            bitmap = ((BitmapDrawable) profileView.getDrawable()).getBitmap();
            navPresenter.uploadProfilePic(bitmap, mAc);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        navPresenter.detectNetwork(this);
    }

    @Override
    public void cropPic() {
        Crop.pickImage(MainActivity.this);
    }

    @Override
    public void refreshProfilePic(Uri uri) {
        profileView.setImageURI(uri);

    }

    @Override
    public void refreshNav(User user) {
       ImageLoader.getInstance().displayImage(ConstantServer.PRFIX_PROFILE_PIC+user.getProfilepic(),profileView);
        nickNameTv.setText(user.getNickname());
        userNameTv.setText(user.getName());
    }

    @Override
    public void refreshLocation(String locationdescribe) {
        navigation.getMenu().findItem(R.id.navItem_location).setTitle("位置：" + locationdescribe);
    }

    @Override
    public void quitApplication() {
        this.finish();
    }

    @Override
    public void goContacts() {

    }

    @Override
    public void goAlbum() {

    }

    @Override
    public void goSettings() {

    }

    @Override
    public void showLocateFail() {
        navigation.getMenu().findItem(R.id.navItem_location).setTitle("未知位置：定位失败");
        Snackbar.make(rootLayout, "定位失败，请开启GPS并于信号良好的地方尝试定位!", Snackbar.LENGTH_SHORT)
                .setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    @Override
    public void showNetworkDisable() {
        TextView networkStateTv = (TextView) findViewById(R.id.main_tv_network_state);
        networkStateTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNetworkDisable() {
        TextView networkStateTv = (TextView) findViewById(R.id.main_tv_network_state);
        networkStateTv.setVisibility(View.GONE);
    }

    @Override
    public void showUploadSuccess() {
        sadlg.dismiss();
        sadlg = new SweetAlertDialog(mAc, SweetAlertDialog.SUCCESS_TYPE);
        sadlg.show();
    }

    @Override
    public void showUploadFail(String s) {
        sadlg.dismiss();
        sadlg = new SweetAlertDialog(mAc, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(s);
        sadlg.show();
    }

    @Override
    public void showUploading(long total, long len) {
        sadlg.dismiss();
        sadlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(String.valueOf(total) + "/" + String.valueOf(len));
        sadlg.show();
    }


    //再按一次退出龙爱
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                drawerLayout.openDrawer(Gravity.LEFT);
                Toast.makeText(getApplicationContext(), "再按一次退出龙爱", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
