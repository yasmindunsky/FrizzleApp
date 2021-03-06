package com.frizzl.app.frizzleapp.practice;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.frizzl.app.frizzleapp.SwipeDirection;

public class PracticeViewPager extends ViewPager {
    private SwipeDirection direction;
    private boolean enabled;
    private float initialXValue;

    public PracticeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
        this.direction = SwipeDirection.all;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.IsSwipeAllowed(event) && super.onTouchEvent(event);

    }

    private boolean IsSwipeAllowed(MotionEvent event) {
        if(this.direction == SwipeDirection.all) return true;

        if(direction == SwipeDirection.none )//disable any swipe
            return false;

        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            initialXValue = event.getX();
            return true;
        }

        if(event.getAction()==MotionEvent.ACTION_MOVE) {
            try {
                float diffX = event.getX() - initialXValue;
                if (diffX > 0 && direction == SwipeDirection.right ) {
                    // swipe from left to right detected
                    return false;
                }else if (diffX < 0 && direction == SwipeDirection.left ) {
                    // swipe from right to left detected
                    return false;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return true;
    }

    public void setAllowedSwipeDirection(SwipeDirection direction) {
        this.direction = direction;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.IsSwipeAllowed(event) && super.onInterceptTouchEvent(event);

    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}