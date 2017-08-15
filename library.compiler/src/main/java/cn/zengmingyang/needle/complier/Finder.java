package cn.zengmingyang.needle.complier;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.inject.Inject;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

import cn.zengmingyang.needle.complier.base.AptUtils;

/**
 * Created by mingyang.zeng on 2017/8/14.
 */

public class Finder {

    private Set<Element> mActivityElements;
    private AptUtils mUtils = AptUtils.get();
    private boolean isActivity = false;

    public Set<? extends Element> findActivities(RoundEnvironment roundEnvironment) {
        if (mActivityElements != null) return mActivityElements;
        mActivityElements = new HashSet<>();
        for (Element e : roundEnvironment.getElementsAnnotatedWith(Inject.class)) {
            if (e.getKind() != ElementKind.FIELD) continue;
            Element topElement = e.getEnclosingElement();
            String target = "android.app.Activity";
            collectActivityElements(target, topElement);
        }
        return mActivityElements;
    }

    private void collectActivityElements(String target, Element topElement) {
        BFS(target, topElement.asType());
        if (isActivity) mActivityElements.add(topElement);
        isActivity = false;
    }

    private void BFS(String target, TypeMirror mirror) {
        if (mirror.toString().equals("java.lang.Object")) return;
        if (mirror.toString().equals(target)) {
            isActivity = true;
            return;
        }
        for (TypeMirror t : mUtils.getTypeUtils().directSupertypes(mirror)) {
            BFS(target, t);
        }
    }
}
