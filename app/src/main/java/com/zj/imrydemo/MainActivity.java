package com.zj.imrydemo;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "RongYunAbout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String portraitUri = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=616492013,3957489063&fm=179&w=56&h=56&img.PNG";
        String nickName = "蛟龙87";
        String token = "e4mQwVAYJHH468gnoAK1skogJ+TmYvxclyqbgdC285T3g3CHk7NdZ/IvMKuNI+xKcU1tQv0QrFmZD623Ch+Wkw==";

        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "onTokenIncorrect");
            }

            @Override
            public void onSuccess(String userId) {
                Log.d(TAG, "ConnectCallback connect onSuccess=" + userId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "ConnectCallback connect onError-ErrorCode=" + errorCode);
            }
        });


        initConversationList();


    }

    private void initConversationList() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ConversationListFragment conversationlistFragment = (ConversationListFragment) supportFragmentManager.findFragmentById(R.id.conversationlist);
        conversationlistFragment.getActivity();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        conversationlistFragment.setUri(uri);
    }


}
