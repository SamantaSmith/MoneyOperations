package com.example.hrenmoney;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Operation.class, version = 1)
public abstract class OperationDatabase extends RoomDatabase {

    //ШАГ 7
    private static OperationDatabase instance;
    public abstract OperationDao operationDao();
    //ШАГ 7

    //ШАГ 8
    public static synchronized OperationDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), OperationDatabase.class, "operation_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }

        return instance;
    }
    //ШАГ 8

    //ШАГ 13
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //ШАГ 15
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    //ШАГ 13

    //ШАГ 14
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private OperationDao operationDao;

        private PopulateDbAsyncTask(OperationDatabase db) {
            operationDao = db.operationDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            operationDao.insert(new Operation("Stavki", "Sportivnoe sobytie na 1xbet", 500));
            return null;
        }
    }
    //ШАГ 14


}




