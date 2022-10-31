package com.example.book.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @Column(name = "pages")
    private int pages;

    @Lob
    @Column(name = "photo")
    private String photo;

    public Book() {
    }

//    public Book(String title, String author, String description, String category, Date releaseDate, int pages) {
//        this.title = title;
//        this.author = author;
//        this.description = description;
//        this.category = category;
//        this.releaseDate = releaseDate;
//        this.pages = pages;
//    }

    public Book(String title, String author, String description, String category, Date releaseDate, int pages, String photo) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.releaseDate = releaseDate;
        this.pages = pages;
        this.photo = photo;
    }

//    @Transient
//    public String getPhotosImagePath() {
//        if (photo == null)
//            return null;
//        return "/book-photos/" + bookId + "/" + photo;
//    }
}
