package com.javaandroid.androidroomorm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javaandroid.androidroomorm.db.AppDatabase;
import com.javaandroid.androidroomorm.model.Author;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class AuthorAdapter extends ArrayAdapter<Author> {
    private final LayoutInflater inflater;
    private final int layout;
    private final ArrayList<Author> authorList;
    private final AppDatabase db;

    AuthorAdapter(Context context, int resource, ArrayList<Author> authors) {
        super(context, resource, authors);
        this.authorList = authors;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.db = AppDatabase.getInstance(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Author author = authorList.get(position);
        viewHolder.idView.setText(String.valueOf(author.id));
        viewHolder.fNameView.setText(author.firstName);
        viewHolder.lNameView.setText(author.lastName);

        viewHolder.editButton.setOnClickListener(v -> {
            // Пример обновления: добавим префикс к имени
            String newFirstName = "Updated " + author.firstName;
            author.firstName = newFirstName;

            Executors.newSingleThreadExecutor().execute(() -> {
                db.authorDao().insertAuthor(author); // insert с тем же id заменит запись
                // Альтернатива: db.authorDao().updateAuthor(author); если использовать @Update
            });

            viewHolder.fNameView.setText(author.firstName);
            Toast.makeText(getContext(), "Автор обновлён", Toast.LENGTH_SHORT).show();
        });

        viewHolder.deleteButton.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                db.authorDao().deleteAuthor(author);
            });
            authorList.remove(position);
            notifyDataSetChanged();
            Toast.makeText(getContext(), "Автор удалён", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }

    private static class ViewHolder {
        final Button editButton, deleteButton;
        final TextView idView, fNameView, lNameView;
        ViewHolder(View view){
            editButton = view.findViewById(R.id.editButton);
            deleteButton = view.findViewById(R.id.deleteButton2);
            idView = view.findViewById(R.id.editId);
            fNameView = view.findViewById(R.id.editFName);
            lNameView = view.findViewById(R.id.editLName);
        }
    }
}