package cn.zengmingyang.needle.complier.base;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Name;
import javax.tools.Diagnostic;

import static cn.zengmingyang.needle.complier.Config.PACKAGE;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public abstract class ProcessorStep {

    protected Messager mMessager;
    protected Filer mFiler;

    void init(ProcessingEnvironment processingEnvironment) {
        mFiler = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
    }

    public abstract void go(RoundEnvironment roundEnvironment) ;

    protected void log(String s) {
        mMessager.printMessage(Diagnostic.Kind.NOTE,
                this.getClass().getSimpleName()+" ==> "+s);
    }

    protected void log(Name name) {
        log(name.toString());
    }


    protected void write(TypeSpec typeSpec) {
        JavaFile javaFile = JavaFile.builder(PACKAGE, typeSpec).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            log(e.getMessage());
        }
    }
}
