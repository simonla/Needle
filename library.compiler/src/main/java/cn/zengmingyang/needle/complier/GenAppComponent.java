package cn.zengmingyang.needle.complier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.tools.Diagnostic;

import cn.zengmingyang.needle.complier.base.ProcessorStep;
import dagger.Component;

import static cn.zengmingyang.needle.complier.Config.APP_MODULE;
import static cn.zengmingyang.needle.complier.Config.COMPONENT_NAME;
import static cn.zengmingyang.needle.complier.Config.INJECT_METHOD_NAME;
import static cn.zengmingyang.needle.complier.Config.PACKAGE;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public class GenAppComponent implements ProcessorStep {

    @Override
    public void go(ProcessingEnvironment processingEnvironment, RoundEnvironment roundEnvironment) {

        AnnotationSpec.Builder componentAnnotation = AnnotationSpec
                .builder(Component.class)
                .addMember("modules", "$L.class", APP_MODULE);

        TypeSpec.Builder interfaceBuilder = TypeSpec
                .interfaceBuilder(COMPONENT_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Singleton.class)
                .addAnnotation(componentAnnotation.build());
        for (Element e : roundEnvironment.getElementsAnnotatedWith(Inject.class)) {
            if(!e.getKind().isField()) continue;
            String className = e.getSimpleName().toString();
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(INJECT_METHOD_NAME)
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .addParameter(TypeName.get(e.asType()), className)
                    .returns(void.class);
            interfaceBuilder.addMethod(methodBuilder.build());
        }
        TypeSpec typeSpec = interfaceBuilder.build();
        JavaFile javaFile = JavaFile.builder(PACKAGE, typeSpec)
                .build();
        try {
            javaFile.writeTo(processingEnvironment.getFiler());
        } catch (IOException e) {
            processingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, e.toString());
        }
    }
}
