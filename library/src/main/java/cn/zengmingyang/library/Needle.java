package cn.zengmingyang.library;

import cn.zengmingyang.library.component.ActivityComponentBuilder;
import cn.zengmingyang.library.component.AppComponentBuilder;

/**
 * Created by mingyang.zeng on 2017/8/8.
 */

public class Needle {

    private String mPacketName;
    private volatile static Needle needle;

    private Needle(String packetName) {
        mPacketName = packetName;
    }

    private void buildComponent(Object target) {
        ActivityComponentBuilder activityComponentBuilder = new ActivityComponentBuilder();
        Object component = null;
        try {
            component = activityComponentBuilder.buildActivityComponent(mPacketName, new AppComponentBuilder().buildAppComponent(mPacketName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (component != null) {
            Injector injector = new Injector(component);
            injector.inject(target);
        }
    }

    public static Needle inject(Object target, String packetName) {
        lazyLoad(packetName);
        needle.buildComponent(target);
        return needle;
    }

    private static void lazyLoad(String packetName) {
        if (needle == null) {
            synchronized (Needle.class) {
                if (needle == null) {
                    needle = new Needle(packetName);
                }
            }
        }
    }
}
