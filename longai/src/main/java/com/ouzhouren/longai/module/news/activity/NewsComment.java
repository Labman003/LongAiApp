package com.ouzhouren.longai.module.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.entity.Comment;
import com.ouzhouren.longai.module.news.adapter.CommentAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 郭泽锋 on 2015/8/19.
 */
public class NewsComment extends AppCompatActivity {
    public static List<Comment> commentList = new ArrayList<>();
    private Button back,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_comment);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_comment_rv);
        back = (Button) findViewById(R.id.news_comment_back);
        edit = (Button) findViewById(R.id.news_comment_edit);

        //添加布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String str = "一天，刘备、关羽和张飞被曹操抓起来了，曹操说：“你们到果园里去摘个水果，我就放了你们。”张飞第一个来，他拿了一个哈密瓜，曹操说：“你把哈密瓜塞进屁股里，我就放了你。”张飞塞呀塞，但塞不进，被杀了。关羽第二个来，他拿了一串葡萄，曹操说了同样的话，关羽一颗一颗地塞，当他塞到最后一颗时，突然“扑哧”一笑，葡萄爆了，也被杀了。在地狱，关羽和张飞见面了，张飞说：“你拿着葡萄，这么好塞，却也被杀了。要是我的话，就肯定会被放！”关羽叹了一口气，说：“没办法呀，那时，我突然看见刘备拿着榴莲走过来。”没多久，刘备也下了地狱。";
        for (int i = 0; i <= 2; i++) {
            Comment comment = new Comment();
            comment.setNickName("nick" + i);
            comment.setContent(str);
            comment.setPubTime(new Date());
            comment.setUserId(i);
            commentList.add(comment);
        }
         CommentAdapter commentAdapter = new CommentAdapter(this, commentList);
        recyclerView.setAdapter(commentAdapter);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.news_comment_back:
                        Intent intent = new Intent(NewsComment.this,NewsDetails.class);
                        startActivity(intent);
                        //finish();
                        break;
                    case R.id.news_comment_edit:
                        Intent intent1 = new Intent(NewsComment.this, CommentEditPage.class);
                        startActivity(intent1);
                        break;
                }
            }
        };
        back.setOnClickListener(listener);
        edit.setOnClickListener(listener);
//
//        newsAdapter.setOnItemClickLitener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent news_intent = new Intent(getActivity(), NewsDetails.class);
//                news_intent.putExtra("position", position);
//                startActivity(news_intent);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });

    }
}
