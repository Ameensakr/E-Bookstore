package org.eBookStore.models;

public class PaperBook extends Book {
    int quantity;
    public PaperBook(double price, String ISBN, String title , int quantity) throws Exception{
        super(price, ISBN, title);
        if(quantity < 0){
            throw new Exception("can not add negative quantity");
        }
        this.quantity = quantity;
    }
    public boolean isOutOfStock()
    {
        return quantity == 0;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}
