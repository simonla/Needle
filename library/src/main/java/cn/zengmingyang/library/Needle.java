package cn.zengmingyang.library;

import java.util.LinkedList;
import java.util.List;

import cn.zengmingyang.library.pool.IPool;

/**
 * Created by mingyang.zeng on 2017/8/8.
 */

public class Needle {

    private volatile static Needle needle;
    private List<Object> mList = new LinkedList<>();

    private Needle(){}

    public static Needle inject(Object target) {
        lazyLoad();
        needle.mList.add(target);
        return needle;
    }

    private static void lazyLoad() {
        if (needle == null) {
            synchronized (Needle.class) {
                if (needle == null) {
                    needle = new Needle();
                }
            }
        }
    }

    public Needle into(IPool IPool) {
        IPool.inject(needle.mList.remove(0));
        return needle;
    }
}
