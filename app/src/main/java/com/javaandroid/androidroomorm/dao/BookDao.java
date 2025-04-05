package com.javaandroid.androidroomorm.dao;
import androidx.room.*;

import com.javaandroid.androidroomorm.model.Book;

import java.util.List;
@Dao
public interface BookDao {
    @Insert
    void insertBook(Book book);

    @Query("SELECT * FROM books WHERE author_id = :authorId")
    List<Book> getBooksByAuthorId(Long authorId);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);
}
