package cn.zengmingyang.needle.complier;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.inject.Inject;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Created by mingyang.zeng on 2017/8/14.
 */

public class ActivityFinder {

    private static Set<Element> mElements;

    public static Set<? extends Element> findElements(RoundEnvironment roundEnvironment) {
        if(mElements!=null) return mElements;
        mElements = new HashSet<>();
        for (Element e : roundEnvironment.getElementsAnnotatedWith(Inject.class)) {
            if (e.getKind() != ElementKind.FIELD) continue;
            Element topElement = e.getEnclosingElement();
            mElements.add(topElement);
        }
        return mElements;
    }
}
