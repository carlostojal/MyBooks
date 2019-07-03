package com.carlostojal.mybooks;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BookManager {
    //Function that loads books from csv file and returns
    //them in form of an ArrayList
    public ArrayList<Book> loadBooks(Context context) {
        ArrayList<Book> books = new ArrayList<Book>();
        File file = new File(context.getFilesDir(), "books.csv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Book book;
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.split("; ");
                int nsaves = Integer.parseInt(splitStr[8]);
                //Toast.makeText(getContext(),splitStr[6],Toast.LENGTH_SHORT).show();
                String[] wasHappening = new String[nsaves];
                for (int i = 0; i < nsaves; i++) {
                    wasHappening[i] = splitStr[i + 9];
                }
                if (splitStr.length == 9 + nsaves) {
                    book = new Book(splitStr[0], splitStr[1], splitStr[2], splitStr[3], Integer.parseInt(splitStr[4]), splitStr[5], Integer.parseInt(splitStr[6]), Integer.parseInt(splitStr[7]), nsaves, wasHappening);
                    books.add(book);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
