package cn.zengmingyang.needle;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by mingyang.zeng on 2017/8/10.
 */

@ApplicationPool
public class M {

    @Inject
    Context mContext;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private int i = 0;

    @Inject
    public M(Context context) {
        System.out.println(context.getPackageName());
    }

    public String getMessage() {
        return "This is a message";
    }
}
