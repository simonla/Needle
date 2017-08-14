package cn.zengmingyang.library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

public class Injector {

    private HashMap<Class<?>, Method> mMethodClassBind;
    private HashMap<Class<?>, Object> mComponents;

    public Injector(List<Object> components) {
        mMethodClassBind = new LinkedHashMap<>();
        mComponents = new LinkedHashMap<>();
        try {
            build(components);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void build(List<Object> components) throws NoSuchMethodException {
        for (Object component : components) {
            Method method = findInjectMethod(component.getClass().getDeclaredMethods());
            if (method == null) continue;
            Class beInjected = method.getParameterTypes()[0];
            if (beInjected != null) {
                mMethodClassBind.put(beInjected, method);
                mComponents.put(beInjected, component);
            }
        }
    }

    private Method findInjectMethod(Method[] declaredMethods) {
        for (Method m : declaredMethods) {
            if (m.getName().equals("inject")){
                return m;
            }
        }
        return null;
    }

    public void inject(Object target) {
        Method method = mMethodClassBind.get(target.getClass());
        Object component = mComponents.get(target.getClass());
        try {
            method.invoke(component, target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
