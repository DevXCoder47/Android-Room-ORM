package com.javaandroid.androidroomorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.javaandroid.androidroomorm.model.Author;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onSubmitButtonClick(View sender) {
        Intent intent = new Intent(this, MainActivity.class);
        TextView txtv1 = findViewById(R.id.editId);
        TextView txtv2 = findViewById(R.id.editFName);
        TextView txtv3 = findViewById(R.id.editLName);
        Author author = new Author(Long.parseLong(txtv1.getText().toString()), txtv2.getText().toString(), txtv3.getText().toString());
        intent.putExtra(Author.class.getSimpleName(), author);
        startActivity(intent);
    }
}