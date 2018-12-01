package com.example.android.booklisting;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        final Button btn_back = rootView.findViewById(R.id.back_button);

        description.setText(getArguments().getString("description"));

        title.setText(getArguments().getString("title"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean fragmentPopped = getFragmentManager().popBackStackImmediate();

                if(!fragmentPopped){
                    Log.i("BEFM","CREATING AGAIN");
                    MainFragment mainFragment = MainFragment.createFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container,mainFragment).commit();
                }


            }
        });

        return rootView;
    }
    public static BookExtendedFragment fragmentFactory(){
        return new BookExtendedFragment();
    }

}
