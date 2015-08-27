package com.ouzhouren.longai.module.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ouzhouren.longai.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 郭泽锋 on 2015/8/20.
 */
public class NewsCommentEditPage extends AppCompatActivity {
    private Button commentEditBack, commentEditSend;
    private EditText commentContentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_edit_page);

        commentEditBack = (Button) findViewById(R.id.comment_edit_back);
        commentEditSend = (Button) findViewById(R.id.comment_edit_send);
        commentContentEdit = (EditText) findViewById(R.id.comment_content_edit);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.comment_edit_back:
                        finish();
                        break;
                    case R.id.comment_edit_send:
                        Intent intent = new Intent(NewsCommentEditPage.this, NewsComment.class);
                        String content = commentContentEdit.getText().toString();
                        intent.putExtra("commentContent", content);
                        setResult(RESULT_OK, intent);
                        new SweetAlertDialog(NewsCommentEditPage.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("发送成功")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        finish();
                                    }
                                })
                                .show();
                        break;
                }
            }
        };
        commentEditBack.setOnClickListener(listener);
        commentEditSend.setOnClickListener(listener);
    }
}
