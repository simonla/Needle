package cn.zengmingyang.needle.complier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

import cn.zengmingyang.needle.ActivityPool;
import cn.zengmingyang.needle.complier.base.ProcessorStep;
import cn.zengmingyang.needle.complier.find.ActivitiesFinder;
import cn.zengmingyang.needle.complier.find.Finder;
import dagger.Component;

import static cn.zengmingyang.needle.complier.Config.ACTIVITY_COMPONENT;
import static cn.zengmingyang.needle.complier.Config.APP_COMPONENT;
import static cn.zengmingyang.needle.complier.Config.APP_MODULE;
import static cn.zengmingyang.needle.complier.Config.INJECT_METHOD_NAME;

/**
 * Created by mingyang.zeng on 2017/8/11.
 */

public class GenActivityComponent extends ProcessorStep {

    @Override
    public void go(RoundEnvironment roundEnvironment) {
        Finder finder = new ActivitiesFinder();
        AnnotationSpec componentAnnotation = AnnotationSpec
                .builder(Component.class)
                .addMember("modules", "$L.class", APP_MODULE)
                .addMember("dependencies", "$L.class", APP_COMPONENT)
                .build();
        TypeSpec.Builder interfaceBuilder = TypeSpec
                .interfaceBuilder(ACTIVITY_COMPONENT)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(ActivityPool.class)
                .addAnnotation(componentAnnotation);

        for (Element e : finder.find(roundEnvironment)) {
            String className = e.getSimpleName().toString();
            MethodSpec.Builder methodBuilder = MethodSpec
                    .methodBuilder(INJECT_METHOD_NAME)
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .addParameter(TypeName.get(e.asType()), className)
                    .returns(void.class);
            interfaceBuilder.addMethod(methodBuilder.build());
        }
        write(interfaceBuilder.build());
    }
}
