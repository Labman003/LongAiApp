package com.ouzhouren.longai.view.talk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListView;
import android.widget.Toast;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.constant.ConstantServer;
import com.ouzhouren.longai.model.MessageVO;
import com.ouzhouren.longai.view.wiget.chat.ChatMessage;
import com.ouzhouren.longai.view.wiget.chat.MessageAdapter;
import com.ouzhouren.longai.view.wiget.chat.MessageInputToolBox;
import com.ouzhouren.longai.view.wiget.chat.OnOperationListener;
import com.ouzhouren.longai.view.wiget.chat.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TalkActivity extends AppCompatActivity {

    private MessageInputToolBox box;
    private ListView listView;
    private MessageAdapter adapter;
    private MyLogger logger = MyLogger.benLog();
    private Toolbar toolbar;
    private Client client;
    private AcceptMessage socketConnect;
    private MyHandler myHandler;
    private Boolean isPrepare =false;

    class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }
        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            // 此处可以更新UI
            if(msg.what==0x123){
                if(isPrepare==true){
                    adapter.notifyDataSetChanged();
                    listView.setSelection(listView.getBottom());
                }

            }

        }
    }

    @SuppressLint("UseSparseArrays")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("YYT");
        setSupportActionBar(toolbar);
        myHandler = new MyHandler();
        new ChatThread().start();
        initMessageInputToolBox();
        initListView();
    }

    /**
     * init MessageInputToolBox
     */
    private void initMessageInputToolBox() {
        box = (MessageInputToolBox) findViewById(R.id.messageInputToolBox);
        box.setOnOperationListener(new OnOperationListener() {

            @Override
            public void send(String content) {

                System.out.println("===============" + content);
                client.sendMessage(content, 5);
                ChatMessage chatMessage = new ChatMessage(0, 1, "Tom", "avatar", "Jerry", "avatar", content, true, true, new Date());


                adapter.getData().add(chatMessage);
                listView.setSelection(listView.getBottom());

                //Just demo
                createReplayMsg(chatMessage);
            }

            @Override
            public void selectedFace(String content) {

                System.out.println("===============" + content);
                ChatMessage chatMessage = new ChatMessage(ChatMessage.MSG_TYPE_FACE, ChatMessage.MSG_STATE_SUCCESS, "Tomcat", "avatar", "Jerry", "avatar", content, true, true, new Date());
                adapter.getData().add(chatMessage);
                listView.setSelection(listView.getBottom());

                //Just demo
                createReplayMsg(chatMessage);
            }


            @Override
            public void selectedFuncation(int index) {

                System.out.println("===============" + index);

                switch (index) {
                    case 0:
                        //do some thing
                        break;
                    case 1:
                        //do some thing
                        break;

                    default:
                        break;
                }
                Toast.makeText(TalkActivity.this, "Do some thing here, index :" + index, Toast.LENGTH_LONG).show();

            }

        });
        logger.i(5);
        ArrayList<String> faceNameList = new ArrayList<String>();
        for (int x = 1; x <= 10; x++) {
            faceNameList.add("big" + x);
        }
        for (int x = 1; x <= 10; x++) {
            faceNameList.add("big" + x);
        }

        ArrayList<String> faceNameList1 = new ArrayList<String>();
        for (int x = 1; x <= 7; x++) {
            faceNameList1.add("cig" + x);
        }


        ArrayList<String> faceNameList2 = new ArrayList<String>();
        for (int x = 1; x <= 24; x++) {
            faceNameList2.add("dig" + x);
        }
        logger.i(6);
        Map<Integer, ArrayList<String>> faceData = new HashMap<Integer, ArrayList<String>>();
        faceData.put(R.drawable.em_cate_magic, faceNameList2);
        faceData.put(R.drawable.em_cate_rib, faceNameList1);
        faceData.put(R.drawable.em_cate_duck, faceNameList);
        box.setFaceData(faceData);

        logger.i(7);
        List<Option> functionData = new ArrayList<Option>();
        for (int x = 0; x < 5; x++) {
            Option takePhotoOption = new Option(this, "Take", R.drawable.take_photo);
            Option galleryOption = new Option(this, "Gallery", R.drawable.gallery);
            functionData.add(galleryOption);
            functionData.add(takePhotoOption);
        }
        logger.i(8);
        box.setFunctionData(functionData);
    }


    private void initListView() {
        listView = (ListView) findViewById(R.id.messageListview);

        //create Data
        ChatMessage chatMessage = new ChatMessage(ChatMessage.MSG_TYPE_TEXT, ChatMessage.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Hi", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8));
        ChatMessage chatMessage1 = new ChatMessage(ChatMessage.MSG_TYPE_TEXT, ChatMessage.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Hello World", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8));
        ChatMessage chatMessage2 = new ChatMessage(ChatMessage.MSG_TYPE_PHOTO, ChatMessage.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "device_2014_08_21_215311", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7));
        ChatMessage chatMessage3 = new ChatMessage(ChatMessage.MSG_TYPE_TEXT, ChatMessage.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Haha", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7));
        ChatMessage chatMessage4 = new ChatMessage(ChatMessage.MSG_TYPE_FACE, ChatMessage.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "big3", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7));
        ChatMessage chatMessage5 = new ChatMessage(ChatMessage.MSG_TYPE_FACE, ChatMessage.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "big2", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));
        ChatMessage chatMessage6 = new ChatMessage(ChatMessage.MSG_TYPE_TEXT, ChatMessage.MSG_STATE_FAIL, "Tom", "avatar", "Jerry", "avatar", "test send fail", true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));
        ChatMessage chatMessage7 = new ChatMessage(ChatMessage.MSG_TYPE_TEXT, ChatMessage.MSG_STATE_SENDING, "Tom", "avatar", "Jerry", "avatar", "test sending", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));

        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        chatMessages.add(chatMessage);
        chatMessages.add(chatMessage1);
        chatMessages.add(chatMessage2);
        chatMessages.add(chatMessage3);
        chatMessages.add(chatMessage4);
        chatMessages.add(chatMessage5);
        chatMessages.add(chatMessage6);
        chatMessages.add(chatMessage7);

        adapter = new MessageAdapter(this, chatMessages);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                box.hide();
                return false;
            }
        });
isPrepare= true;
    }


    private void createReplayMsg(ChatMessage chatMessage) {

        final ChatMessage reChatMessage = new ChatMessage(chatMessage.getType(), 1, "Tom", "avatar", "Jerry", "avatar",
                chatMessage.getType() == 0 ? "Re:" + chatMessage.getContent() : chatMessage.getContent(),
                false, true, new Date()
        );
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * (new Random().nextInt(3) + 1));
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            adapter.getData().add(reChatMessage);
                            listView.setSelection(listView.getBottom());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        try {
            client.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();

    }
    class ChatThread extends Thread{
        public ChatThread() {
        }

        @Override
        public void run() {
            //定义消息
            logger.i("runinto");
            try {
                client = new Client(ConstantServer.CHAT, 8899,3);
                socketConnect = new AcceptMessage(client.getSocket(),new MessageCallBack());
                socketConnect.start();
                logger.i("OOBInline status is:"+client.getSocket().getOOBInline());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    class MessageCallBack {
        public void onSuccess(MessageVO messageVO){
            ChatMessage chatMessage = new ChatMessage(0, 1, "Tom", "avatar", "Jerry", "avatar", messageVO.getMessage(), true, true, new Date());
            adapter.getData().add(chatMessage);
            Message msg = new Message();
            msg.what=0x123;
            TalkActivity.this.myHandler.sendMessage(msg); // 向Handler发送消息，更新UI
           // listView.setSelection(listView.getBottom());
        }
        public void onFail(String s){};
    }

}
