package cn.zengmingyang.library.component;

import java.lang.reflect.Method;

/**
 * Created by mingyang.zeng on 2017/8/16.
 */

public class ActivityComponentBuilder {

    public Object buildActivityComponent(String packetName, Object daggerAppComponent) throws Exception {
        Class<?> daggerActivityComponent = Class.forName(packetName + ".DaggerActivityComponent");
        Method buildMethod = daggerActivityComponent.getDeclaredMethod("builder");
        Object builder = buildMethod.invoke(null);
        Method appComponentMethod = null;
        for (Method m : builder.getClass().getDeclaredMethods()) {
            if (m.getName().equals("appComponent")) {
                appComponentMethod = m;
            }
        }
        if(appComponentMethod==null) throw new RuntimeException("no such method");
        appComponentMethod.invoke(builder, daggerAppComponent);
        return builder.getClass().getDeclaredMethod("build").invoke(builder);
    }
}
