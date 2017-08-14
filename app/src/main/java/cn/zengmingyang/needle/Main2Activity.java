package cn.zengmingyang.needle;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class Main2Activity extends BaseActivity {

    @Inject
    M mM;

    @Inject
    N mN;

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: M == " + mM.toString());
        Log.d(TAG, "onCreate: N == " + mN.toString());
    }
}
