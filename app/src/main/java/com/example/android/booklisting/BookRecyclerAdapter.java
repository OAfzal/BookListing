package com.example.android.booklisting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.MyViewHolder>{

    Context context;

    FragmentManager fragmentManager;

    private ArrayList<Book> mBooks;

    public BookRecyclerAdapter(Context context,ArrayList<Book> mBooks, FragmentManager manager) {
        this.mBooks = mBooks;
        this.context = context;
        fragmentManager = manager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View bookView = inflater.inflate(R.layout.list_item_view, viewGroup,false);

        MyViewHolder viewHolder = new MyViewHolder(bookView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        final Book book = mBooks.get(position);

        myViewHolder.book_title.setText(book.getBookTitle());
        myViewHolder.book_author.setText(book.getBookAuthor());
        myViewHolder.book_category.setText(book.getBookCategory());
        myViewHolder.book_reviewers.setText(Integer.toString(book.getBookRatingCount()));
        myViewHolder.book_languages.setText(book.getBookLanguages());
        myViewHolder.ratingBar.setRating((float) book.getBookRating());

        myViewHolder.book_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookExtendedFragment bookExtendedFragment = BookExtendedFragment.fragmentFactory();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                Bundle data = new Bundle();
                data.putString("description",book.getBookDescription());
                data.putString("title",book.getBookTitle());

                bookExtendedFragment.setArguments(data);

                fragmentTransaction.replace(R.id.fragment_container,bookExtendedFragment).addToBackStack(getClass().getName()).commit();


//                Intent intent = new Intent(context,BookExtendedActivity.class);
//                intent.putExtra("description",book.getBookDescription());
//                intent.putExtra("title",book.getBookTitle());
//                context.startActivity(intent);
            }
        });
        Glide.with(context).load(book.getBookThumbnailURL()).into(myViewHolder.book_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_title;
        TextView book_author;
        TextView book_category;
        TextView book_reviewers;
        TextView book_languages;

        ImageView book_thumbnail;
        ImageView book_info;

        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_title = itemView.findViewById(R.id.book_title);
            book_author = itemView.findViewById(R.id.book_author);
            book_category = itemView.findViewById(R.id.book_category);
            book_reviewers = itemView.findViewById(R.id.book_reviewers);
            book_languages = itemView.findViewById(R.id.language);
            book_thumbnail = itemView.findViewById(R.id.book_thumbnail);
            book_info = itemView.findViewById(R.id.book_info);
            ratingBar = itemView.findViewById(R.id.book_rating);
        }
    }

}
