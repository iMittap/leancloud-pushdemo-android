package com.luke.leanpushdemo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by lukeliu on 11/17/15.
 */
public class PushUtil {

    public static final String PUSH_ACTION = "mihua_push_action";

    public static final int MessageTypeText = 1;
    public static final int MessageTypeImage = 2;
    public static final int MessageTypeAudio = 3;
    public static final int MessageTypeLocation = 4;

    /**
     * 通知後台發送聊天推播
     *
     * @param ctx
     * @param user_jid 聊天對象的xmpp jid
     * @param my_name  自己的名字
     * @param type     聊天訊息類型
     * @param msg      聊天訊息
     * @param my_jid   我的xmpp jid
     */
    public static void sendChatPush(Context ctx, String user_jid, String my_name, int type, String msg ,String my_jid) {
        if (!TextUtils.isEmpty(user_jid) && !TextUtils.isEmpty(my_name)) {

            String alert = "";

            switch (type) {
                case MessageTypeText:
                    if (!TextUtils.isEmpty(msg)) {
                        alert = String.format(ctx.getString(R.string.msg_type_text), my_name, msg);
                    } else {
                        alert = String.format(ctx.getString(R.string.msg_type_other), my_name);
                    }
                    break;
                case MessageTypeImage:
                    alert = String.format(ctx.getString(R.string.msg_type_image), my_name);
                    break;
                case MessageTypeAudio:
                    alert = String.format(ctx.getString(R.string.msg_type_audio), my_name);
                    break;
                case MessageTypeLocation:
                    alert = String.format(ctx.getString(R.string.msg_type_location), my_name);
                    break;
                default:
                    alert = String.format(ctx.getString(R.string.msg_type_other), my_name);
                    break;
            }

            Intent i = new Intent(ctx, PushIntentService.class);
            i.putExtra("alert", alert);
            i.putExtra("user_jid", user_jid);
            i.putExtra("target_id", user_jid);
            ctx.startService(i);
        }
    }
}
