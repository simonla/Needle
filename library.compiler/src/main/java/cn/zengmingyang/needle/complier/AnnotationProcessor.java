package cn.zengmingyang.needle.complier;


import com.google.auto.service.AutoService;

import java.util.Arrays;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

import cn.zengmingyang.needle.complier.base.BaseProcessor;
import cn.zengmingyang.needle.complier.base.ProcessorStep;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({Config.ANNO_APPLICATION_POOL,
        Config.ANNO_LOCAL_POOL})
@AutoService(Processor.class)
public class AnnotationProcessor extends BaseProcessor {

    @Override
    protected Iterable<? extends ProcessorStep> initSteps(ProcessingEnvironment processingEnvironment
            , RoundEnvironment roundEnvironment) {
        return Arrays.asList(
                new GenAppModule(),
                new GenAppComponent(),
                new Step2()
        );
    }
}
