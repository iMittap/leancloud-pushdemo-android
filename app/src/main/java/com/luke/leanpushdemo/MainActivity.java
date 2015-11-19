package com.luke.leanpushdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;


public class MainActivity extends Activity implements View.OnClickListener {

    private String myJid = "android_luke_test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPushIntent(getIntent());

        PushService.setDefaultPushCallback(this, MainActivity.class);

        AVInstallation.getCurrentInstallation().put("userJid", myJid);
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                Toast.makeText(MainActivity.this, "Installation Done.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_send).setOnClickListener(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(mPushMessageReceiver, new IntentFilter(PushUtil.PUSH_ACTION));

    }

    @Override
    public void onClick(View view) {
        PushUtil.sendChatPush(this, ((EditText) findViewById(R.id.et_jid)).getText().toString(), "Luke", PushUtil.MessageTypeText, ((EditText) findViewById(R.id.et_alert)).getText().toString(),myJid);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        getPushIntent(intent);

        super.onNewIntent(intent);
    }

    private BroadcastReceiver mPushMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("pushType") && intent.hasExtra("target_id")){
                Log.e("mPushMessageReceiver","pushType:"+intent.getStringExtra("pushType")+",target_id:"+intent.getStringExtra("target_id"));
                ((EditText)findViewById(R.id.et_bocas)).setText("pushType:" + intent.getStringExtra("pushType") + ",target_id:" + intent.getStringExtra("target_id"));

                if (intent.getStringExtra("pushType").equals(PushTypeConfig.CHAT_MESSAGE)){
//                    有CHAT_MESSAGE類型推播進來
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPushMessageReceiver);
        super.onDestroy();
    }


    private void getPushIntent(Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals(PushUtil.PUSH_ACTION)) {
            if (intent.hasExtra("pushType") && intent.hasExtra("target_id")) {
                Log.e("getPushIntent","pushType:"+intent.getStringExtra("pushType")+",target_id:"+intent.getStringExtra("target_id"));
                ((EditText)findViewById(R.id.et_notif)).setText("pushType:"+intent.getStringExtra("pushType")+",target_id:"+intent.getStringExtra("target_id"));

                switch (intent.getStringExtra("pushType")) {
                    case PushTypeConfig.CHAT_MESSAGE:

                        break;
                    case PushTypeConfig.FEEDS_COMMENT:

                        break;
                    case PushTypeConfig.DATE_COMMENT:

                        break;
                    case PushTypeConfig.DATE_APPLT:

                        break;
                    case PushTypeConfig.DATE_DENY:

                        break;
                    case PushTypeConfig.DATE_ACCEPT:

                        break;
                    case PushTypeConfig.DATE_REMIND:

                        break;
                    case PushTypeConfig.FRIEND_APPLY:

                        break;
                    case PushTypeConfig.FRIEND_CONFIRM:

                        break;
                    case PushTypeConfig.FRIEND_DEL:

                        break;
                    case PushTypeConfig.AD_INFO:

                        break;
                    case PushTypeConfig.VERSION:

                        break;
                    case PushTypeConfig.LOGOUT:

                        break;
                    default:
                        break;
                }
            }
        }
    }

}
