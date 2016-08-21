package com.neurological.todd.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.neurological.todd.ToddDiagnosticApplication;

/**
 * Created by bhavdip on 8/21/16.
 */
public abstract class BaseDAO {

    protected ToddDiagnosticApplication mDiagnosticApplication;
    protected SQLiteDatabase mSQLitBaseDAO;

    public BaseDAO() {
        mDiagnosticApplication = ToddDiagnosticApplication.getToddApplication();
        mSQLitBaseDAO = mDiagnosticApplication.mSqLiteDatabase;
    }

    public BaseDAO(SQLiteDatabase sqLiteDatabase) {
        mSQLitBaseDAO = sqLiteDatabase;
    }

    public boolean hasDBConnected() {
        return mSQLitBaseDAO != null;
    }


    public int getInt(Cursor aCursor, String columnName) {    //1. Returns the index for the given column name
        //2. then same cursor use to get value
        return aCursor.getInt(aCursor.getColumnIndexOrThrow(columnName));
    }

    public String getString(Cursor aCursor, String columnName) {
        //1. Returns the index for the given column name
        //2. then same cursor use to get value
        return aCursor.getString(aCursor.getColumnIndexOrThrow(columnName));
    }

    public long getLong(Cursor aCursor, String columnName) {
        return aCursor.getLong(aCursor.getColumnIndexOrThrow(columnName));
    }
}
