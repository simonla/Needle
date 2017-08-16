package cn.zengmingyang.needle.complier.find;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import cn.zengmingyang.needle.complier.base.AptUtils;

/**
 * Created by mingyang.zeng on 2017/8/14.
 */

public abstract class Finder {

    Types mTypeUtils;
    Elements mElementUtils;
    AptUtils mAptUtils;

    public Finder() {
        mAptUtils= AptUtils.get();
        mTypeUtils = mAptUtils.getTypeUtils();
        mElementUtils = mAptUtils.getElementUtils();
    }

    public abstract Set<? extends Element> find(RoundEnvironment roundEnvironment);

}
