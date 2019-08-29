package com.example.expensestracker.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Task.class},version = 1)
@TypeConverters({converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
}
