package gabdorahmanova.onthefence.Helpers;

import android.app.Fragment;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import  android.view.View.OnTouchListener;
/**
 * Created by admin on 23.03.2018.
 */

public abstract class OnSwipeTouchListener implements OnTouchListener {

    @SuppressWarnings("deprecation")
    private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());

    public boolean onTouch(final View v, final MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }



        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                } else {
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }


    public abstract void onSwipeRight();
    public abstract void onSwipeLeft() ;


}