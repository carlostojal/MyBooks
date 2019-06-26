package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
// BookDetails.java
// MyBooks
// github.com/carlostojal/MyBooks
//

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //gets intent extras to a Book object
        Bundle extras = getIntent().getExtras();
        Book old = new Book(extras.getString("Title"),extras.getString("Writer"),extras.getString("Publisher"),extras.getInt("Year"),extras.getInt("Cpage"),extras.getInt("Npages"),extras.getString("Was_happening"));

        //sets activity title to the title of the book
        setTitle("Overview - "+old.getTitle());

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
}
