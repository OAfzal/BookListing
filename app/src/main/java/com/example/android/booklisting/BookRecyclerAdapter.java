package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.MyViewHolder>{

    private ArrayList<Book> mBooks;

    public BookRecyclerAdapter(ArrayList<Book> mBooks) {
        this.mBooks = mBooks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bookView = inflater.inflate(R.layout.list_item_view_2, viewGroup,false);

        MyViewHolder viewHolder = new MyViewHolder(bookView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Book book = mBooks.get(position);

        TextView book_title_1 = myViewHolder.book_title_1;
        book_title_1.setText(book.getBookTitle());

        TextView book_author_1 = myViewHolder.book_author_1;
        book_author_1.setText(book.getBookAuthor());
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_title_1;
        TextView book_author_1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_title_1 =(TextView) itemView.findViewById(R.id.book_title_1);
            book_author_1 = (TextView) itemView.findViewById(R.id.book_author_1);
        }



    }


}
