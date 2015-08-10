package com.ouzhouren.longai;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.soundcloud.android.crop.Crop;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private final int FRAGMENT_SEARCH = 0;
    private final int FRAGMENT_TALK = 1;
    private final int FRAGMENT_NEWS = 2;
    private final int FRAGMENT_EVENT = 3;

    Activity mAc;
    SubsamplingScaleImageView resultView;
    ImageView imageView;
    TextView textView;
    CoordinatorLayout rootLayout;
    FloatingActionButton fabBtn;
    Toolbar toolbar;
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NavigationView navigation;
    SwipeRefreshLayout mSwipeRefreshWidget;
    ViewPager mViewPager;
    MainFragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAc = this;
//        resultView = (SubsamplingScaleImageView)findViewById(R.id.imageView);
//        imageView = (ImageView) findViewById(R.id.imageView2);
//        textView = (TextView) findViewById(R.id.text);


      /*****************选择图片裁剪********************/
      //  Crop.pickImage(this);
        /*******************FB******************/
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "收到彭菊的来信!", Snackbar.LENGTH_SHORT)
                        .setAction("拆开", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });
        /*****************ToolBar*********************/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /***************Drawer*********************/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.welcomanim_title_login, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*********bar折叠*************/
     //   collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
      //  collapsingToolbarLayout.setTitle("龙爱");
        /******************抽屉导航************************/
        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        break;
                    case R.id.navItem2:
                        break;
                    case R.id.navItem3:
                        break;
                }
                return false;
            }
        });
        /*****************下拉加载***********************************/
       // mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        /***************sweet对话框***************/
//        //MD进度条
//        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();
//        //普通消息
//        new SweetAlertDialog(this)
//                .setTitleText("Here's a message!")
//                .show();

//        //确认后改变样式
//        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setConfirmText("Yes,delete it!")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog
//                                .setTitleText("Deleted!")
//                                .setContentText("Your imaginary file has been deleted!")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                    }
//                })
//                .show();
        /***************TimePicker****************/
//        Calendar now = Calendar.getInstance();
////        DatePickerDialog dpd = DatePickerDialog.newInstance(
////                MainActivity.this,
////                now.get(Calendar.YEAR),
////                now.get(Calendar.MONTH),
////                now.get(Calendar.DAY_OF_MONTH)
////        );
////        dpd.show(getFragmentManager(), "Datepickerdialog");
//        TimePickerDialog tpd = TimePickerDialog.newInstance(
//                MainActivity.this,
//                now.get(Calendar.HOUR_OF_DAY),
//                now.get(Calendar.MINUTE),
//                false
//        );
//        tpd.setThemeDark(false);
//        tpd.vibrate(false);
//        tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialogInterface) {
//                Log.d("TimePicker", "Dialog was cancelled");
//            }
//        });
//        tpd.show(getFragmentManager(), "Timepickerdialog");
        /******************* Tab & ViewPager *******/
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        // 设置ViewPager的数据等
        pagerAdapter = new MainFragmentStatePagerAdapter(getSupportFragmentManager());
       mViewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    //    tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        /******************************************/
//        String url = "api.douban.com";
//        StringRequest req = new StringRequest(url).addUrlPrifix("http://").addUrlSuffix("/v2/book/1220562");
//        LiteHttpUtil.getLiteHttp(mAc).executeAsync(req.setHttpListener(new HttpListener<String>() {
//            @Override
//            public void onSuccess(String s, Response<String> response) {
//                // 成功：主线程回调，反馈一个string
//                new SweetAlertDialog(mAc)
//                        .setTitleText("Here's a message!")
//                        .setContentText(s+response.resToString())
//                        .show();
//            }
//
//            @Override
//            public void onFailure(HttpException e, Response<String> response) {
//                // 失败：主线程回调，反馈异常
//              //  logger.i("faile exception:" + e + "----response:" + response);
//            }
//        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        if (resultCode == RESULT_OK) {
           // resultView.setImageURI(Crop.getOutput(result));
          //  imageView.setImage(ImageSource.resource(R.drawable.monkey));
// ... or ...
          //  imageView.setImage(ImageSource.asset("map.png"))
// ... or ...
            resultView.setImage(ImageSource.uri(Crop.getOutput(result)));

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
    public void onDateSet(DatePickerDialog datePickerDialog,  int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        //普通消息
        new SweetAlertDialog(this)
                .setTitleText(date)
                .show();
    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {

    }
    //再按一次退出速读
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出速读", Toast.LENGTH_SHORT).show();
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
