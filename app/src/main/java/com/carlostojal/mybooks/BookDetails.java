package com.carlostojal.mybooks;

//
// Copyright © Carlos Tojal (carlostojal)
// BookDetails.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BookDetails extends AppCompatActivity {

    TextView title;
    TextView writer;
    TextView publisher;
    TextView year;
    TextView genre;
    TextView npages;
    TextView was_happening;
    TextView cpage;
    TextView happened;

    Book old;
    Book newBook;
    BookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookManager = new BookManager();

        //gets intent extras to a Book object
        Bundle extras = getIntent().getExtras();
        old = new Book(extras.getString("ISBN"), extras.getString("Title"), extras.getString("Writer"), extras.getString("Publisher"), extras.getInt("Year"), extras.getString("Genre"), extras.getInt("Cpage"), extras.getInt("Npages"), extras.getInt("Nsaves"), extras.getStringArray("Was_happening"));
        newBook = old;

        //sets activity title to the title of the book
        setTitle(old.getTitle() + " - Details");

        //sets fields data
        title = (TextView) findViewById(R.id.title_value_details);
        writer = (TextView) findViewById(R.id.writer_value_details);
        publisher = (TextView) findViewById(R.id.publisher_value_details);
        year = (TextView) findViewById(R.id.year_value_details);
        genre = (TextView) findViewById(R.id.genre_value_details);
        npages = (TextView) findViewById(R.id.npages_value_details);
        was_happening = (TextView) findViewById(R.id.was_happening_value_details);
        cpage = (TextView) findViewById(R.id.cpage_value_details);
        happened = (TextView) findViewById(R.id.happened_value_details);


        title.setText(old.getTitle());
        writer.setText(old.getWriter());
        publisher.setText(old.getPublisher());
        genre.setText(old.getGenre());
        if (old.getYear() == 0) {
            year.setText("No information provided.");
        } else {
            year.setText(String.valueOf(old.getYear()));
        }
        npages.setText(String.valueOf(old.getNpages()));
        StringBuilder wasHappening = new StringBuilder();
        for (int i = 0; i < old.getNsaves(); i++) {
            wasHappening.append("• ");
            wasHappening.append(old.getWas_happening()[i]);
            wasHappening.append("\n");
        }
        was_happening.setText(wasHappening);
        cpage.setText(String.valueOf(old.getCpage()));
    }

    public void onRemove(View view) {
        Bundle extras = getIntent().getExtras();
        Book toRemove = new Book(extras.getString("ISBN"), extras.getString("Title"), extras.getString("Writer"), extras.getString("Publisher"), extras.getInt("Year"), extras.getString("Genre"), extras.getInt("Cpage"), extras.getInt("Npages"), extras.getInt("Nsaves"), extras.getStringArray("Was_happening"));

        ArrayList<Book> books = bookManager.loadBooks(getApplicationContext());

        bookManager.cleanBooks(getApplicationContext());

        for (int i = 0; i < books.size(); i++) {
            if (!books.get(i).getIsbn().equals(toRemove.getIsbn())) { //if current book is not the deleted, adds it
                bookManager.addBook(getApplicationContext(),books.get(i));
            }
        }
        Toast.makeText(getApplicationContext(), "Book removed successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onSave(View view) {
        newBook.setCpage(Integer.parseInt(cpage.getText().toString()));

        ArrayList<Book> books = bookManager.loadBooks(getApplicationContext());

        //if the page introduced as current is not a bigger value than total number of pages
        if (newBook.getCpage() <= old.getNpages() && !happened.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_SHORT).show();
            newBook.setNsaves(old.getNsaves() + 1);
            String[] wasHappening = new String[newBook.getNsaves()];
            for (int i = 0; i < newBook.getNsaves() - 1; i++) {
                wasHappening[i] = old.getWas_happening()[i];
            }
            wasHappening[newBook.getNsaves() - 1] = happened.getText().toString() + " (page " + newBook.getCpage() + ")";
            newBook.setWas_happening(wasHappening);

            bookManager.cleanBooks(getApplicationContext());
            bookManager.addBook(getApplicationContext(),newBook);
            //writes each book in the list, if it isn't the edited
            for (int i = 0; i < books.size(); i++) {
                if (!books.get(i).getIsbn().equals(old.getIsbn())) {
                    if(bookManager.addBook(getApplicationContext(),books.get(i))) {
                        Toast.makeText(getApplicationContext(), "Changes saved successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else
            Toast.makeText(getApplicationContext(), "Current page can't be a bigger value than the total number of pages and you have to describe what happened now.", Toast.LENGTH_LONG).show();
    }
}