package com.sfg.administrator.customviewdemo.customView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sfg.administrator.customviewdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_customview);
        final TextView tv = (TextView) findViewById(R.id.mTv);
        tv.setOnClickListener(v-> click());
    }

    private void click() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //重新回到该activity
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        finish();
    }
}
