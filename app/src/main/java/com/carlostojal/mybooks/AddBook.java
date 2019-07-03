package com.carlostojal.mybooks;

//
// Copyright © Carlos Tojal (carlostojal)
// AddBook.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddBook extends Fragment {
    public static final String TAG = "AddBookFragment";

    BookManager bookManager;

    //Interface elements declaration
    private EditText isbnField;
    private EditText titleField;
    private EditText writerField;
    private EditText publisherField;
    private EditText yearField;
    private EditText genreField;
    private EditText npagesField;
    private Button addbook_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book,container,false);

        bookManager = new BookManager();

        isbnField = (EditText) view.findViewById(R.id.isbn_field);
        titleField = (EditText) view.findViewById(R.id.title_field);
        writerField = (EditText) view.findViewById(R.id.writer_field);
        publisherField = (EditText) view.findViewById(R.id.publisher_field);
        yearField = (EditText) view.findViewById(R.id.year_field);
        genreField = (EditText) view.findViewById(R.id.genre_field);
        npagesField = (EditText) view.findViewById(R.id.npages_field);
        addbook_button = (Button) view.findViewById(R.id.addbook_button);

        addbook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddBook(getContext());
            }
        });

        return view;
    }

    public void onAddBook(Context context) {
        String isbn = isbnField.getText().toString(); //obligatory
        String title = titleField.getText().toString(); //obligatory
        String writer = writerField.getText().toString() ; //obligatory
        String publisher = publisherField.getText().toString();
        String genre = genreField.getText().toString();
        int year;
        int npages;
        if(yearField.getText().toString().equals(""))
            year = 0;
        else
            year = Integer.parseInt(yearField.getText().toString());

        if(genre.equals("")) {
            genre = "No information provided.";
        }

        if(npagesField.getText().toString().equals(""))
            npages = 0;
        else
            npages = Integer.parseInt(npagesField.getText().toString()); //obligatory

        String[] wasHappening = new String[1];
        wasHappening[0] = "Book add.";

        Book newBook = new Book(isbn,title,writer,publisher,year,genre,0,npages,1,wasHappening);

        //if no field was left empty
        if(!title.equals("")&&!writer.equals("")&&npages>0) {
            if(bookManager.addBook(getContext(),newBook)) { //if adding was successful
                isbnField.setText("");
                titleField.setText("");
                writerField.setText("");
                publisherField.setText("");
                yearField.setText("");
                genreField.setText("");
                npagesField.setText("");
                Toast.makeText(getActivity(),"Book added successfully.",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(),"Error.",Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(getActivity(),"Obligatory fields can't be left empty and number of pages can't be negative.",Toast.LENGTH_SHORT).show();
    }
}
