package cn.zengmingyang.needle.complier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

import cn.zengmingyang.needle.ApplicationPool;
import cn.zengmingyang.needle.complier.base.ProcessorStep;
import dagger.Component;

import static cn.zengmingyang.needle.complier.Config.ACTIVITY_COMPONENT;
import static cn.zengmingyang.needle.complier.Config.APP_COMPONENT;
import static cn.zengmingyang.needle.complier.Config.APP_MODULE;

/**
 * Created by mingyang.zeng on 2017/8/14.
 */

public class GenAppComponent extends ProcessorStep {

    @Override
    public void go(RoundEnvironment roundEnvironment) {
        AnnotationSpec componentAnnotation = AnnotationSpec
                .builder(Component.class)
                .addMember("modules", "$L.class", APP_MODULE)
                .build();

        TypeSpec.Builder interfaceBuilder = TypeSpec
                .interfaceBuilder(APP_COMPONENT)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(ApplicationPool.class)
                .addAnnotation(componentAnnotation);
        Finder finder = new Finder();
        for (Element element : finder.findActivities(roundEnvironment)) {
            ClassName returnType = ClassName.get("cn.zengmingyang.needle", element.getSimpleName()
                    + ACTIVITY_COMPONENT);
            MethodSpec subComponent = MethodSpec
                    .methodBuilder(element.getSimpleName().toString().toLowerCase() + ACTIVITY_COMPONENT)
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .returns(returnType)
                    .build();
            interfaceBuilder.addMethod(subComponent);
        }
        write(interfaceBuilder.build());
    }
}
