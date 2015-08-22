package com.ouzhouren;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Gradient;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaiduMapTestActivity extends AppCompatActivity {
    MyLogger logger = MyLogger.benLog();
    MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    private Marker mMarkerD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map_test);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //卫星地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        /************标注覆盖物****************/
        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.welcome_logo_wechat);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap)
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽;
//在地图上添加Marker，并显示
        //将marker添加到地图上
        mBaiduMap.addOverlay(option);
        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
                logger.i("拖拽中");
            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                logger.i("拖拽结束");
                //        针对已添加在地图上的标注覆盖物，可利用如下方法进行修改和删除操作
               // mMarkerD.remove();   //调用Marker对象的remove方法实现指定marker的删除
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
                logger.i("开始拖拽");
            }
        });
/*********************Marker增加动画的能力****************/
        // 通过marker的icons设置一组图片，再通过period设置多少帧刷新一次图片资源
        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.welcome_logo_qq);
        BitmapDescriptor bdB = BitmapDescriptorFactory
                .fromResource(R.drawable.welcome_logo_wechat);
        BitmapDescriptor bdC = BitmapDescriptorFactory
                .fromResource(R.drawable.welcome_logo_weibo);
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(bdA);
        giflist.add(bdB);
        giflist.add(bdC);
        LatLng pt = new LatLng(30.963175, 111.400244);
        OverlayOptions ooD = new MarkerOptions().position(pt).icons(giflist)
                .zIndex(0).period(10);
        mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                弹出窗覆盖物的实现方式如下，开发者可利用此接口，构建具有更强交互性的地图页面。
//创建InfoWindow展示的view
                Button button = new Button(getApplicationContext());
//定义用于显示该InfoWindow的坐标点
                LatLng pt = new LatLng(39.86923, 116.397428);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
//显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);
                return false;
            }
        });

/********************几何覆盖物********************************/
        //定义多边形的五个顶点
        LatLng pt1 = new LatLng(39.93923, 116.357428);
        LatLng pt2 = new LatLng(39.91923, 116.327428);
        LatLng pt3 = new LatLng(39.89923, 116.347428);
        LatLng pt4 = new LatLng(39.89923, 116.367428);
        LatLng pt5 = new LatLng(39.91923, 116.387428);
        List<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);
        pts.add(pt3);
        pts.add(pt4);
        pts.add(pt5);
//构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Stroke(5, 0xAA00FF00))
                .fillColor(0xAAFFFF00);
//在地图上添加多边形Option，用于显示
        mBaiduMap.addOverlay(polygonOption);
/*******************热力图***********************************/
//        第一步，设置颜色变化：
//设置渐变颜色值
        int[] DEFAULT_GRADIENT_COLORS = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0) };
//设置渐变颜色起始值
        float[] DEFAULT_GRADIENT_START_POINTS = { 0.2f, 1f };
//构造颜色渐变对象
        Gradient gradient = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);

//        第二步，准备数据：
//以下数据为随机生成地理位置点，开发者根据自己的实际业务，传入自有位置数据即可
        List<LatLng> randomList = new ArrayList<LatLng>();
        Random r = new Random();
        for (int i = 0; i < 500; i++) {
            // 116.220000,39.780000 116.570000,40.150000
            int rlat = r.nextInt(370000);
            int rlng = r.nextInt(370000);
            int lat = 39780000 + rlat;
            int lng = 116220000 + rlng;
            LatLng ll = new LatLng(lat / 1E6, lng / 1E6);
            randomList.add(ll);
        }
//        第三步，添加、显示热力图：
//在大量热力图数据情况下，build过程相对较慢，建议放在新建线程实现
        HeatMap heatmap = new HeatMap.Builder()
                .data(randomList)
                .gradient(gradient)
                .build();
//在地图上添加热力图
        mBaiduMap.addHeatMap(heatmap);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
     protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_baidu_map_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
