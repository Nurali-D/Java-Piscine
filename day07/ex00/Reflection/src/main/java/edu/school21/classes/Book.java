package edu.school21.classes;

public class Book {
    private Integer publishingYear;
    private String author;
    private String name;
    private double price;
    private  boolean isInSale;

    public Book() {
    }

    public Book(int publishingYear, String author, String name, Double price, Boolean isInSale) {
        this.publishingYear = publishingYear;
        this.author = author;
        this.name = name;
        this.price = price;
        this.isInSale = isInSale;
    }

    public double increasePrice(Double diff)
    {
        price += diff;
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "publishingYear=" + publishingYear +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isInSale=" + isInSale +
                '}';
    }
}
