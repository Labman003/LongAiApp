package com.ouzhouren.longai.view.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.News;
import com.ouzhouren.longai.view.news.NewsFragment;

/**
 * Created by 郭泽锋 on 2015/8/17.
 */
public class NewsDetails extends AppCompatActivity {
    private TextView newsDetailsTitle, newsDetailsAuthor, newsDetailsZanNumb, newsDetailsCommentNumb,newsDetailsContent;
    private Button newsDetailsComment, newsDetailsBack, newsDetailsZan;
    private Toolbar mToolbar;
    boolean isZan = false;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        init();
        showNewsDetails();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.news_details_comment:
                        Intent intent = new Intent(mActivity, NewsCommentPage.class);
                        startActivity(intent);
                        break;
                    case R.id.news_details_back:
                        finish();
                        break;
                    case R.id.news_details_zan:
                        String text = newsDetailsZanNumb.getText().toString();
                        int numb = Integer.parseInt(text);
                        if (!isZan) {
                            numb++;
                            newsDetailsZanNumb.setText(numb + "");
                            newsDetailsZan.setBackgroundResource(R.drawable.icon_zan_orange);
                            isZan = true;
                        } else {
                            numb--;
                            newsDetailsZanNumb.setText(numb + "");
                            newsDetailsZan.setBackgroundResource(R.drawable.icon_zan_white);
                            isZan = false;
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        newsDetailsComment.setOnClickListener(listener);
        newsDetailsBack.setOnClickListener(listener);
        newsDetailsZan.setOnClickListener(listener);
    }

    private void showNewsDetails() {
        int position = getIntent().getIntExtra("position", 0);
        News newsItem = NewsFragment.newsLists.get(position);

        newsDetailsTitle.setText(newsItem.getTitle());
        newsDetailsAuthor.setText(newsItem.getAuthor());
        newsDetailsContent.setText(newsItem.getContent().trim());
    }

    private void init() {
        newsDetailsTitle = (TextView) findViewById(R.id.news_details_title);
        newsDetailsAuthor = (TextView) findViewById(R.id.news_details_author);
        newsDetailsContent = (TextView) findViewById(R.id.news_details_content);
        newsDetailsComment = (Button) findViewById(R.id.news_details_comment);
        newsDetailsBack = (Button) findViewById(R.id.news_details_back);
        newsDetailsZan = (Button) findViewById(R.id.news_details_zan);
        newsDetailsZanNumb = (TextView) findViewById(R.id.news_details_zanNum);
        newsDetailsCommentNumb = (TextView) findViewById(R.id.news_details_commentNum);
        mToolbar = (Toolbar) findViewById(R.id.news_details_toolbar);
        mActivity = NewsDetails.this;
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }
}
