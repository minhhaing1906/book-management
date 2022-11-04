package com.example.book.controller;

import com.example.book.entities.Book;
import com.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model) {
        List<Book> all = bookService.findAll();
        model.addAttribute("all", all);
        return "book-list";
    }

    @GetMapping("/detail")
    public String displayBookDetail(Model model) {
        model.addAttribute("book", new Book());
        return "book-detail";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) throws IOException {
        System.out.println(book.getPhoto());

        if (bindingResult.hasErrors()){
            System.out.println("----------------Hit error---------------------");
            return "book-detail";
        }

        Book savedBook = bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String updateBook(Model model, @PathVariable int id) {
        Optional<Book> byId = bookService.findById(id);
        model.addAttribute("book", byId);
        return "book-detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
