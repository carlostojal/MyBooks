package com.carlostojal.mybooks;

//
// Copyright © Carlos Tojal (carlostojal)
// Books.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private ArrayList<Book> books;
    private boolean isStarted = false;
    private boolean isVisible = false;

    @Override
    public void onStart() {
       super.onStart();
       isStarted = true;
       if(isVisible&&isStarted) {
           books = loadBooks();
           if(books.size()==0) {
               Toast.makeText(getContext(),"No books were found.",Toast.LENGTH_SHORT).show();
           }
           ArrayAdapter bookAdapter = new BookAdapter(getContext(),books);
           bookList.setAdapter(bookAdapter);
       }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if(isStarted&&isVisible) {
            books = loadBooks();
            if(books.size()==0) {
                Toast.makeText(getContext(),"No books were found.",Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter bookAdapter = new BookAdapter(getContext(),books);
            bookList.setAdapter(bookAdapter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books,container,false);

        //Toast.makeText(getContext(),"Test",Toast.LENGTH_SHORT).show();

        books = loadBooks();
        if(books.size()==0) {
            Toast.makeText(getContext(),"No books were found.",Toast.LENGTH_SHORT).show();
        }
        bookList = view.findViewById(R.id.booklist);
        ArrayAdapter bookAdapter = new BookAdapter(getContext(),books);
        bookList.setAdapter(bookAdapter);

        //gets element click
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book selectedBook = (Book) adapterView.getItemAtPosition(i);
                //open the book activity
                Intent intent = new Intent(Books.this.getActivity(),BookDetails.class);
                intent.putExtra("ISBN",selectedBook.getIsbn());
                intent.putExtra("Title",selectedBook.getTitle());
                intent.putExtra("Writer",selectedBook.getWriter());
                intent.putExtra("Publisher",selectedBook.getPublisher());
                intent.putExtra("Year",selectedBook.getYear());
                intent.putExtra("Genre",selectedBook.getGenre());
                intent.putExtra("Cpage",selectedBook.getCpage());
                intent.putExtra("Npages",selectedBook.getNpages());
                intent.putExtra("Nsaves",selectedBook.getNsaves());
                intent.putExtra("Was_happening",selectedBook.getWas_happening());
                startActivity(intent);
            }
        });

        return view;
    }

    public ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        File file = new File(getContext().getFilesDir(),"books.csv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            //Toast.makeText(getContext(),"Loading books. Please wait...",Toast.LENGTH_SHORT).show();
            Book book;
            while((line = br.readLine()) != null) {
                String[] splitStr=line.split("; ");
                int nsaves = Integer.parseInt(splitStr[8]);
                //Toast.makeText(getContext(),splitStr[6],Toast.LENGTH_SHORT).show();
                String[] wasHappening = new String[nsaves];
                for(int i=0;i<nsaves;i++) {
                    wasHappening[i] = splitStr[i+9];
                }
                if(splitStr.length==9+nsaves) {
                    book = new Book(splitStr[0],splitStr[1],splitStr[2],splitStr[3],Integer.parseInt(splitStr[4]),splitStr[5],Integer.parseInt(splitStr[6]),Integer.parseInt(splitStr[7]),nsaves,wasHappening);
                    books.add(book);
                }
            }
            br.close();
            //Toast.makeText(getContext(),"Loaded books.",Toast.LENGTH_SHORT).show();
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
