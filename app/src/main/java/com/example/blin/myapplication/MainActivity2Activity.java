package com.example.blin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity2Activity extends ActionBarActivity {
    private Intent mIntent;
    private Context mContext;
    private IntentFilter mIntentFilter;
    private LocalBroadcastManager mLocalBroadcastManager;
    private LocalBroadcastReceiver mLocalBroadcastReceiver;
    private String TAGSTR= "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        mContext=getApplicationContext();
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(mContext);
    }
public void SendClick(View v)
{
    Log.i(TAGSTR,"SendClick!");
    sendLocalBroadcast();
}
    private void sendLocalBroadcast(){
        Log.i(TAGSTR, "sendLocalBroadcast");
        mIntent=new Intent("test_action");
        mIntent.putExtra("number", "1234");
        mLocalBroadcastManager.sendBroadcast(mIntent);
        //?送?播后取消本地?播的注?
//        unRegisterLocalBroadcastReceiver();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
