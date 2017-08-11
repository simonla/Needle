package cn.zengmingyang.needle.complier.base;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public abstract class BaseProcessor extends AbstractProcessor {

    private ProcessingEnvironment mProcessingEnvironment;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mProcessingEnvironment = processingEnvironment;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Iterable<? extends ProcessorStep> iterable = initSteps(mProcessingEnvironment, roundEnvironment);
        for (ProcessorStep p : iterable) {
            p.go(mProcessingEnvironment,roundEnvironment);
        }
        return true;
    }

    abstract protected Iterable<? extends ProcessorStep>
    initSteps(ProcessingEnvironment processingEnvironment,RoundEnvironment roundEnvironment);
}
