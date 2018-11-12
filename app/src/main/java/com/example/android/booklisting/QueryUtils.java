package com.example.android.booklisting;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

class QueryUtils{


    public static ArrayList<Book> extractFeaturesFromJSON(String jsonResponse){
        ArrayList<Book> booksArray = new ArrayList<Book>();

        int pageCount = 0;



        double averageRating = 0.0;

        String author ="", title="", publishedDate="", description="", category="", imageLinkURL="", language="", ISBN_13 ="";

        JSONArray items, authorArray, industryIdentifiers;

        JSONObject baseJSONObject, bookObject, volumeInfo, imageLinkObject;

        try {

            baseJSONObject = new JSONObject(jsonResponse);

            items = baseJSONObject.optJSONArray("items");

            for(int i =0; i<items.length();i++){

                bookObject = items.optJSONObject(i);

                volumeInfo = bookObject.optJSONObject("volumeInfo");

                title = volumeInfo.optString("title");

                Log.i("extraction",title);

                authorArray = volumeInfo.optJSONArray("authors");

                author = "";

                if(authorArray != null) {
                    for (int j = 0; j < authorArray.length(); j++) {

                        author += authorArray.getString(j);
                    }
                }
                else {
                    author = "Not Available";
                }

                publishedDate = volumeInfo.optString("publishedDate");

                description = volumeInfo.optString("description");

                category = volumeInfo.optString("categories");

                //double averageRating = (Double) volumeInfo.opt("averageRating");
                averageRating = volumeInfo.optDouble("averageRating");

                int ratingCount = volumeInfo.optInt("ratingsCount");

                imageLinkObject = volumeInfo.optJSONObject("imageLinks");

                if(imageLinkObject != null)
                    imageLinkURL = imageLinkObject.optString("thumbnail");

                pageCount = volumeInfo.optInt("pageCount");

                language = volumeInfo.optString("language");

                industryIdentifiers = volumeInfo.optJSONArray("industryIdentifiers");

                ISBN_13 = industryIdentifiers.optJSONObject(0).getString("identifier");

                if(ISBN_13  == null){
                    ISBN_13 = "N/A";
                }

                Book newBook = new Book(title, author, category, description, publishedDate, pageCount, imageLinkURL, ratingCount, language, averageRating, ISBN_13);


                Log.i("NewBook",newBook.toString());

                booksArray.add(newBook);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return booksArray;
    }


}
