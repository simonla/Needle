package cn.zengmingyang.library.component;

import java.lang.reflect.Method;

/**
 * Created by mingyang.zeng on 2017/8/9.
 */

public class AppComponentBuilder {

    public Object buildAppComponent(String packetName) throws Exception {
        Class<?> daggerAppComponent = Class.forName(packetName + ".DaggerAppComponent");
        Method builder = daggerAppComponent.getDeclaredMethod("builder");
        Object Builder = builder.invoke(null);
        Method build = Builder.getClass().getDeclaredMethod("build");
        return build.invoke(Builder);
    }
}
