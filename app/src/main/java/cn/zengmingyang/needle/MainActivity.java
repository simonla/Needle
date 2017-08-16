package cn.zengmingyang.needle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    M mM;

    @Inject
    N mN;

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv);
        Log.d(TAG, "onCreate: M == " + mM.toString());
        Log.d(TAG, "onCreate: N == " + mN.toString());
        final Intent i = new Intent(this, Main2Activity.class);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }
}
