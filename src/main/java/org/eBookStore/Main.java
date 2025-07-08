package org.eBookStore;

import org.eBookStore.models.DemoBook;
import org.eBookStore.models.EBook;
import org.eBookStore.models.PaperBook;
import org.eBookStore.models.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Main {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();
        store.inventory.clearBooks();
        PaperBook paperBook = new PaperBook(100.0, "ISBN-PAPER", "Paper Book", 5);
        EBook eBook = new EBook(50.0, "ISBN-EBOOK", "E-Book", "pdf");
        DemoBook demoBook = new DemoBook(0.0, "ISBN-DEMO", "Demo Book");
        store.inventory.addBook(paperBook);
        store.inventory.addBook(eBook);
        store.inventory.addBook(demoBook);
        try {
            store.Buy("ISBN-DEMO", 1, "user@example.com", null);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
}