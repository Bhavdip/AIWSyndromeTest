package com.neurological.todd.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.neurological.todd.dao.PatientDiagnosisDAO;

/**
 * Created by bhavdip on 8/21/16.
 */
public class ToddLiteDB extends SQLiteOpenHelper {

    private static final String TAG = "ToddLiteDB";
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "todd.db";

    /**
     * Create a helper object to create, open, and/or manage a database. This method always returns very quickly.
     * The database is not actually created or opened until one of getWritableDatabase()
     * or getReadableDatabase() is called.
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public ToddLiteDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // when we call SQLiteDatabase getWritableDatabase() this would called
        // In fist time application install if no database then it would called once but that time Application
        // database might be null we have to used here db
        Log.d(TAG, "OnCreate of Todd Lite Data Base");
        new PatientDiagnosisDAO(sqLiteDatabase).createPatientDiagnosisTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
