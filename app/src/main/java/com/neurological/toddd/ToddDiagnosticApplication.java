package com.neurological.toddd;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by bhavdip on 8/20/16.
 */
public class ToddDiagnosticApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpCalligraphy();
    }

    private void setUpCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Bold.ttf")
                .setFontAttrId(R.attr.fontPath).build());

    }
}
