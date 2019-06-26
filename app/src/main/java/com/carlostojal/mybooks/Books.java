package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
// Books.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Books extends Fragment {
    public static final String TAG = "BooksFragment";

    private ListView bookList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books,container,false);

        bookList = view.findViewById(R.id.booklist);
        ArrayList<Book> books = loadBooks();
        ArrayAdapter bookAdapter = new BookAdapter(getContext(),books);
        bookList.setAdapter(bookAdapter);

        return view;
    }

    private ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        File file = new File(getContext().getFilesDir(),"books.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Toast.makeText(getContext(),"Loading books. Please wait...",Toast.LENGTH_SHORT).show();
            Book book;
            while((line = br.readLine()) != null) {
                String[] splitStr=line.split("; ");
                if(splitStr.length==6) {
                    String title = splitStr[0];
                    book = new Book(splitStr[0],splitStr[1],splitStr[2],Integer.parseInt(splitStr[3]),Integer.parseInt(splitStr[4]),Integer.parseInt(splitStr[5]));
                    books.add(book);
                    //Toast.makeText(getContext(),splitStr[0]+"\n"+splitStr[1],Toast.LENGTH_SHORT).show();
                }
            }
            br.close();
            Toast.makeText(getContext(),"Loaded books.",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No books were found.",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Error loading books.",Toast.LENGTH_SHORT).show();
        }

        return books;
    }
}
