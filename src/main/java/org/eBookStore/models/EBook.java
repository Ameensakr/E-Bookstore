package org.eBookStore.models;

public class EBook extends Book {
    String fileType;
    public EBook(double price, String ISBN, String title, String fileType) throws Exception {
        super(price, ISBN, title);
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
    }
}
