package com.example.hrenmoney;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "operation_table")
public class Operation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String category;
    private String description;
    private int value;

    public Operation(String category, String description, int value) {
        this.category = category;
        this.description = description;
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
