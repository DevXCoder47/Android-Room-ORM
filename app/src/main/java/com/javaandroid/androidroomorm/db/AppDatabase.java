package com.javaandroid.androidroomorm.db;
import androidx.room.*;
import com.javaandroid.androidroomorm.dao.AuthorDao;
import com.javaandroid.androidroomorm.dao.BookDao;
import com.javaandroid.androidroomorm.model.Author;
import com.javaandroid.androidroomorm.model.Book;

@Database(entities = {Author.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuthorDao authorDao();
    public abstract BookDao bookDao();
}
