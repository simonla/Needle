package cn.zengmingyang.needle;

import javax.inject.Inject;

/**
 * Created by mingyang.zeng on 2017/8/14.
 */

public class N {

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private int i = 0;

    @Inject
    public N() {

    }

    public String getMessage() {
        return "This is a message";
    }
}
