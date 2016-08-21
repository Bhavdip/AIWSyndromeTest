package com.neurological.todd;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.neurological.todd.data.ToddLiteDB;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by bhavdip on 8/20/16.
 */
public class ToddDiagnosticApplication extends Application {

    private static ToddDiagnosticApplication mDiagnosticApplication;
    public SQLiteDatabase mSqLiteDatabase;


    public static ToddDiagnosticApplication getToddApplication() {
        return mDiagnosticApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDiagnosticApplication = this;
        loadLocalDataBase();
        loadCalligraphy();
    }

    private void loadLocalDataBase() {
        ToddLiteDB toddLiteDB = new ToddLiteDB(this, ToddLiteDB.DATABASE_NAME, null, ToddLiteDB.DATABASE_VERSION);
        // it return null if first time database create onCreate() called,
        // once database create it will open the database so openDataBase() called
        mSqLiteDatabase = toddLiteDB.getWritableDatabase();
    }

    private void loadCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.font_mont_regular))
                .setFontAttrId(R.attr.fontPath).build());
    }
}
