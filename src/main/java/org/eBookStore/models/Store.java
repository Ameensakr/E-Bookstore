package org.eBookStore.models;

import org.eBookStore.services.EmailService;
import org.eBookStore.services.ShippingService;

public class Store {
    public static Inventory inventory;
    private static Store instance;


    private Store() {
        inventory = inventory.getInstance();
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void Buy(String ISBN, int quantity, String email, String address) throws Exception {
        inventory.removeOutDatedBooks(); // lazy update (remove outDated books)

        boolean existingFlag = false;
        for (Book book : inventory.Books) {
            if (book.getISBN().equals(ISBN)) {
                existingFlag = true;
                if (book instanceof DemoBook) throw new Exception("Sorry, This book is not for sale.");
                buyBook(book, quantity);
                if (book instanceof EBook)
                    EmailService.sendEmail(ISBN, email);
                else if (book instanceof PaperBook)
                    ShippingService.shipping(ISBN, address);
            }
        }
        if (!existingFlag) // indicator for non-existent book
        {
            throw new Exception("We do not have this book.");
        }
    }


    private void buyBook(Book book, int quantity) throws Exception {
        if (book instanceof PaperBook) {
            if (((PaperBook) book).getQuantity() < quantity) {
                throw new Exception("Book: " + book.getTitle() + " out of stock.");
            } else {
                ((PaperBook) book).setQuantity(((PaperBook) book).getQuantity() - quantity);
            }
        }

        double price = book.getPrice() * quantity;

        System.out.println("Paid amount is: " + price);
    }

}
