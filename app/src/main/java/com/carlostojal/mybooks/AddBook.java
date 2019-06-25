package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
// AddBook.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddBook extends Fragment {
    public static final String TAG = "AddBookFragment";

    //Interface elements declaration
    private TextView titleText;
    private TextView writerText;
    private TextView publisherText;
    private TextView yearText;
    private TextView npagesText;
    private EditText titleField;
    private EditText writerField;
    private EditText publisherField;
    private EditText yearField;
    private EditText npagesField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book,container,false);

        titleText = (TextView) view.findViewById(R.id.title);
        writerText = (TextView) view.findViewById(R.id.writer);
        publisherText = (TextView) view.findViewById(R.id.publisher);
        yearText = (TextView) view.findViewById(R.id.year);
        npagesText = (TextView) view.findViewById(R.id.npages);
        titleField = (EditText) view.findViewById(R.id.title_field);
        writerField = (EditText) view.findViewById(R.id.writer_field);
        publisherField = (EditText) view.findViewById(R.id.publisher_field);
        yearField = (EditText) view.findViewById(R.id.year_field);
        npagesField = (EditText) view.findViewById(R.id.npages_field);

        return view;
    }
}
