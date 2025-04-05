package com.javaandroid.androidroomorm.dao;
import androidx.room.*;

import com.javaandroid.androidroomorm.model.Author;

import java.util.List;
@Dao
public interface AuthorDao {
    @Insert
    void insertAuthor(Author author);

    @Query("SELECT * FROM authors")
    List<Author> getAllAuthors();
}
