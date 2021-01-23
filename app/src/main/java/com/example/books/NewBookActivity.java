package com.example.books;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class NewBookActivity extends AppCompatActivity {
    private EditText mAuthor_editTxt;
    private EditText mTitle_editTxt;
    private EditText mISBN_editTxt;
    private Spinner mBook_categories_spinner;
    private Button mAdd_btn;
    private Button mBack_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        mAuthor_editTxt = (EditText) findViewById(R.id.author_editTxt);
        mTitle_editTxt = (EditText) findViewById(R.id.title_editTxt);
        mISBN_editTxt = (EditText) findViewById(R.id.isbn_editTxt);
        mBook_categories_spinner = (Spinner) findViewById(R.id.book_categories_spinner);

        mAdd_btn = (Button) findViewById(R.id.add_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);
    }
}
