package com.example.android.booklisting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("title")
    @Expose
    private String bookTitle;


    @SerializedName("authors")
    @Expose
    private String bookAuthor;

    @SerializedName("categories")
    @Expose
    private String bookCategory;

    @SerializedName("description")
    @Expose
    private String bookDescription;

    @SerializedName("publishedDate")
    @Expose
    private String bookPublishedDate;

    @SerializedName("pageCount")
    @Expose
    private int bookPageCount;
    //private String bookURL;

    @SerializedName("thumbnail")
    @Expose
    private String bookThumbnailURL;

    @SerializedName("ratingsCount")
    @Expose
    private int bookRatingCount;

    @SerializedName("language")
    @Expose
    private String bookLanguages;

    @SerializedName("averageRating")
    @Expose
    private double bookRating;

    @SerializedName("ISBN_13")
    @Expose
    private String ISBN_13;

    public Book() {
    }
    public Book(String bookTitle, String bookAuthor, String bookCategory, String bookDescription, String bookPublishedDate, int bookPageCount, String bookThumbnailURL, int bookRatingCount, String bookLanguages, double bookRating,String ISBN_13) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookDescription = bookDescription;
        this.bookPublishedDate = bookPublishedDate;
        this.bookPageCount = bookPageCount;
        this.bookThumbnailURL = bookThumbnailURL;
        this.bookRatingCount = bookRatingCount;
        this.bookLanguages = bookLanguages;
        this.bookRating = bookRating;
        this.ISBN_13 = ISBN_13 ;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookPublishedDate() {
        return bookPublishedDate;
    }

    public void setBookPublishedDate(String bookPublishedDate) {
        this.bookPublishedDate = bookPublishedDate;
    }

    public int getBookPageCount() {
        return bookPageCount;
    }

    public void setBookPageCount(int bookPageCount) {
        this.bookPageCount = bookPageCount;
    }

    public String getBookThumbnailURL() {
        return bookThumbnailURL;
    }

    public void setBookThumbnailURL(String bookThumbnailURL) {
        this.bookThumbnailURL = bookThumbnailURL;
    }

    public int getBookRatingCount() {
        return bookRatingCount;
    }

    public void setBookRatingCount(int bookRatingCount) {
        this.bookRatingCount = bookRatingCount;
    }

    public String getBookLanguages() {
        return bookLanguages;
    }

    public void setBookLanguages(String bookLanguages) {
        this.bookLanguages = bookLanguages;
    }

    public double getBookRating() {
        return bookRating;
    }

    public void setBookRating(double bookRating) {
        this.bookRating = bookRating;
    }

    public String getISBN_13() {
        return ISBN_13;
    }

    public void setISBN_13(String ISBN_13) {
        this.ISBN_13 = ISBN_13;
    }
}
