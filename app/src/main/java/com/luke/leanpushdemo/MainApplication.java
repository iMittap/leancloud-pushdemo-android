package com.luke.leanpushdemo;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by lukeliu on 11/13/15.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        AVOSCloud.initialize(this, "2k01gUhytmAkuN8nk2fonor0",
//                "UP2k6xXYNSbVwsmfvq6dDRov");
        AVOSCloud.initialize(this, "s1t4x6i3d83l34d0rksefc5g2srh2ogy5czhqklaqxupo8mh",
                "5smhmu701l3c7omeeur0mjaples2i0nitrlv6gzw883dcu7x");
    }
}
