package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import java.util.ArrayList;

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    String link;
    public BookLoader(@NonNull Context context,String link) {
        super(context);
        this.link = link;
    }

    @Nullable
    @Override
    public ArrayList<Book> loadInBackground() {
        Log.i("BookLoader","loadInBackground");

        QueryUtils queryUtils = new QueryUtils(link);
        ArrayList<Book> books =queryUtils.fetchData();
        return  books;
    }
}

