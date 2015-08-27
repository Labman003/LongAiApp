package com.ouzhouren.longai.module.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ouzhouren.longai.R;

/**
 * Created by 郭泽锋 on 2015/8/17.
 */
public class NewsDetails extends AppCompatActivity {
    private TextView titleTV, authorTV, zanNumb, commentNumb;
    private Button newsComment, newsDetailsBack, newsDetailsZan;
    Toolbar mToolbar;
    boolean isZan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        newsComment = (Button) findViewById(R.id.news_comment);
        newsDetailsBack = (Button) findViewById(R.id.news_details_back);
        newsDetailsZan = (Button) findViewById(R.id.news_details_zan);
        zanNumb = (TextView) findViewById(R.id.news_zanNum);
        commentNumb = (TextView) findViewById(R.id.news_commentNum);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.news_comment:
                        Intent intent = new Intent(NewsDetails.this, NewsComment.class);
                        startActivity(intent);
                        break;
                    case R.id.news_details_back:
                        finish();
                        break;
                    case R.id.news_details_zan:
                        String text = zanNumb.getText().toString();
                        int numb = Integer.parseInt(text);
                        if (!isZan) {
                            numb++;
                            zanNumb.setText(numb + "");
                            newsDetailsZan.setBackgroundResource(R.drawable.icon_zan_orange);
                            Toast.makeText(NewsDetails.this, "赞+1", Toast.LENGTH_SHORT).show();
                            isZan = true;
                        } else {
                            numb--;
                            zanNumb.setText(numb + "");
                            newsDetailsZan.setBackgroundResource(R.drawable.icon_zan_white);
                            Toast.makeText(NewsDetails.this, "赞-1", Toast.LENGTH_SHORT).show();
                            isZan = false;
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        newsComment.setOnClickListener(listener);
        newsDetailsBack.setOnClickListener(listener);
        newsDetailsZan.setOnClickListener(listener);
        mToolbar = (Toolbar) findViewById(R.id.news_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);


//        titleTV = (TextView) findViewById(R.id.news_details_titleTV);
//        authorTV = (TextView) findViewById(R.id.news_details_authorTV);
//        int position = getIntent().getIntExtra("position", 0);
//        News newsItem = NewsFragment.newsLists.get(position);

//        titleTV.setText(newsItem.getTitle());
//        authorTV.setText(newsItem.getAuthor());
    }
}
