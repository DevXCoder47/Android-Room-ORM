package com.javaandroid.androidroomorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.javaandroid.androidroomorm.db.AppDatabase;
import com.javaandroid.androidroomorm.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private AuthorAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getInstance(this);

        ListView authorList = findViewById(R.id.listView);
        adapter = new AuthorAdapter(this, R.layout.author_list_item, new ArrayList<>());
        authorList.setAdapter(adapter);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            String firstName = arguments.getString("firstName");
            String lastName = arguments.getString("lastName");
            if (firstName != null && lastName != null) {
                Author author = new Author();
                author.firstName = firstName;
                author.lastName = lastName;
                Executors.newSingleThreadExecutor().execute(() -> {
                    db.authorDao().insertAuthor(author);
                    loadAuthors();
                });
            } else {
                loadAuthors();
            }
        } else {
            loadAuthors();
        }
    }

    private void loadAuthors() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Author> authors = db.authorDao().getAllAuthors();
            runOnUiThread(() -> {
                adapter.clear();
                adapter.addAll(authors);
                adapter.notifyDataSetChanged();
            });
        });
    }

    public void onAddButtonClick(View sender) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}