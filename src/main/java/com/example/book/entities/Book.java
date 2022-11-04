package com.example.book.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotBlank(message = "Must enter title")
    @Size(min = 1, max = 255)
    private String title;

    @NotBlank(message = "Must enter author name")
    @Size(min = 1, max = 255)
    @Column(name = "author")
    private String author;

    @NotBlank(message = "Must enter description")
    @Size(min = 1, max = 1000)
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Must select a category")
    @Column(name = "category")
    private String category;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @Min(value = 10)
    @Max(value = 2000)
    @Column(name = "pages")
    private int pages;

//    @Lob
    @Column(name = "photo")
    private String photo;

    public Book() {
    }

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
