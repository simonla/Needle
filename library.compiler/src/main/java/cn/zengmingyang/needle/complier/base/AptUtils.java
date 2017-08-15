package cn.zengmingyang.needle.complier.base;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Name;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * Created by mingyang.zeng on 2017/8/15.
 */

public class AptUtils {

    protected Messager mMessager;
    protected Filer mFiler;
    protected Types mTypeUtils;
    protected Elements mElementUtils;

    private volatile static AptUtils INSTANCE;

    public static AptUtils get(ProcessingEnvironment processingEnvironment) {
        if (INSTANCE == null) {
            synchronized (AptUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AptUtils(processingEnvironment);
                }
            }
        }
        return INSTANCE;
    }

    public static AptUtils get() {
        if (INSTANCE == null) {
            synchronized (AptUtils.class) {
                if (INSTANCE == null) {
                    throw new RuntimeException("Not init!");
                }
            }
        }
        return INSTANCE;
    }

    private AptUtils(ProcessingEnvironment processingEnvironment) {
        mFiler = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
        mTypeUtils = processingEnvironment.getTypeUtils();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    public Messager getMessager() {
        return mMessager;
    }

    public Filer getFiler() {
        return mFiler;
    }

    public Types getTypeUtils() {
        return mTypeUtils;
    }

    public Elements getElementUtils() {
        return mElementUtils;
    }

    public void log(String s) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, s);
    }

    public void log(Name name) {
        log(name.toString());
    }

    public void log(boolean content) {
        log(String.valueOf(content));
    }
}
