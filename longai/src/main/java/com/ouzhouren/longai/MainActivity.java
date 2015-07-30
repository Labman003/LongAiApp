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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.soundcloud.android.crop.Crop;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Activity mAc;
    SubsamplingScaleImageView resultView;
    ImageView imageView;
    TextView textView;
    private CoordinatorLayout rootLayout;
    private FloatingActionButton fabBtn;
    Toolbar toolbar;
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAc = this;
        resultView = (SubsamplingScaleImageView)findViewById(R.id.imageView);
        imageView = (ImageView) findViewById(R.id.imageView2);
        textView = (TextView) findViewById(R.id.text);
/************ Initialize ImageLoader with configuration.***********************/
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
              //  .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
               // .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);//全局初始化此配置
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage("http://img4.douban.com/view/photo/photo/public/p2255230989.jpg",imageView);
      /*****************选择图片裁剪********************/
      //  Crop.pickImage(this);
        /*******************MD******************/
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
        /*********************Tab***********************/
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("缘分"));
        tabLayout.addTab(tabLayout.newTab().setText("消息"));
        tabLayout.addTab(tabLayout.newTab().setText("课室"));
        tabLayout.addTab(tabLayout.newTab().setText("活动"));
        /***************Drawer*********************/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.welcomanim_title_login, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*********bar折叠*************/
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
       // collapsingToolbarLayout.setTitle("龙爱私房菜");
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
}
