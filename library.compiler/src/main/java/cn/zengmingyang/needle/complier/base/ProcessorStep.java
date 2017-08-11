package cn.zengmingyang.needle.complier.base;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public interface ProcessorStep {
    void go(ProcessingEnvironment processingEnvironment, RoundEnvironment roundEnvironment);
}
