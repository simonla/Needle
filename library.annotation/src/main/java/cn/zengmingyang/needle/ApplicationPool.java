package cn.zengmingyang.needle;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */


@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationPool {

}
