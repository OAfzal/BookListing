package com.example.android.booklisting;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookExtendedFragment extends android.support.v4.app.Fragment {


    public BookExtendedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_book_extended,container,false);

        final TextView title = rootView.findViewById(R.id.fragment_menu_book_title);

        final TextView description = rootView.findViewById(R.id.fragment_menu_book_description);
        // Inflate the layout for this fragment

        description.setText(getArguments().getString("description"));

        title.setText(getArguments().getString("title"));

        return rootView;
    }
    public static BookExtendedFragment fragmentFactory(){
        return new BookExtendedFragment();
    }

}
