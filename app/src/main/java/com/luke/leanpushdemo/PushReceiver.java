package com.luke.leanpushdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.avos.avoscloud.AVOSCloud;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lukeliu on 11/13/15.
 */
public class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
            final String message = json.getString("alert");

            Intent resultIntent = new Intent(AVOSCloud.applicationContext, MainActivity.class);
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(AVOSCloud.applicationContext)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(
                                    AVOSCloud.applicationContext.getResources().getString(R.string.app_name))
                            .setContentText(message)
                            .setTicker(message);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setAutoCancel(true);

            int mNotificationId = 10086;
            NotificationManager mNotifyMgr =
                    (NotificationManager) AVOSCloud.applicationContext
                            .getSystemService(
                                    Context.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

        } catch (JSONException e) {
        }

    }
}
