/**
 * GetAppThumbnailActivity.java
 * Copyright (C) 2015
 * All right reserved. 2015-3-5
 */
package com.cayden.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 获取截图 并添加水印 
 * @author cuiran
 * @version 1.0.0
 */
public class GetAppThumbnailActivity extends Activity {

	private Button btnThum;  
    private ImageView imgThum;  
    private ImageView imgSource;  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.thumb);  
        setupViews();  
    }  
  
    private void setupViews() {  
        btnThum = (Button) findViewById(R.id.getThum);  
        imgThum = (ImageView) findViewById(R.id.setThum);  
        imgSource = (ImageView) findViewById(R.id.source);  
  
        btnThum.setOnClickListener(new OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
                Bitmap bitmap = getViewBitmap(imgSource);  
                Bitmap bitmap1 = createBitmap(bitmap, "haha哈哈");  
                if (bitmap1 != null) {  
                    imgThum.setImageBitmap(bitmap1);  
                }  
            }  
        });  
    }  
  
    /** 
     * Draw the view into a bitmap. 
     */  
    private Bitmap getViewBitmap(View v) {  
        v.clearFocus();  
        v.setPressed(false);  
  
        boolean willNotCache = v.willNotCacheDrawing();  
        v.setWillNotCacheDrawing(false);  
  
        // Reset the drawing cache background color to fully transparent  
        // for the duration of this operation  
        int color = v.getDrawingCacheBackgroundColor();  
        v.setDrawingCacheBackgroundColor(0);  
  
        if (color != 0) {  
            v.destroyDrawingCache();  
        }  
        v.buildDrawingCache();  
        Bitmap cacheBitmap = v.getDrawingCache();  
        if (cacheBitmap == null) {  
            return null;  
        }  
  
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);  
  
        // Restore the view  
        v.destroyDrawingCache();  
        v.setWillNotCacheDrawing(willNotCache);  
        v.setDrawingCacheBackgroundColor(color);  
  
        return bitmap;  
    }  
  
    // 给图片添加水印  
    private Bitmap createBitmap(Bitmap src, String str) {  
        Time t = new Time();  
        t.setToNow();   
        int w = src.getWidth();  
        int h = src.getHeight();  
        String mstrTitle = "截图时间："+t.hour + ":" + t.minute + ":" + t.second;  
        Bitmap bmpTemp = Bitmap.createBitmap(w, h, Config.ARGB_8888);  
        Canvas canvas = new Canvas(bmpTemp);  
        Paint p = new Paint();  
        String familyName = "宋体";  
        Typeface font = Typeface.create(familyName, Typeface.BOLD);  
        p.setColor(Color.BLUE);  
        p.setTypeface(font);  
        p.setTextSize(22);  
        canvas.drawBitmap(src, 0, 0, p);  
        canvas.drawText(mstrTitle, 0, 20, p);  
        canvas.save(Canvas.ALL_SAVE_FLAG);  
        canvas.restore();  
        return bmpTemp;  
    }  
}
