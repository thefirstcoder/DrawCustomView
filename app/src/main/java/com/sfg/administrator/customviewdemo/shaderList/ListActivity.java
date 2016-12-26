package com.sfg.administrator.customviewdemo.shaderList;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sfg.administrator.customviewdemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */

public class ListActivity extends AppCompatActivity {

    private ArrayList<String> mDatas;
    private View title;
    private MyListView mListView;
    private View foot;
    private int mTouchSlop;
    private int mFirstY;
    private ObjectAnimator mAnimatorTitle;
    private ObjectAnimator mAnimatorFoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_listview);
        title = findViewById(R.id.mTitle);
        foot = findViewById(R.id.mFoot);
        View view = new View(this);
        view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (48*getResources().getDisplayMetrics().density)));
        mListView = (MyListView) findViewById(R.id.mListView);
        mListView.addHeaderView(view);
        mDatas = new ArrayList<>();
        for (int i=0;i<20;i++){
            mDatas.add("第"+i+"数据");
        }
        mListView.setAdapter(new MyAdapter(mDatas));
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mListView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            int y = (int) event.getY();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    mFirstY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int mCurrentY = y;
                    if(mCurrentY - mFirstY > mTouchSlop){
                        showFootAndHeader(false);
                    }else if(mFirstY -mCurrentY>mTouchSlop){
                        showFootAndHeader(true);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        });
    }

    private void showFootAndHeader(boolean b) {
        if(mAnimatorFoot!=null && mAnimatorFoot.isRunning()){
            mAnimatorFoot.cancel();
        }
        if(mAnimatorTitle!=null && mAnimatorTitle.isRunning()){
            mAnimatorTitle.cancel();
        }
        if(b){
            mAnimatorTitle = ObjectAnimator.ofFloat(title, "translationY", title.getTranslationY(),
                    -(int) (48 * getResources().getDisplayMetrics().density));
            mAnimatorFoot = ObjectAnimator.ofFloat(foot,"translationY",foot.getTranslationY(),
                    (int) (48 * getResources().getDisplayMetrics().density));
        }else{
            mAnimatorTitle = ObjectAnimator.ofFloat(title, "translationY", title.getTranslationY(),0);
            mAnimatorFoot = ObjectAnimator.ofFloat(foot,"translationY",foot.getTranslationY(),0);
        }
        mAnimatorTitle.start();
        mAnimatorFoot.start();
    }


    public class MyAdapter extends BaseAdapter{

        ArrayList<String> mDatas;

        public MyAdapter(ArrayList<String> mDatas) {
            this.mDatas=mDatas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                holder = new ViewHolder();
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view,null);
                holder.textView= (TextView) convertView.findViewById(R.id.mTv);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(mDatas.get(position));
            return convertView;
        }

        public final class ViewHolder{
            TextView textView;
        }
    }
}
