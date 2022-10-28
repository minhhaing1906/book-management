package com.example.book.controller;

import com.example.book.entities.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class BookController {
    private BookRepository bookRepo;

    @Autowired
    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public String getBooks(Model model) {
        Iterable<Book> all = bookRepo.findAll();
        model.addAttribute("all", all);
        return "book-list";
    }

    @GetMapping("/detail")
    public String displayBookDetail(Model model) {
        model.addAttribute("book", new Book());
        return "book-detail";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRepo.save(book);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateBook(Model model, @PathVariable int id) {
        Optional<Book> byId = bookRepo.findById(id);
        model.addAttribute("book", byId);
        return "book-detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepo.deleteById(id);
        return "redirect:/";
    }
}
