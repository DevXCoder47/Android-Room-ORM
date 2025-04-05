package com.javaandroid.androidroomorm.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import java.io.Serializable;

@Entity(tableName = "authors")
public class Author implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public Author(){}
    public Author(Long id, String fName, String lName) {
        this.id = id;
        firstName = fName;
        lastName = lName;
    }
}
