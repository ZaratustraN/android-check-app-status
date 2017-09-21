package cn.zaratustra.check.engine;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by zaratustra on 2017/9/21.
 */

public class CheckStatusAccessibilityService extends AccessibilityService {



    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){

        }
    }

    @Override
    public void onInterrupt() {

    }


}
