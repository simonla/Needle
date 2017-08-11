package cn.zengmingyang.needle.complier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.tools.Diagnostic;

import cn.zengmingyang.needle.complier.base.ProcessorStep;
import dagger.Module;

import static cn.zengmingyang.needle.complier.Config.APP_MODULE;
import static cn.zengmingyang.needle.complier.Config.PACKAGE;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public class GenAppModule implements ProcessorStep {


    @Override
    public void go(ProcessingEnvironment processingEnvironment, RoundEnvironment roundEnvironment) {
        TypeSpec moduleClass = TypeSpec
                .classBuilder(APP_MODULE)
                .addAnnotation(Module.class)
                .addModifiers(Modifier.PUBLIC).build();
        JavaFile javaFile = JavaFile.builder(PACKAGE, moduleClass).build();
        try {
            javaFile.writeTo(processingEnvironment.getFiler());
        } catch (IOException e) {
            processingEnvironment
                    .getMessager()
                    .printMessage(Diagnostic.Kind.NOTE, e.toString());
        }
    }
}
