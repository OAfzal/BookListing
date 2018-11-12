package com.example.android.booklisting;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

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
                getData();

            }
        });

    }

    public void getData(){
        StringRequest stringRequest = new StringRequest(BOOKS_REQUEST_URL + (searchBox.getEditableText().toString()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                booksBackup = QueryUtils.extractFeaturesFromJSON(response);
                updateUI(QueryUtils.extractFeaturesFromJSON(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("getData",error.getMessage());

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
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

}



