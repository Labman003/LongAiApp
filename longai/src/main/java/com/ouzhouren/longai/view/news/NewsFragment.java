package com.ouzhouren.longai.view.news;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.News;
import com.ouzhouren.longai.view.news.activity.NewsDetails;
import com.ouzhouren.longai.view.news.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements NewsViewInterface {
    public static List<News> newsLists = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    NewsPresenter newsPresenter;
    NewsAdapter newsAdapter;
    Activity mActivity;
    String str = "这篇文章主要教你怎样约会，并坚定了下面的几个观点----“网上的女孩一般不如现实中的好”；“交友网站上的女孩都是虚伪的”；“网上的人性格都很怪异，因为他们在现实生活中都不善交往”；“如果你遇到某个网上的女孩，你会觉得她要么是长相丑陋、缺乏基本的社交技巧要么是举止怪异”现在我们一起来看看这些观点的正确与否。\n" +
            "\n" +
            "       在我们开始探讨之前，你必须明白这样的事实：我刚刚和一个比我年纪几乎大一倍的女人做完爱回来。完事后，我问她每发布一条这样的主题信息大概会收到多少回复信息。她说每条信息大概会有30-50个人回复他，同时她每隔十分钟就发一条这样的主题信息。所以如果对你没什么兴趣的的话，她几乎不会跟你SC，除非你是个爱爱高手或者你是个富二代。当然，说不定你是个幸运儿，稀里糊涂地就和女人SC了，甚至自己都不知道为什么。然而如果只是这样的话，于你是没有意义的。因为你只想从那条主题中得到一个信息--你情我愿。顺便说一下，我这周已经和两个女人发生了三次关系，而且我也不觉得自己是个坏人。\n" +
            "\n" +
            " 交友网站约会指南：\n" +
            "\n" +
            "      第一，你要相信自己能够和网上的异性发生关系；第二，你必须得知道你不是来猥亵的，来找8,-9岁的小孩；如果你真有那样的嗜好，陌陌之类的交友网站（工具）是一个性爱根据地。它同时也是一个展现自身性爱技巧和能力的舞台，并且逐渐打败那些靠运气泡妞的人，看他们沦为失败者。\n" +
            "\n" +
            "       一个星期之前我开始在陌陌寻找信息。我坚持的原则是-----找不到猎物，决不罢休。也请你们记住这点。总之，观察过这边的气氛和竞争后，我发现一些事情：第一，这里有很多的失败者，他们怨天尤人，发布着千篇一律的牢骚：“我是一只饥渴的猫，我能和你做爱吗”；“我真的真的很想和你做爱”诸如此类。我很认真地想了，这TMD的到底是怎么回事。所以我就在Craigslist上面编辑了第一条信息，内容大概是“我可以请你整夜的...”。发布之后，就有很多的回应。我很开心地翻着回贴却陷入了反思。我想这个过程肯定是有问题的，为什么会有这么多的女人愿意像那样奉献自己?我的意思是，虽然我居住的城市有100万多人，但是这不能说明任何问题。于是我认真地检查了这些人的回复并且发现了正如我所想的事实：这些人的回复都有链接，指向一个约会网站。我似乎成了典型的性奴，但是我没有放弃。\n" +
            "\n" +
            "       三天过去了，我开始想我是不是哪里做错了。于是我在男性需求版块做了一些调查并且发现了三件事：\n" +
            "\n" +
            "       第一，我的主题和很多人的主题内容都一样，无外乎有一个“饥渴的男人想和一个长相娇好的女人整夜风流”的题目，然后文章内容是“我不需要胸大美丽的女人，我想要一个苗条光滑的能和做爱的女孩，我有6英寸的家伙，来试试吧。”我的文章几乎和这些文章一样。我赶紧修改了我的文章，目的是和别人区分出来。标题是：“我知道你想要这个”文章内容是：“我一直再找那个能够整夜驾驭我的女孩”。\n" +
            "\n" +
            "       我注意到的第二件事就是我没有上传照片。于是我迅速地传了一张我最帅的单人照而不是我的家伙照片。很多人都上传家伙照，但是你要知道女人们更想先看看将和她们做爱的男人的长相。\n" +
            "\n" +
            "       第三件事是我注意到一般女孩不会回应你的文章的，因为女孩天生就是那种等着被约会的，而男人天生是侵略者和主动寻找猎物的。于是我到女性需求板块寻找女性发布的需求信息。最终我发现在女性需求板块，约会网站中65%的信息是真实的。\n" +
            "\n" +
            "       文章做完调整后，我真的得到了一个真实女人的回应。我之所以区分出来她是真实的，是因为她的邮箱格式是和那些僵尸会员不一样的。她是一个老手。这次我没有想到做爱本身，而是想用性行为的发生来满足我的成就感。我们约好在三里屯那边见面，交换了联系方式后就离开了。第二天我在约好的地点等她，她却迟迟没出现。我打电话发短信发邮件，她都没有回应我。那天确实给我上了一堂很有价值的课：约会出发前，要先打电话确认。因为总会有些事影响约会，让她不能准时赴约，从而一误再误，没有后续。好好反省之后，我确实学到了一些。\n" +
            "\n" +
            "       第四天的时候准备要放弃了。我又自我反省了一遍，突然灵光一闪，发现了转机。准确地说，应该是启示。我开始思考除了女人，还有什么样的人群，会对这样的信息进行回复。我发现在女性需求板块中，90%的帖子都和胸大美丽、老妇女、以及寻找爱情之类词语的相关搜索有联系。这些同时也让我明白一些事情。如果我真的想得到一个女人的真实回应，我就必须展示给她我最好的一面，更别说是我的脸部特写照片了。与此同时，你也是有风险的，因为你很有可能被你熟悉的人或者朋友认出来，导致你们的关系变得很尴尬或者破裂。但是你要记住：想要得到一些就必须付出一些。所以我鼓足了勇气将照片展示给任何真实的回应我的女性看。晚点时候我又上传了一张我自认为最好的照片。这是一篇能让我和异性发生关系的文章。标题是“我想要一个胸大，美丽，年纪稍大的中年女人”，文章的内容是“我想要一个年纪稍大的，能够知道自己在做什么并且有点曲线的，因为我喜欢骑大马。我已经厌倦了和学校的小女生做爱，因为她们经不住折腾，并且毫无经验。让我们今晚就见面吧，交换生活照、个人照和其他任何的糗照。”这次我得到了3个女人的回应，并且和她们仍在持续的交往中。\n" +
            "\n" +
            "       第六天（就是今天），我收到了一个交往女孩的信息，对我来说真的是一个我没有预料到的惊喜。你看我们都有一个固有的想法：女孩是等着被约的。然而总有一些女孩，一旦她们自己非常想要，就会回应任何形式的需求信息，即使是意味着比较极端的方式也无所谓，比如在陌陌约会一样。\n" +
            "\n" +
            "       我真的开始相信约会网站比现实生活更难。记住,记住，要认真玩你的游戏。不要觉得女人给你回复就万事大吉了，你得自己找点事做。我一晚上给她发了十几个短信，告诉她我就是我所说的我，没有什么假的。得到了她的信任后，我们开始了短信游戏。\n" +
            "\n" +
            "       在短信游戏里，首先要确保她是真实的,确保问她问题,她会回答。“你可以……吗？”。她回答说:“可以。”这就是兴趣指标。我想跟她SC，但是要循序渐进，慢慢导入，确保万无一失。\n" +
            "\n" +
            "      后来，她约我到她家。我以为她是恐龙，没想到她长得不错，身材也很好。我们有了非常美好的性爱体验。\n" +
            "\n" +
            "       这个过程让我对前面提到的一些交友网站里的观点有了自己的论断----“网上的女孩一般不如现实中的好”错了，这是我经历过的最好的性； “约会网站上的女孩都是虚伪的”完全错误；  “网上的人性格都很怪异，因为他们在现实生活中都不善交往”错了，我和她的交流非常愉快； “如果你遇到某个网上的女孩，你会觉得她要么是长相丑陋、缺乏基本的社交技巧要么是举止怪异”错了，她比我预期的要好得多！";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newsView = inflater.inflate(R.layout.fragment_news, container, false);
        init(newsView);
        //newsPresenter.showNews();

        for (int i = 0; i <= 5; i++) {
            News news = new News();
            news.setAuthor("彭仕忠" + i);
            news.setTitle("一分钟教你约会" + i);
            news.setContent(str);
            newsLists.add(news);
        }
        showNews(newsLists);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        News news = new News();
                        int i = newsLists.size();
                        news.setAuthor("彭仕忠" + i);
                        news.setTitle("一分钟教你约会" + i);
                        news.setContent(str);
                        //     news.setImgUrl("http://img3.douban.com/view/photo/photo/public/p914300763.jpg");
                        newsLists.add(news);
                        newsAdapter.notifyDataSetChanged();
                        //停止刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return newsView;
    }

    private void init(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_swipe_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_rv);
        newsPresenter = new NewsPresenter();
        mActivity = getActivity();
        //添加布局
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showNews(List<News> newsLists) {
        newsAdapter = new NewsAdapter(mActivity, newsLists);
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToActivity(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        //添加适配器
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void goToActivity(int itemPosition) {
        Intent news_intent = new Intent(mActivity, NewsDetails.class);
        news_intent.putExtra("position", itemPosition);
        startActivity(news_intent);
    }
}
