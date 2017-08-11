package cn.zengmingyang.needle.complier;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

public class Config {
    public static final String PACKAGE = "cn.zengmingyang.library";
    public static final String COMPONENT_NAME = "AppComponent";
    public static final String APP_MODULE = "AppModule";
    public static final String APP_DAGGER_REFERENCE = PACKAGE + ".Dagger" + COMPONENT_NAME;

    public static final String ANNO_APPLICATION_POOL = "cn.zengmingyang.needle.ApplicationPool";
    public static final String ANNO_LOCAL_POOL = "cn.zengmingyang.nedle.ActivityPool";

    public static final String INJECT_METHOD_NAME = "inject";
}
