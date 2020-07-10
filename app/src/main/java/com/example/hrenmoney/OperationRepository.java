package com.example.hrenmoney;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OperationRepository {

    //ШАГ 9
    private OperationDao operationDao;
    private LiveData<List<Operation>> allOperations;

    public OperationRepository(Application application) {

        OperationDatabase database = OperationDatabase.getInstance(application);
        operationDao = database.operationDao();
        allOperations = operationDao.getAllOperations();
    }
    //ШАГ 9

    //ШАГ 10, 12
    public void insert(Operation operation) {
        new InsertOperationAsyncTask(operationDao).execute(operation);
    }

    public void update(Operation operation) {
        new UpdateOperationAsyncTask(operationDao).execute(operation);
    }

    public void delete(Operation operation) {
        new DeleteOperationAsyncTask(operationDao).execute(operation);
    }

    public void deleteAllOperations() {
        new DeleteAllOperationsAsyncTask(operationDao).execute();
    }

    public LiveData<List<Operation>> getAllOperations() {

        return allOperations;
    }
    //ШАГ 10, 12


    //ШАГ 11
    private static class InsertOperationAsyncTask extends AsyncTask<Operation, Void, Void> {

        private OperationDao operationDao;

        private InsertOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }

        @Override
        protected Void doInBackground(Operation... operations) {

            operationDao.insert(operations[0]);
            return null;
        }
    }

    private static class UpdateOperationAsyncTask extends AsyncTask<Operation, Void, Void> {

        private OperationDao operationDao;

        private UpdateOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }

        @Override
        protected Void doInBackground(Operation... operations) {

            operationDao.update(operations[0]);
            return null;
        }
    }

    private static class DeleteOperationAsyncTask extends AsyncTask<Operation, Void, Void> {

        private OperationDao operationDao;

        private DeleteOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }

        @Override
        protected Void doInBackground(Operation... operations) {

            operationDao.delete(operations[0]);
            return null;
        }
    }

    private static class DeleteAllOperationsAsyncTask extends AsyncTask<Void, Void, Void> {

        private OperationDao operationDao;

        private DeleteAllOperationsAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            operationDao.deleteAllOperations();
            return null;
        }
    }
    //ШАГ 11
}
