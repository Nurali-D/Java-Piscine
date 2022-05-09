package edu.school21.models;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

@OrmEntity(table = "books")
public class Book {
    @OrmColumnId
    private Long id;
    @OrmColumn(name = "author", length = 60)
    private String author;

    @OrmColumn(name = "name", length = 80)
    private String name;

    @OrmColumn(name = "price")
    private Double price;

    @OrmColumn(name = "year")
    private Integer year;
    @OrmColumn(name = "is_in_sale")
    private Boolean isInSale;

    public Book() {
    }

    public Book(Long id, String author, String name, Double price, Integer year, Boolean isInSale) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.price = price;
        this.year = year;
        this.isInSale = isInSale;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", isInSale=" + isInSale +
                '}';
    }
}