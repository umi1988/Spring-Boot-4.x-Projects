package com.starttohkar.controller;

import java.util.List;
import java.util.Optional;

import com.starttohkar.model.Book;
import com.starttohkar.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Tag(name = "Book Service", description = "My Book Store")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping("/save")
    @Operation(summary = "store book api")
    public String saveBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    @Operation(summary = "search book api")
    @GetMapping("/searchBook/{bookId}")
    public Optional<Book> getBook(@PathVariable int bookId) {
        return service.getBook(bookId);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public List<Book> deleteBook(@PathVariable int bookId) {
        return service.removeBook(bookId);

    }
}
