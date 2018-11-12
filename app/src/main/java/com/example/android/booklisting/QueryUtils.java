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

    String link;

    public QueryUtils(String url) {
        link = url;
    }

    public ArrayList<Book> fetchData(){
        String jsonResponse = makeHTTPSRequest();
        ArrayList<Book> books = extractFeaturesFromJSON(jsonResponse);

        return books;
    }

    private String makeHTTPSRequest(){
        Log.i("QueryUtils","MakeHttpsRequest");
        String jsonResponse="";
        Log.i("the URL",link);
        try {
            URL url = new URL(link);

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            httpsURLConnection.setRequestMethod("GET");

            httpsURLConnection.connect();

            Log.i("makeHTTPSREQ","calling method readfrominputstream");

            InputStream inputStream = httpsURLConnection.getInputStream();

            jsonResponse = readInputStream(inputStream);
            Log.i("MakheHttpsreq","Back from readInputStream");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("RESPONSE",jsonResponse);

        return jsonResponse;


    }

    private String readInputStream(InputStream inputStream){

        StringBuilder output = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String line = bufferedReader.readLine();
            while(line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    private ArrayList<Book> extractFeaturesFromJSON(String jsonResponse){
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
