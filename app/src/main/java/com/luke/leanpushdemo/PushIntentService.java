package com.luke.leanpushdemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PushIntentService extends IntentService {

    public PushIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null && intent.hasExtra("alert") && intent.hasExtra("user_jid") && intent.hasExtra("target_id")) {

            String url = "http://123.57.175.166/newmuhua/v1.0/System/pushNotification";

            AQuery aq = new AQuery(this);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("alert", intent.getStringExtra("alert"));
            params.put("pushType", PushTypeConfig.CHAT_MESSAGE);
            params.put("userJid", intent.getStringExtra("user_jid"));
            params.put("target_id",intent.getStringExtra("target_id"));

            Log.e("params", params.toString());

            aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {

                @Override
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    Log.e("callback", json.toString());
                }
            });
        }
    }

}
