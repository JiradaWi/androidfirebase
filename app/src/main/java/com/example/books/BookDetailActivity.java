package com.example.books;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BookDetailActivity extends AppCompatActivity {
    private Button deleteBtn;

    private String key;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        key = getIntent().getStringExtra("key");
        title = getIntent().getStringExtra("title");

        TextView edit = (TextView) findViewById(R.id.title_editTxt);
        edit.setText(title);

        deleteBtn = (Button) findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<Book> books, List<String> keys) {

                            }

                            @Override
                            public void DataInserted() {

                            }

                            @Override
                            public void DataIsUpdated() {

                            }

                            @Override
                            public void DataisDeleted() {
                                Toast.makeText(BookDetailActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                                finish();
                                return;
                            }
                        });
                    }
                }
        );
    }
}
