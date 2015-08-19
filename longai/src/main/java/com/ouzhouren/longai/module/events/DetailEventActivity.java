package com.ouzhouren.longai.module.events;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.ouzhouren.longai.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetailEventActivity extends AppCompatActivity {

    private WebView webView;
    private FlatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        if(getIntent().getBooleanExtra("enroll_directlly",false)==true){
            enroll();
        }
        webView = (WebView) findViewById(R.id.webview);
        button = (FlatButton) findViewById(R.id.detail_event_fb_enroll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enroll();
            }
        });
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(false);

        //打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebViewClient(new WebViewClient(){
            SweetAlertDialog pDialog = new SweetAlertDialog(DetailEventActivity.this, SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText("Loading");
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pDialog.cancel();
            }
        });

        //打开页面时， 自适应屏幕：
        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);

        //加载需要显示的网页
        webView.loadUrl("http://www.baidu.com/");
    }

    private void enroll() {
        new SweetAlertDialog(DetailEventActivity.this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("确定报名吗？")
                .setConfirmText("嗯嗯嗯，要报名~")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        button.getAttributes().setTheme(FlatUI.ORANGE,getResources());
                        button.setText("已报名成功");
                        sDialog
                                .setTitleText("报名成功!")
                                .setContentText("一切安排妥当，届时于活动地点前台凭身份证即可入场，记得带身份证哟!")
                                .setConfirmText("记得啦")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog
                                                .setTitleText("真的记得带身份证了吗？")
                                                .setContentText(null)
                                                .setConfirmText("不要再说了，我记得！")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.WARNING_TYPE);
                                    }
                                })
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_event, menu);
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
