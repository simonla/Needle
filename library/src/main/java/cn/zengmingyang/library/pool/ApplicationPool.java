package cn.zengmingyang.library.pool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.zengmingyang.library.Injector;

/**
 * Created by mingyang.zeng on 2017/8/9.
 */

public class ApplicationPool implements IPool {

    private Injector mInjector;
    private static String mPacketName;

    private ApplicationPool() {
        try {
            Class<?> daggerAppComponent = Class.forName(mPacketName + ".DaggerAppComponent");
            Method method = daggerAppComponent.getDeclaredMethod("create");
            Object appComponent = method.invoke(null);
            Class<?> appComponentClazz = appComponent.getClass();
            List<Object> components = new ArrayList<>();
            for (Method m : appComponentClazz.getDeclaredMethods()) {
                if (!m.getName().endsWith("Component")) continue;
                components.add(m.invoke(appComponent));
            }
            mInjector = new Injector(components);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ApplicationPool getInstance(String packetName) {
        mPacketName = packetName;
        return Holder.INSTANCE;
    }

    @Override
    public void inject(Object target) {
        mInjector.inject(target);
    }

    private static class Holder {
        private static final ApplicationPool INSTANCE = new ApplicationPool();
    }

}
