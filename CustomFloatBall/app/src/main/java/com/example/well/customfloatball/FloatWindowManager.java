package com.example.well.customfloatball;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by Well on 2016/12/5.
 */

public class FloatWindowManager {

    private static WindowManager mWm;
    private static FloatBallView floatBallView;

    public static void addBallView(Context context) {
        if (floatBallView == null) {
            WindowManager wm = getWindowManager(context);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
            params.x = width;
            params.y = height/2;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.LEFT | Gravity.TOP;
            params.format = PixelFormat.RGBA_8888;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            floatBallView = new FloatBallView(context);
            floatBallView.setLayoutParam(params);
            wm.addView(floatBallView, params);
        }
    }

    public static void removeBallView(Context context) {
        if (floatBallView != null) {
            WindowManager wm = getWindowManager(context);
            wm.removeView(floatBallView);
            floatBallView = null;
        }
    }

    private static WindowManager getWindowManager(Context context) {
        if (mWm == null) {
            mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWm;
    }
}
