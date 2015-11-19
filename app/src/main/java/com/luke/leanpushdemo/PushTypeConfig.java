package com.luke.leanpushdemo;

/**
 * Created by lukeliu on 11/17/15.
 */
public class PushTypeConfig {
    public static final String CHAT_MESSAGE = "chatMessage";    //推送聊天訊息
    public static final String FEEDS_COMMENT = "feedsComment";  //動態有新的評論
    public static final String DATE_COMMENT = "dateComment";    //約會評論
    public static final String DATE_APPLT = "dateApply";        //約會報名
    public static final String DATE_DENY = "dateDeny";          //約會拒絕

    public static final String DATE_ACCEPT = "dateAccept";      //約會接受
    public static final String DATE_REMIND = "dateRemind";      //約會提醒（前一小時)
    public static final String FRIEND_APPLY = "friendApply";    //新增好友
    public static final String FRIEND_CONFIRM = "friendConfirm";//確認好友
    public static final String FRIEND_DEL = "friendDel";        //刪除好友

    public static final String AD_INFO = "adInfo";              //由後台手動發送廣告或系統通知
    public static final String VERSION = "systemVersion";       //版本更新
    public static final String LOGOUT = "systemMultilogin";     //帳號多重登入

}
