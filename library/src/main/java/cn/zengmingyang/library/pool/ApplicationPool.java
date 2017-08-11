package cn.zengmingyang.library.pool;

import java.lang.reflect.Method;

import cn.zengmingyang.library.Injector;

/**
 * Created by mingyang.zeng on 2017/8/9.
 */

public class ApplicationPool implements IPool {

    private Injector mInjector;

    public ApplicationPool() {
        try {
            Class<?> daggerAppComponent = Class.forName("cn.zengmingyang.library.DaggerAppComponent");
            Method method = daggerAppComponent.getDeclaredMethod("create");
            Object appComponent = method.invoke(null);
            mInjector = new Injector(appComponent);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void inject(Object target) {
        mInjector.inject(target);
    }
}
