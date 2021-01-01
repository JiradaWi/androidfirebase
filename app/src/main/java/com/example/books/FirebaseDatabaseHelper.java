package com.example.books;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBooks;
    private List<Book> books = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Book> books, List<String> keys);
        void DataInserted();
        void DataIsUpdated();
        void DataisDeleted();
    }

    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBooks = mDatabase.getReference(
                "books"
        );
    }

    public void readBooks(final DataStatus dataStatus){
        mReferenceBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Book book = keyNode.getValue(Book.class);
                    books.add(book);
                }

                dataStatus.DataIsLoaded(books, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
