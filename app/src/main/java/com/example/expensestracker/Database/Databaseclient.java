package com.example.expensestracker.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class Databaseclient {
    private Context mCtx;
    private static Databaseclient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private Databaseclient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "Expenses Tracker").build();
    }

    public static synchronized Databaseclient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new Databaseclient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
