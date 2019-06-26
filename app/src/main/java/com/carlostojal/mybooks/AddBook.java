package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
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

    //Interface elements declaration
    private EditText titleField;
    private EditText writerField;
    private EditText publisherField;
    private EditText yearField;
    private EditText npagesField;
    private Button addbook_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book,container,false);

        titleField = (EditText) view.findViewById(R.id.title_field);
        writerField = (EditText) view.findViewById(R.id.writer_field);
        publisherField = (EditText) view.findViewById(R.id.publisher_field);
        yearField = (EditText) view.findViewById(R.id.year_field);
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
        String title = titleField.getText().toString(); //obligatory
        String writer = writerField.getText().toString() ; //obligatory
        String publisher = publisherField.getText().toString();
        int year = Integer.parseInt(yearField.getText().toString());
        int npages = Integer.parseInt(npagesField.getText().toString()); //obligatory

        if(!title.equals("")&&!writer.equals("")&&npages>=0) {
            try {
                Toast.makeText(getActivity(),"Please wait...",Toast.LENGTH_SHORT).show();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("books.csv",Context.MODE_APPEND));
                outputStreamWriter.write(title); //title
                outputStreamWriter.write("; ");
                outputStreamWriter.write(writer); //writer
                outputStreamWriter.write("; ");
                outputStreamWriter.write(publisher); //publisher
                outputStreamWriter.write("; ");
                outputStreamWriter.write(String.valueOf(year)); //year
                outputStreamWriter.write("; ");
                outputStreamWriter.write(String.valueOf(0)); //current page
                outputStreamWriter.write("; ");
                outputStreamWriter.write(String.valueOf(npages)); //number of pages
                outputStreamWriter.write("; ");
                outputStreamWriter.write("No information provided."); //what was happening
                outputStreamWriter.write("\n");
                outputStreamWriter.close();
                Toast.makeText(getActivity(),"Book added successfully.",Toast.LENGTH_SHORT).show();
                //cleans all input fields
                titleField.setText("");
                writerField.setText("");
                publisherField.setText("");
                yearField.setText("");
                npagesField.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"Error.",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"Error.",Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(getActivity(),"Obligatory fields can't be left empty.",Toast.LENGTH_SHORT).show();
    }
}
