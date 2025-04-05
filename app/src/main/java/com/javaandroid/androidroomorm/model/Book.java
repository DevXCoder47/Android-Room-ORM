package com.javaandroid.androidroomorm.model;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import java.io.Serializable;

@Entity(
        tableName = "books",
        foreignKeys = @ForeignKey(
                entity = Author.class,
                parentColumns = "id",
                childColumns = "author_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Book implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String title;
    public String genre;
    public int year;

    @ColumnInfo(name = "author_id")
    public Long authorId;
}
