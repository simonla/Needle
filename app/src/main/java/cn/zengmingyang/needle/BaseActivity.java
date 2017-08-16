package cn.zengmingyang.needle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.zengmingyang.library.Needle;
import cn.zengmingyang.library.pool.ApplicationPool;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder().appComponent(DaggerAppComponent.builder().build()).build();
        Needle.inject(this).into(ApplicationPool.getInstance(this.getPackageName()));
    }
}
