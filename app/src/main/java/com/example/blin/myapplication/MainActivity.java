package com.example.blin.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

/**
        * Demo描述:
        * 1 本地?播LocalBroadcast使用完整示例
        * 2 在?播的onReceive()方法中?出Dialog
        *
        * 本地?播的特?:
        * 1 本地?播只能在本App中?播,并且?播接收器也只能接收?自本APP?出的?播.
        *   所以在?情?下可防止其他?用?取?播中的敏感?据?而确保?据安全.
        *
        * 2 本地?播只能使用在代?中??注?的方式.
        *   因???注?主要是?了?程序在未??的情?下也能收到?播.在?送本地?播
        *   ?,我?的App肯定是已???了,因此也完全不需要使用??注?.否?,??本地
        *   ?播也?什么特?了.
        *
        *
        * 注意事?:
        * 在?播的onReceive()方法中?出Dialog
        * 1 ?Dialog?置Type
        *   dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        * 2 注意?限
        *   <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
        *
        *
        * ?考?料:
        * http://blog.csdn.net/guolin_blog
        * Thank you very much
        */
public class MainActivity extends Activity {
    private Button mButton;
    private Intent mIntent;
    private Context mContext;
    private IntentFilter mIntentFilter;
    private LocalBroadcastManager mLocalBroadcastManager;
    private LocalBroadcastReceiver mLocalBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        mContext=getApplicationContext();
        mButton=(Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerLocalBroadcastReceiver();
                sendLocalBroadcast();
            }
        });
    }


    /**
     * 注?本地?播接收者
     */
    private void registerLocalBroadcastReceiver(){
        mIntentFilter=new IntentFilter("test_action");
        mLocalBroadcastReceiver=new LocalBroadcastReceiver();
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(mContext);
        mLocalBroadcastManager.registerReceiver(mLocalBroadcastReceiver, mIntentFilter);
    }

    /**
     * ?送本地?播
     */
    private void sendLocalBroadcast(){
        mIntent=new Intent("test_action");
        mIntent.putExtra("number", "9527");
        mLocalBroadcastManager.sendBroadcast(mIntent);
        //?送?播后取消本地?播的注?
        unRegisterLocalBroadcastReceiver();
    }

    /**
     * 取消本地?播的注?
     */
    private void unRegisterLocalBroadcastReceiver(){
        if (mLocalBroadcastManager!=null) {
            if (mLocalBroadcastReceiver!=null) {
                mLocalBroadcastManager.unregisterReceiver(mLocalBroadcastReceiver);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterLocalBroadcastReceiver();
    }

}