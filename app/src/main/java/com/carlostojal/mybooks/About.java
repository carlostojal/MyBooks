package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
// About.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class About extends Fragment {
    public static final String TAG = "AboutFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about,container,false);

        return view;
    }
}
