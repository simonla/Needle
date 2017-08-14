package cn.zengmingyang.needle.complier;

import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;

import cn.zengmingyang.needle.complier.base.ProcessorStep;
import dagger.Module;

import static cn.zengmingyang.needle.complier.Config.ACTIVITY_MODULE;
import static cn.zengmingyang.needle.complier.Config.APP_MODULE;

/**
 * Created by mingyang.zeng on 2017/8/11.
 */

public class GenModule extends ProcessorStep {

    @Override
    public void go(RoundEnvironment roundEnvironment) {
        GenModuleByName(APP_MODULE);
        GenModuleByName(ACTIVITY_MODULE);
    }

    private void GenModuleByName(String appModule){
        TypeSpec module = TypeSpec
                .classBuilder(appModule)
                .addAnnotation(Module.class)
                .addModifiers(Modifier.PUBLIC).build();
        write(module);
    }
}
