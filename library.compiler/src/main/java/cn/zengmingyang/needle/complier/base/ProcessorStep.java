package cn.zengmingyang.needle.complier.base;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static cn.zengmingyang.needle.complier.Config.PACKAGE;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public abstract class ProcessorStep {

    protected Messager mMessager;
    protected Filer mFiler;
    protected Types mTypeUtils;
    protected Elements mElementUtils;

    void init(ProcessingEnvironment processingEnvironment) {
        AptUtils aptUtils = AptUtils.get(processingEnvironment);
        mTypeUtils = aptUtils.mTypeUtils;
        mFiler = aptUtils.getFiler();
        mElementUtils = aptUtils.getElementUtils();
        mMessager = aptUtils.getMessager();
    }

    public abstract void go(RoundEnvironment roundEnvironment);

    protected void write(TypeSpec typeSpec) {
        JavaFile javaFile = JavaFile.builder(PACKAGE, typeSpec).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            AptUtils.get().log(e.getMessage());
        }
    }
}
