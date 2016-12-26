package com.sfg.administrator.customviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sfg.administrator.customviewdemo.customView.MainActivity;
import com.sfg.administrator.customviewdemo.qqSlide.QQSlideMenu;
import com.sfg.administrator.customviewdemo.shaderList.ListActivity;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MainUI extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public void onCustomViewClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onZhihuClick(View view) {
        startActivity(new Intent(this, ListActivity.class));
    }

    public void onQQSlideClick(View view) {
        startActivity(new Intent(this, QQSlideMenu.class));
    }
}
