package com.example.hrenmoney;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class OperationViewModel extends AndroidViewModel {

    //ШАГ 16
    private OperationRepository repository;
    private LiveData<List<Operation>> allOperations;


    public OperationViewModel(@NonNull Application application) {
        super(application);

        repository = new OperationRepository(application);
        allOperations = repository.getAllOperations();
    }
    //ШАГ 16

    //ШАГ 17
    public void insert(Operation operation) {
        repository.insert(operation);
    }

    public void update(Operation operation) {
        repository.update(operation);
    }

    public void delete(Operation operation) {
        repository.delete(operation);
    }

    public void deleteAllOperations() {
        repository.deleteAllOperations();
    }

    public LiveData<List<Operation>> getAllOperations() {
        return allOperations;
    }
    //ШАГ 17


}
