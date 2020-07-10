package com.example.hrenmoney;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OperationDao {

    @Insert
    void insert(Operation operation);

    @Update
    void update(Operation operation);

    @Delete
    void delete(Operation operation);

    @Query("DELETE FROM operation_table")
    void deleteAllOperations();

    @Query("SELECT * FROM operation_table")
    LiveData<List<Operation>> getAllOperations();
}
