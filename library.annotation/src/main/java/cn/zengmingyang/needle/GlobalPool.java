package cn.zengmingyang.needle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface GlobalPool {

}
