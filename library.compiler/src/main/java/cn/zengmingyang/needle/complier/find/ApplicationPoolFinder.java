package cn.zengmingyang.needle.complier.find;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.inject.Inject;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import cn.zengmingyang.needle.ActivityPool;

/**
 * Created by mingyang.zeng on 2017/8/15.
 */

public class ApplicationPoolFinder extends Finder {

    @Override
    public Set<? extends Element> find(RoundEnvironment roundEnvironment) {
        Set<Element> set = new HashSet<>();

        for (Element e : roundEnvironment.getElementsAnnotatedWith(Inject.class)) {
            if (e.getKind() == ElementKind.METHOD || e.getKind() == ElementKind.CONSTRUCTOR) {
                e = e.getEnclosingElement();
                set.add(e);
            }
        }
        for (Element e : roundEnvironment.getElementsAnnotatedWith(ActivityPool.class)) {
            e = e.getEnclosingElement();
            set.remove(e);
        }
        return set;
    }
}
