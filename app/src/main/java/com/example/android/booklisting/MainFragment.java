package com.example.android.booklisting;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    public String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyBsLDs0hHCiRJa5dW8YPJS81pWLTMr8noo&prettyPrint=true&q=isbn:";

    View rootView;

    ArrayList<Book> books = new ArrayList<Book>();
    BookRecyclerAdapter adapter2;

    EditText searchBox;
    ImageButton searchButton;
    RecyclerView recyclerView;

    Bundle data;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {

            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            searchBox = rootView.findViewById(R.id.search_box);
            searchButton = rootView.findViewById(R.id.button_search);
            recyclerView = rootView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            data = new Bundle();


            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
                    getData();

                }
            });

        } else {
            // Do not inflate the layout again.
            // The returned View of onCreateView will be added into the fragment.
            // However it is not allowed to be added twice even if the parent is same.
            // So we must remove _rootView from the existing parent view group
            // in onDestroyView() (it will be added back).
        }
        return rootView;
    }


    public void getData() {
        StringRequest stringRequest = new StringRequest(BOOKS_REQUEST_URL + (searchBox.getEditableText().toString()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                books = QueryUtils.extractFeaturesFromJSON(response);
                adapter2 = new BookRecyclerAdapter(getContext(), books, getFragmentManager());
                recyclerView.setAdapter(adapter2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("getData", error.getMessage());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);

    }

    public static MainFragment createFragment() {
        return new MainFragment();
    }

    @Override
    public void onDestroyView() {
        if (rootView.getParent() != null) {
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }   


}
