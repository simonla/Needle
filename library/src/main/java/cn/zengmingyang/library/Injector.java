package cn.zengmingyang.library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

public class Injector {

    private HashMap<Class<?>, Method> mMethodClassBind;
    private Object mComponent;

    public Injector(Object component) {
        mMethodClassBind = new LinkedHashMap<>();
        mComponent = component;
        try {
            build();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void build() throws NoSuchMethodException {
        for (Method method : findInjectMethods(mComponent.getClass().getDeclaredMethods())) {
            Class beInjected = method.getParameterTypes()[0];
            if (beInjected != null) {
                mMethodClassBind.put(beInjected, method);
            }
        }
    }

    private List<Method> findInjectMethods(Method[] declaredMethods) {
        List<Method> list = new ArrayList<>();
        for (Method m : declaredMethods) {
            if (m.getName().equals("inject")) {
                list.add(m);
            }
        }
        return list;
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
