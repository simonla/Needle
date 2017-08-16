package cn.zengmingyang.needle.complier;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public class Config {
    public static final String PACKAGE = "cn.zengmingyang.needle";
    public static final String APP_COMPONENT = "AppComponent";
    public static final String ACTIVITY_COMPONENT = "ActivityComponent";
    public static final String APP_MODULE = "AppModule";
    public static final String ACTIVITY_MODULE = "ActivityModule";
    public static final String APP_DAGGER_REFERENCE = PACKAGE + ".Dagger" + APP_COMPONENT;

    public static final String ANNO_APPLICATION_POOL = "cn.zengmingyang.needle.ApplicationPool";
    public static final String ANNO_LOCAL_POOL = "cn.zengmingyang.nedle.ActivityPool";
    public static final String ANNO_INJECT = "javax.inject.Inject";

    public static final String INJECT_METHOD_NAME = "inject";
}
