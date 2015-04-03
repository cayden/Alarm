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
    // �����Ƿ�չ��  
    private boolean isFolded = true;  
    // ���ÿؼ�  
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
  
    // ��ʼ��  
    private void initView() {  
        layout = (FrameLayout) findViewById(R.id.layout);  
        unfoldButton = (Button) findViewById(R.id.unfoldButton);  
        unfoldButton.setOnClickListener(new UnfoldClickListener());  
    }  
  
    // ��ť������չ��һ��͸������ʾ�ı����ڵ���  
    private class UnfoldClickListener implements OnClickListener {  
        public void onClick(View v) {  
            if (isFolded) {  
                textView = new TextView(ShadeActivity.this);  
                textView.setTextColor(Color.BLUE);  
                textView.setTextSize(20);  
                textView.setText("������������ˮ���˻��Ծ�Ӣ�ۡ�\n" + "�Ƿǳɰ�תͷ�գ�\n"  
                        + "��ɽ�����ڣ�����Ϧ���졣\n" + "�׷����Խ���ϣ��߿����´��硣 \n"  
                        + "һ���Ǿ�ϲ��꣬\n" + "�Ž�����£�����Ц̸�С�");  
                textView.setGravity(Gravity.CENTER);  
                textView.setLayoutParams(new ViewGroup.LayoutParams(  
                        ViewGroup.LayoutParams.FILL_PARENT,  
                        ViewGroup.LayoutParams.FILL_PARENT));  
                textView.setBackgroundColor(Color.parseColor("#86222222"));  
  
                unfoldButton.setText("ȡ������");  
  
                isFolded = false;  
  
                layout.addView(textView);  
            } else {  
                unfoldButton.setText("��ʾ����");  
                isFolded = true;  
                layout.removeView(textView);  
            }  
        }  
    }  
}  