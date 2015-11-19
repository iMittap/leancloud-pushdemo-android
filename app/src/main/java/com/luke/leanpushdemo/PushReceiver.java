package com.luke.leanpushdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lukeliu on 11/13/15.
 */
public class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("PushReceiver", "onReceive");
        try {
            if (intent != null) {

                JSONObject json = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
                Log.e("onReceive:json",json.toString());

                if (json != null && json.has("alert")) {

                    final String message = json.getString("alert");
                    if (message == null)
                        return;

                    final String push_type = json.has("pushType") ?json.getString("pushType"):"";
                    final String target_id = json.has("target_id")?json.getString("target_id"):"";

                    sendLocalBroadcast(context,push_type,target_id);

                    sendPushNotification(push_type,target_id ,message);
                }
            }
        } catch (JSONException e) {
        } catch (Exception e) {
        }
    }

    /**
     * 發送推播LocalBroadcast訊息
     * @param context
     * @param push_type
     * @param target_id
     */
    private void sendLocalBroadcast(Context context,String push_type,String target_id){
        Intent intent = new Intent(PushUtil.PUSH_ACTION);
        intent.putExtra("pushType", push_type);
        intent.putExtra("target_id", target_id);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * 發送推播Notification訊息
     * @param push_type
     * @param target_id
     * @param message
     */
    private void sendPushNotification(String push_type,String target_id,String message){
        Intent resultIntent = new Intent(AVOSCloud.applicationContext, MainActivity.class);
        resultIntent.setAction(PushUtil.PUSH_ACTION);
        resultIntent.putExtra("pushType", push_type);
        resultIntent.putExtra("target_id", target_id);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(AVOSCloud.applicationContext);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(
                AVOSCloud.applicationContext.getResources().getString(R.string.app_name));
        mBuilder.setContentText(message);
        mBuilder.setTicker(message);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        int mNotificationId = 1;
        NotificationManager mNotifyMgr =
                (NotificationManager) AVOSCloud.applicationContext
                        .getSystemService(
                                Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
