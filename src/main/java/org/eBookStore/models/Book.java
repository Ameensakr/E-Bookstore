package org.eBookStore.models;

import java.time.LocalDate;


public class Book {
    private String ISBN;
    private String title;
    private LocalDate publishDate;
    private double price;

    // assume that the author create the object in the time he is published it.
    public Book(double price, String ISBN, String title) throws Exception {
        if(price<0){
            throw new Exception("can not add negative price");
        }
        this.price = price;
        if(ISBN == null || ISBN.isEmpty() ){
            throw new Exception("can not add empty ISBN");
        }
        this.ISBN = ISBN;
        this.title = title;
        this.publishDate = LocalDate.now(); // this book just published
    }


    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }


    public boolean isOutDated()
    {
        // outDated books: books that have been published from more than 5 years
        // current date  - published date
        int years = LocalDate.now().getYear() - publishDate.getYear();
        return years > 5;
    }


}
