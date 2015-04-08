package com.example.blin.myapplication;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
/**
 * Demo�y�z:
 * 1 ���a?��LocalBroadcast�ϥΧ���ܨ�
 * 2 �b?����onReceive()��k��?�XDialog
 *
 * ���a?�����S?:
 * 1 ���a?���u��b��App��?��,�}�B?���������]�u�౵��?�ۥ�APP?�X��?��.
 *   �ҥH�b?��?�U�i�����L?��?��?�������ӷP?�u?���̫O?�u�w��.
 *
 * 2 ���a?���u��ϥΦb�N?��??�`?���覡.
 *   �]???�`?�D�n�O?�F?�{�Ǧb��??����?�U�]�ব��?��.�b?�e���a?��
 *   ?,��?��App�֩w�O�w???�F,�]���]�������ݭn�ϥ�??�`?.�_?,??���a
 *   ?���]?���\�S?�F.
 *
 *
 * �`�N��?:
 * �b?����onReceive()��k��?�XDialog
 * 1 ?Dialog?�mType
 *   dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
 * 2 �`�N?��
 *   <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
 *
 *
 * ?��?��:
 * http://blog.csdn.net/guolin_blog
 * Thank you very much
 */

/**
 * Created by blin on 2015/4/7.
 */
public class LocalBroadcastReceiver extends BroadcastReceiver {
    private String TAGSTR= "LocalBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("number");
        Log.i(TAGSTR, "Receive!");

        showDialogInBroadcastReceiver(context, message);
    }


    /**
     * �b?����onReceive()��k��?�XDialog
     * 1 ?Dialog?�mType
     * dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
     * 2 �`�N?��
     * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
     */
    private void showDialogInBroadcastReceiver(Context context, String message) {
       AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Title");
        builder.setMessage(message);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }

}