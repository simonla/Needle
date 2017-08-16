package cn.zengmingyang.needle;

import javax.inject.Inject;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

@ApplicationPool
public class M {

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private int i = 0;

    @Inject
    public M() {

    }

    public String getMessage() {
        return "This is a message";
    }
}
