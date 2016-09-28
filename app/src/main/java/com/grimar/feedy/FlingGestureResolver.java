package com.grimar.feedy;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class FlingGestureResolver extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY_MAX = 800;

    private MainActivity mainActivity;

    public FlingGestureResolver(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float dX = e2.getX() - e1.getX();
        float dY = e2.getY() - e1.getY();
        if (Math.abs(velocityX) >= SWIPE_THRESHOLD_VELOCITY && Math.abs(dX) >= mainActivity.getMINSWIPE() && Math.abs(velocityX) <= SWIPE_THRESHOLD_VELOCITY_MAX && Math.abs(dX) > Math.abs(dY) && e1.getY() > mainActivity.getDeviceHeight() * 1 / 7) {
            if (dX > 0) {
                if (!mainActivity.getMenuListExpanded()) mainActivity.toggleArticleList();
            } else {
                if (mainActivity.getMenuListExpanded()) mainActivity.toggleArticleList();
            }
            return true;

        }
        return false;
    }
}
