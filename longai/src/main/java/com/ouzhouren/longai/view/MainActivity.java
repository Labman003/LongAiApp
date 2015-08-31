package com.ouzhouren.longai.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.request.content.multi.FilePart;
import com.litesuits.http.request.content.multi.MultipartBody;
import com.litesuits.http.request.content.multi.StringPart;
import com.litesuits.http.request.param.HttpMethods;
import com.litesuits.http.response.Response;
import com.litesuits.http.utils.HttpUtil;
import com.ouzhouren.base.cache.ACache;
import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.common.utils.UriUtil;
import com.soundcloud.android.crop.Crop;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private final int FRAGMENT_SEARCH = 0;
    private final int FRAGMENT_TALK = 1;
    private final int FRAGMENT_NEWS = 2;
    private final int FRAGMENT_EVENT = 3;

    Activity mAc;
    ImageView resultView;
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
    private Bitmap bitmap;

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
//        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
//        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
//        fabBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(rootLayout, "收到彭菊的来信!", Snackbar.LENGTH_SHORT)
//                        .setAction("拆开", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                            }
//                        })
//                        .show();
//            }
//        });
        /*****************ToolBar*********************/
        toolbar = (Toolbar) findViewById(R.id.news_toolbar);
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
        ACache aCache = ACache.get(mAc);
        String locationdescribe = aCache.getAsString("locationdescribe");
        if(locationdescribe!=null&&locationdescribe.length()>0){
            navigation.getMenu().findItem(R.id.navItem_location).setTitle("位置："+locationdescribe);
        }
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem_message:
                        break;
                    case R.id.navItem_album:
                        break;
                    case R.id.navItem_vip:
                        break;
                    case R.id.navItem_location:
                        break;
                }
                return false;
            }
        });
        View header = navigation.inflateHeaderView(R.layout.nav_header);
        resultView = (ImageView) header.findViewById(R.id.result);
        ImageButton cameraIB = (ImageButton) header.findViewById(R.id.nav_ib_camera);
        cameraIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(MainActivity.this);
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
        /********************LiteHttp**********************/
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
        MyLogger logger = MyLogger.benLog();
        if (resultCode == RESULT_OK) {
            resultView.setImageURI(Crop.getOutput(result));
            BitmapDrawable bd = (BitmapDrawable) resultView.getDrawable();
            bitmap = bd.getBitmap();
            logger.i("beforesave");
            saveBitmapFile(bitmap) ;
            logger.i("aftersave");
          //  imageView.setImage(ImageSource.resource(R.drawable.monkey));
// ... or ...
          //  imageView.setImage(ImageSource.asset("map.png"))
// ... or ...
          //  resultView.setImage(ImageSource.uri(Crop.getOutput(result)));
//            MultipartBody body = new MultipartBody();
//            body.addPart(new StringPart("key1", "hello"));
//            body.addPart(new StringPart("key2", "很高兴见到你", "utf-8", null));
//            body.addPart(new BytesPart("key3", new byte[]{1, 2, 3}));
//            body.addPart(new FilePart("pic", new File("/sdcard/aaa.jpg"), "image/jpeg"));
//            body.addPart(new InputStreamPart("litehttp", fis, "litehttp.txt", "text/plain"));
//            postRequest.setHttpBody(body);

            File file = UriUtil.getFromMediaUri(this, getContentResolver(), Crop.getOutput(result));
            logger.i(file.getAbsolutePath());
            /*************upload**********/
            final ProgressDialog postProgress = new ProgressDialog(this);
            postProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            postProgress.setIndeterminate(false);
            postProgress.show();
            String uploadUrl= "http://192.168.1.106:8080/longai/userIcon";
            final StringRequest postRequest = new StringRequest(uploadUrl)
                    .setMethod(HttpMethods.Post)
                    .setHttpListener(new HttpListener<String>(true, false, true) {
                        @Override
                        public void onSuccess(String s, Response<String> response) {
                            //                                postProgress.dismiss();
                            HttpUtil.showTips(MainActivity.this, "Upload Success", s);
                            response.printInfo();
                        }

                        @Override
                        public void onFailure(HttpException e, Response<String> response) {
                            postProgress.dismiss();
                            HttpUtil.showTips(MainActivity.this, "Upload Failed", e.toString());
                        }

                        @Override
                        public void onUploading(AbstractRequest<String> request, long total, long len) {
                            postProgress.setMax((int) total);
                            postProgress.setProgress((int) len);
                        }
                    });
            MultipartBody body = new MultipartBody();
            body.addPart(new StringPart("userId", "3"));
            //body.addPart(new FilePart("userIcon",file, "image/jpeg"));
          //  body.addPart(new FilePart("userIcon",file));
            body.addPart(new FilePart("userIcon",new File(Environment.getExternalStorageDirectory()+"/01.jpg"), "image/jpeg"));
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(file);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            body.addPart(new InputStreamPart("litehttp", fis, "litehttp.txt", "text/plain"));
//            body.addPart(new  InputStreamPart("userIcon",fis,file.getName(),"image/jpeg"));
           // body.addPart(new InputStreamPart("userIcon",fis,file.getName()));
            postRequest.setHttpBody(body);
            LiteHttpUtil.getLiteHttp(mAc).executeAsync(postRequest);

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    Bitmap对象保存味图片文件
    public void saveBitmapFile(Bitmap bitmap){
        File file=new File(Environment.getExternalStorageDirectory()+"/01.jpg");//将要保存图片的路径
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
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
    //再按一次退出龙爱
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
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

    @Override
    protected void onResume() {
        super.onResume();
        new ReactiveNetwork().observeConnectivity(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityStatus.isEqualTo(ConnectivityStatus.OFFLINE))
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override public void call(ConnectivityStatus connectivityStatus) {
                        // do something with connectivityStatus, which will be WIFI_CONNECTED
                        TextView networkStateTv = (TextView) findViewById(R.id.main_tv_network_state);
                        networkStateTv.setVisibility(View.VISIBLE);
                    }
                });
        new ReactiveNetwork().observeConnectivity(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityStatus.isEqualTo(ConnectivityStatus.MOBILE_CONNECTED))
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override public void call(ConnectivityStatus connectivityStatus) {
                        // do something with connectivityStatus, which will be WIFI_CONNECTED
                        TextView networkStateTv = (TextView) findViewById(R.id.main_tv_network_state);
                        networkStateTv.setVisibility(View.GONE);
                    }
                });
        new ReactiveNetwork().observeConnectivity(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityStatus.isEqualTo(ConnectivityStatus.WIFI_CONNECTED))
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override public void call(ConnectivityStatus connectivityStatus) {
                        // do something with connectivityStatus, which will be WIFI_CONNECTED
                        TextView networkStateTv = (TextView) findViewById(R.id.main_tv_network_state);
                        networkStateTv.setVisibility(View.GONE);
                    }
                });
    }
}
