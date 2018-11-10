package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> booksBackup = new ArrayList<Book>();
    public BookAdapter adapter;

    RelativeLayout relativeLayout;
    EditText searchBox;

    public String BOOKS_REQUEST_URL ="https://www.googleapis.com/books/v1/volumes?key=AIzaSyBsLDs0hHCiRJa5dW8YPJS81pWLTMr8noo&prettyPrint=true&q=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        searchBox = (EditText) findViewById(R.id.search_box);

        relativeLayout = (RelativeLayout) findViewById(R.id.prograss_bar_layout);

        ImageButton searchButton =(ImageButton) findViewById(R.id.button_search);

        relativeLayout.setVisibility(View.GONE);

        adapter = new BookAdapter(this, new ArrayList<Book>());

        ListView bookListView = (ListView) findViewById(R.id.list_View);

        bookListView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
                new CustomAsyncTask().execute(BOOKS_REQUEST_URL+(searchBox.getEditableText().toString()));
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adapter.addAll(booksBackup);
    }

    public void updateUI(ArrayList<Book> booksToAdd){

        adapter.clear();
        adapter.addAll(booksToAdd);
    }

    class CustomAsyncTask extends AsyncTask<String,Void,ArrayList<Book>>{


        @Override
        protected ArrayList<Book> doInBackground(String... strings) {

            ArrayList<Book> books = new ArrayList<Book>();

            Log.i("MAINACTLINK",strings[0]);
            QueryUtils queryUtils = new QueryUtils(strings[0]);
            books =queryUtils.fetchData();

            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            booksBackup = books;
            updateUI(books);
        }
    }



}



