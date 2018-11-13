package com.example.android.booklisting;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> books= new ArrayList<Book>();
    BookRecyclerAdapter adapter2;

    EditText searchBox;
    ImageButton searchButton;
    RecyclerView recyclerView;

    public String BOOKS_REQUEST_URL ="https://www.googleapis.com/books/v1/volumes?key=AIzaSyBsLDs0hHCiRJa5dW8YPJS81pWLTMr8noo&prettyPrint=true&q=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        searchBox = (EditText) findViewById(R.id.search_box);
        searchButton =(ImageButton) findViewById(R.id.button_search);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


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
        StringRequest stringRequest = new StringRequest(BOOKS_REQUEST_URL + (searchBox.getEditableText().toString()) , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                books = QueryUtils.extractFeaturesFromJSON(response);
                adapter2 = new BookRecyclerAdapter(getApplicationContext(),books);
                recyclerView.setAdapter(adapter2);
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

}

