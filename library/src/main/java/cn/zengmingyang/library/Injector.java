package cn.zengmingyang.library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

public class Injector {

    private HashMap<Class<?>, Method> mMethodClassBind;
    private Object mComponent;

    public Injector(Object component) {
        mMethodClassBind = new LinkedHashMap<>();
        mComponent = component;
        build();
    }

    private void build() {
        for (Method m : mComponent.getClass().getDeclaredMethods()) {
            if (!m.getName().equals("inject")) continue;
            Class beInjected = m.getParameterTypes()[0];
            if (beInjected != null) {
                mMethodClassBind.put(beInjected, m);
            }
        }
    }

    public void inject(Object target) {
        Method method = mMethodClassBind.get(target.getClass());
        try {
            method.invoke(mComponent, target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
