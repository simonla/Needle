package cn.zengmingyang.needle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.zengmingyang.library.Needle;

/**
 * Created by mingyang.zeng on 2017/8/4.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Needle.inject(this, getPackageName());
        DaggerActivityComponent.builder().appComponent(DaggerAppComponent.builder().build());
    }
}
