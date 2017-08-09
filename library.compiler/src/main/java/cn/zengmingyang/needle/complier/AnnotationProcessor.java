package cn.zengmingyang.needle.complier;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.inject.Singleton;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import cn.zengmingyang.needle.GlobalPool;
import dagger.Component;
import dagger.Module;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({"cn.zengmingyang.library.annotation.GlobalPool",
        "cn.zengmingyang.library.annotation.LocalPool"})
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // generateAppModule();
        AnnotationSpec.Builder componentAnnotation = AnnotationSpec
                .builder(Component.class)
                .addMember("modules", "$T.class", AppModule.class);

        TypeSpec.Builder interfaceBuilder = TypeSpec
                .interfaceBuilder("AppComponent")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Singleton.class)
                .addAnnotation(componentAnnotation.build());

        for (Element e : roundEnvironment.getElementsAnnotatedWith(GlobalPool.class)) {
            String className = e.getSimpleName().toString();
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .addParameter(TypeName.get(e.asType()), className)
                    .returns(void.class);
            interfaceBuilder.addMethod(methodBuilder.build());
        }
        TypeSpec typeSpec = interfaceBuilder.build();
        JavaFile javaFile = JavaFile.builder("cn.zengmingyang.needle", typeSpec)
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void generateAppModule() {
        TypeSpec moduleClass = TypeSpec
                .classBuilder("AppModule")
                .addAnnotation(Module.class)
                .addModifiers(Modifier.PUBLIC).build();
        JavaFile javaFile = JavaFile.builder("cn.zengmingyang.needle", moduleClass).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
