/**
 * ShadeActivity.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-3
 */
package com.cayden.activity;

import android.app.Activity;  
import android.graphics.Color;  
import android.os.Bundle;  
import android.view.Gravity;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.ViewGroup;  
import android.view.Window;  
import android.widget.Button;  
import android.widget.FrameLayout;  
import android.widget.TextView;  
  
  
public class ShadeActivity extends Activity {  
    // 设置是否展开  
    private boolean isFolded = true;  
    // 设置控件  
    private FrameLayout layout = null;  
    private Button unfoldButton = null;  
    private TextView textView = null;  
  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.show);  
  
        initView();  
    }  
  
    @Override  
    protected void onResume() {  
        // TODO Auto-generated method stu  
        super.onResume();  
        isFolded = true;  
    }  
  
    // 初始化  
    private void initView() {  
        layout = (FrameLayout) findViewById(R.id.layout);  
        unfoldButton = (Button) findViewById(R.id.unfoldButton);  
        unfoldButton.setOnClickListener(new UnfoldClickListener());  
    }  
  
    // 按钮监听，展开一个透明的显示文本的遮挡层  
    private class UnfoldClickListener implements OnClickListener {  
        public void onClick(View v) {  
            if (isFolded) {  
                textView = new TextView(ShadeActivity.this);  
                textView.setTextColor(Color.BLUE);  
                textView.setTextSize(20);  
                textView.setText("滚滚长江东逝水，浪花淘尽英雄。\n" + "是非成败转头空，\n"  
                        + "青山依旧在，几度夕阳红。\n" + "白发渔樵江渚上，惯看秋月春风。 \n"  
                        + "一壶浊酒喜相逢，\n" + "古今多少事，都付笑谈中。");  
                textView.setGravity(Gravity.CENTER);  
                textView.setLayoutParams(new ViewGroup.LayoutParams(  
                        ViewGroup.LayoutParams.FILL_PARENT,  
                        ViewGroup.LayoutParams.FILL_PARENT));  
                textView.setBackgroundColor(Color.parseColor("#86222222"));  
  
                unfoldButton.setText("取消遮罩");  
  
                isFolded = false;  
  
                layout.addView(textView);  
            } else {  
                unfoldButton.setText("显示遮罩");  
                isFolded = true;  
                layout.removeView(textView);  
            }  
        }  
    }  
}  