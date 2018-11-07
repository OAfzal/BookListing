package com.example.android.booklisting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BookExtendedActivity extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_extended_view);

        String title = getIntent().getExtras().getString("title");

        String description = getIntent().getExtras().getString("description");

        TextView extendedMenuBookTitle = (TextView) findViewById(R.id.extended_menu_book_title);

        TextView extendedMenuBookDescription = (TextView) findViewById(R.id.extended_menu_book_description);

        extendedMenuBookTitle.setText(title);

        extendedMenuBookDescription.setText(description);
    }
}
