package com.example.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_books);
        new FirebaseDatabaseHelper().readBooks(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Book> books, List<String> keys) {
                findViewById(R.id.loading_book).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView, BookListActivity.this, books, keys);
            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataisDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.booklist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem){
        switch (menuitem.getItemId()){
            case R.id.new_book:
                startActivity(new Intent(this, NewBookActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(menuitem);
    }
}
