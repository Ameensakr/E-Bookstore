package org.eBookStore.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    public List<Book> Books;
    private Inventory()
    {
        Books = new ArrayList<>();
    }

    public static Inventory getInstance()
    {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }
    public void addBook(Book Book)
    {
        try {
            if(Book == null)throw new Exception("You can not add null Book");
            Books.add(Book);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void remove(Book Book)
    {
        if(Books.contains(Book))
            Books.remove(Book);
    }

    public List<Book> removeOutDatedBooks()
    {
        List<Book> outDatedBooks = new ArrayList<>();

        for(Book book : Books)
        {
            if(book.isOutDated())
            {
                outDatedBooks.add(book);
            }
        }
        for (Book outDatedBook : outDatedBooks) {
            Books.remove(outDatedBook);
        }
        return outDatedBooks;
    }

    public void clearBooks() {
        Books.clear();
    }


}
