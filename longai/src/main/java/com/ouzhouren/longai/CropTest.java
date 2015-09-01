package com.ouzhouren.longai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropTest extends AppCompatActivity {

    private ImageView resultView ;
    private ImageView bitmapView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_test);
        resultView = (ImageView) findViewById(R.id.image1);
        bitmapView = (ImageView) findViewById(R.id.image2);
        Crop.pickImage(this);
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
         //   bitmapView.setImageBitmap(bitmap);
            logger.i("beforesave");
            saveBitmapFile(bitmap) ;
            logger.i("aftersave");
            FileInputStream fis = null;
            try {
             fis = new FileInputStream(new File(Environment.getExternalStorageDirectory()+"/01.jpg"));
                logger.i(new File(Environment.getExternalStorageDirectory()+"/01.jpg").getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Bitmap bff= BitmapFactory.decodeStream(fis);
            bitmapView.setImageBitmap(bff);
            upload();

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void upload(){
        /*************upload**********/
        final ProgressDialog postProgress = new ProgressDialog(this);
        postProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        postProgress.setIndeterminate(false);
        postProgress.show();
        String uploadUrl= "http://192.168.1.113:8080/longai/userIcon";
        final StringRequest postRequest = new StringRequest(uploadUrl)
                .setMethod(HttpMethods.Post)
                .setHttpListener(new HttpListener<String>(true, false, true) {
                    @Override
                    public void onSuccess(String s, Response<String> response) {
                        //                                postProgress.dismiss();
                        HttpUtil.showTips(CropTest.this, "Upload Success", s);
                        response.printInfo();
                    }

                    @Override
                    public void onFailure(HttpException e, Response<String> response) {
                        postProgress.dismiss();
                        HttpUtil.showTips(CropTest.this, "Upload Failed", e.toString());
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
        postRequest.setHttpBody(body);
        LiteHttpUtil.getLiteHttp(this).executeAsync(postRequest);
    }
    //    Bitmap对象保存味图片文件
    public void saveBitmapFile(Bitmap bitmap){
        File file=new File(Environment.getExternalStorageDirectory()+"/01.jpg");//将要保存图片的路径
        MyLogger logger = MyLogger.benLog();
        logger.i(Environment.getExternalStorageDirectory());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crop_test, menu);
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
