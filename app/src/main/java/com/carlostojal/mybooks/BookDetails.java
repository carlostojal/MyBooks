package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
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
    TextView npages;
    TextView was_happening;
    TextView cpage;
    TextView happened;

    Book old;
    Book newBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //gets intent extras to a Book object
        Bundle extras = getIntent().getExtras();
        old = new Book(extras.getString("Title"),extras.getString("Writer"),extras.getString("Publisher"),extras.getInt("Year"),extras.getInt("Cpage"),extras.getInt("Npages"),extras.getString("Was_happening"));
        newBook = old;

        //sets activity title to the title of the book
        setTitle(old.getTitle()+" - Details");

        //sets fields data
        title = (TextView) findViewById(R.id.title_value_details);
        writer = (TextView) findViewById(R.id.writer_value_details);
        publisher = (TextView) findViewById(R.id.publisher_value_details);
        year = (TextView) findViewById(R.id.year_value_details);
        npages = (TextView) findViewById(R.id.npages_value_details);
        was_happening = (TextView) findViewById(R.id.was_happening_value_details);
        cpage = (TextView) findViewById(R.id.cpage_value_details);
        happened = (TextView) findViewById(R.id.happened_value_details);


        title.setText(old.getTitle());
        writer.setText(old.getWriter());
        publisher.setText(old.getPublisher());
        year.setText(String.valueOf(old.getYear()));
        npages.setText(String.valueOf(old.getNpages()));
        was_happening.setText(old.getWas_happening());
        cpage.setText(String.valueOf(old.getCpage()));
        happened.setText(old.getWas_happening());
    }

    public void onSave(View view) {

        newBook.setCpage(Integer.parseInt(cpage.getText().toString()));
        newBook.setWas_happening(happened.getText().toString());

        ArrayList<Book> books = loadBooks();
        Toast.makeText(getApplicationContext(),String.valueOf(books.size()),Toast.LENGTH_SHORT).show();

        //cleans previous file content
        File file = new File(getApplicationContext().getFilesDir(),"books.csv");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("books.csv", Context.MODE_APPEND));
            //puts edited book on the top of the list
            outputStreamWriter.write(newBook.getTitle());
            outputStreamWriter.write("; ");
            outputStreamWriter.write(newBook.getWriter());
            outputStreamWriter.write("; ");
            outputStreamWriter.write(newBook.getPublisher());
            outputStreamWriter.write("; ");
            outputStreamWriter.write(String.valueOf(newBook.getYear()));
            outputStreamWriter.write("; ");
            outputStreamWriter.write(String.valueOf(newBook.getCpage()));
            outputStreamWriter.write("; ");
            outputStreamWriter.write(String.valueOf(newBook.getNpages()));
            outputStreamWriter.write("; ");
            outputStreamWriter.write(newBook.getWas_happening());
            outputStreamWriter.write("\n");
            //writes each book in the list, if it isn't the edited
            for(int i=0;i<books.size();i++) {
                if(!books.get(i).getTitle().equals(old.getTitle())||!books.get(i).getWriter().equals(old.getWriter())) {
                    outputStreamWriter.write(books.get(i).getTitle());
                    outputStreamWriter.write("; ");
                    outputStreamWriter.write(books.get(i).getWriter());
                    outputStreamWriter.write("; ");
                    outputStreamWriter.write(books.get(i).getPublisher());
                    outputStreamWriter.write("; ");
                    outputStreamWriter.write(String.valueOf(books.get(i).getYear()));
                    outputStreamWriter.write("; ");
                    outputStreamWriter.write(String.valueOf(books.get(i).getCpage()));
                    outputStreamWriter.write("; ");
                    outputStreamWriter.write(String.valueOf(books.get(i).getNpages()));
                    outputStreamWriter.write("; ");
                    outputStreamWriter.write(books.get(i).getWas_happening());
                    outputStreamWriter.write("\n");
                }
            }
            outputStreamWriter.close();
            Toast.makeText(getApplicationContext(),"Changes saved successfully.",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error.",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error.",Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        File file = new File(getApplicationContext().getFilesDir(),"books.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Book book;
            while((line = br.readLine()) != null) {
                String[] splitStr=line.split("; ");
                if(splitStr.length==7) {
                    String title = splitStr[0];
                    book = new Book(splitStr[0],splitStr[1],splitStr[2],Integer.parseInt(splitStr[3]),Integer.parseInt(splitStr[4]),Integer.parseInt(splitStr[5]),splitStr[6]);
                    books.add(book);
                }
            }
            br.close();
            //Toast.makeText(getApplicationContext(),stringBuilder,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error.",Toast.LENGTH_SHORT).show();
        }

        return books;
    }
}