package com.example.blin.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//http://m.blog.csdn.net/blog/lfdfhl/39190069
/**
 * Demo描述:
 * 1 本地广播LocalBroadcast使用完整示例
 * 2 在广播的onReceive()方法中弹出Dialog
 *
 * 本地广播的特点:
 * 1 本地广播只能在本App中传播,并且广播接收器也只能接收来自本APP发出的广播.
 *   所以在该情况下可防止其他应用获取广播中的敏感数据从而确保数据安全.
 *
 * 2 本地广播只能使用在代码中动态注册的方式.
 *   因为静态注册主要是为了让程序在未启动的情况下也能收到广播.在发送本地广播
 *   时,我们的App肯定是已经启动了,因此也完全不需要使用静态注册.否则,这个本地
 *   广播也没什么特点了.
 *
 *
 * 注意事项:
 * 在广播的onReceive()方法中弹出Dialog
 * 1 给Dialog设置Type
 *   dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
 * 2 注意权限
 *   <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
 *
 *
 * 参考资料:
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
    private String TAGSTR= "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        registerLocalBroadcastReceiver();

    }

    private void init(){
        mContext=getApplicationContext();
        mButton=(Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendLocalBroadcast();
            }
        });
    }

public void StartSec(View v)
{
    Intent intent=new Intent(this,MainActivity2Activity.class);
    startActivity(intent);


}
    /**
     * 注?本地?播接收者
     */
    private void registerLocalBroadcastReceiver(){
        Log.i(TAGSTR,"registerLocalBroadcastReceiver");
        mIntentFilter=new IntentFilter("test_action");
        mLocalBroadcastReceiver=new LocalBroadcastReceiver();
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(mContext);
        mLocalBroadcastManager.registerReceiver(mLocalBroadcastReceiver, mIntentFilter);
    }

    /**
     * ?送本地?播
     */
    private void sendLocalBroadcast(){
        Log.i(TAGSTR,"sendLocalBroadcast");
        mIntent=new Intent("test_action");
        mIntent.putExtra("number", "9527");
        mLocalBroadcastManager.sendBroadcast(mIntent);
        //?送?播后取消本地?播的注?
//        unRegisterLocalBroadcastReceiver();
    }

    /**
     * 取消本地?播的注?
     */
    private void unRegisterLocalBroadcastReceiver(){
        if (mLocalBroadcastManager!=null) {
            if (mLocalBroadcastReceiver!=null) {
                Log.i(TAGSTR,"unRegisterLocalBroadcastReceiver");
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