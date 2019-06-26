package com.carlostojal.mybooks;

//
// Copyright (c) Carlos Tojal (carlostojal)
// Book.java
// MyBooks
// github.com/carlostojal/MyBooks
//

public class Book {

    private String title;
    private String writer;
    private String publisher;
    private int year;
    private int cpage; //current page
    private int npages; //number of pages
    private String was_happening;

    public Book(String title,String writer,String publisher,int year,int cpage,int npages,String was_happening) {
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
        this.year = year;
        this.cpage = cpage;
        this.npages = npages;
        this.was_happening = was_happening;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getCpage() {
        return cpage;
    }

    public int getNpages() {
        return  npages;
    }

    public String getWas_happening() {
        return was_happening;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCpage(int cpage) {
        this.cpage = cpage;
    }

    public void setNpages(int npages) {
        this.npages = npages;
    }

    public void setWas_happening(String was_happening) {
        this.was_happening = was_happening;
    }
}
