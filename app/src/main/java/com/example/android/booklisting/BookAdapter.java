package com.example.android.booklisting;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    Context mContext;


    public BookAdapter(@NonNull Context context, ArrayList<Book> resource) {
        super(context,0,resource);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view,parent,false);
        }

        final Book currentBook = getItem(position);

        final ImageView imageView = (ImageView) listItemView.findViewById(R.id.book_thumbnail);

        Glide.with(mContext).load(currentBook.getBookThumbnailURL()).into(imageView);

        TextView bookCategory = (TextView) listItemView.findViewById(R.id.book_category);

        char[] oldChar ={'[',']','"'};

        bookCategory.setText(refineCategory(currentBook.getBookCategory(),oldChar));

        TextView bookTitle = (TextView) listItemView.findViewById(R.id.book_title);

        bookTitle.setText(currentBook.getBookTitle());

        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.book_author);

        bookAuthor.setText(currentBook.getBookAuthor());

        RatingBar ratingBar = (RatingBar) listItemView.findViewById(R.id.book_rating);

        ratingBar.setRating((float)currentBook.getBookRating());

        TextView ratingsCount = (TextView) listItemView.findViewById(R.id.book_reviewers);

        ratingsCount.setText("("+currentBook.getBookRatingCount()+")");

        TextView bookLanguage = (TextView) listItemView.findViewById(R.id.language);

        bookLanguage.setText(currentBook.getBookLanguages());

        ImageView bookInfo = (ImageView) listItemView.findViewById(R.id.book_info);

        bookInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,BookExtendedActivity.class);
                intent.putExtra("description",currentBook.getBookDescription());
                intent.putExtra("title",currentBook.getBookTitle());
                mContext.startActivity(intent);
            }
        });

        return listItemView;
    }

    private String refineCategory(String category,char[] oldChars){
        String refinedCategory = "";

        for(int i=0; i<oldChars.length; i++){
            category = category.replace(oldChars[i],' ');
        }
        refinedCategory = category.trim();
        return refinedCategory;
    }
}
