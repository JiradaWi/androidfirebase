package com.example.books;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mBookAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext = context;
        mBookAdapter = new BooksAdapter(books, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBookAdapter);
    }

    class BookItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mISBN;
        private TextView mCategory;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false));

            mTitle      = (TextView) itemView.findViewById(R.id.title_txtview);
            mAuthor     = (TextView) itemView.findViewById(R.id.author_txtview);
            mISBN       = (TextView) itemView.findViewById(R.id.isbn_textview);
            mCategory   = (TextView) itemView.findViewById(R.id.category_txtview);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BookDetailActivity.class);

                    intent.putExtra("key", key);
                    intent.putExtra("title", mTitle.getText());

                    mContext.startActivity(intent);
                   // intent.putExtra("key", key);
                }
            });
        }
        public void bind(Book book, String key){
            mTitle.setText(book.getTitle());
            mAuthor.setText(book.getAuthor());
            mISBN.setText(book.getIsbn());
            mCategory.setText(book.getCategory());

            this.key = key;

        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book> mBookList, List<String> mKeys){
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @Override
        public BookItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(BookItemView holder, int position) {
           holder.bind(mBookList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
