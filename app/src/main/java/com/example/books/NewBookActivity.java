package com.example.books;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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

        mAdd_btn = (Button) findViewById(R.id.delete_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);
        
        mAdd_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Book book = new Book();
            book.setAuthor(mAuthor_editTxt.getText().toString());
            book.setTitle(mTitle_editTxt.getText().toString());
            book.setIsbn(mISBN_editTxt.getText().toString());
            book.setCategory(mBook_categories_spinner.getSelectedItem().toString());

            new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void DataIsLoaded(List<Book> books, List<String> keys) {

                }

                @Override
                public void DataInserted() {
                    Toast.makeText(NewBookActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                }

                @Override
                public void DataIsUpdated() {

                }

                @Override
                public void DataisDeleted() {

                }
            });
            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                return;

            }
        });
    }
}
