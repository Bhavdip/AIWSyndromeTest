package com.neurological.todd.utility;

import android.app.Activity;

import com.neurological.todd.R;

/**
 * Created by bhavdip on 8/21/16.
 */
public class Utils {

    public static void activityOpenBottomTransition(Activity activity) {
        if (activity != null) {
            activity.overridePendingTransition(R.anim.slide_up, R.anim.stay_its);
        }
    }

    public static void activityCloseBottomTransition(Activity activity) {
        if (activity != null) {
            activity.overridePendingTransition(R.anim.stay_its, R.anim.slide_down);
        }
    }
}
