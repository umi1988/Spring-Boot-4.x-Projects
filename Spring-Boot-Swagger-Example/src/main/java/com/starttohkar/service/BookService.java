package com.starttohkar.service;

import java.util.List;
import java.util.Optional;

import com.starttohkar.dao.BookRepository;
import com.starttohkar.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public String saveBook(Book book) {
        repository.save(book);
        return "book save dwith id " + book.getBookId();
    }

    public Optional<Book> getBook(int bookId) {
        return repository.findById(bookId);
    }

    public List<Book> removeBook(int bookId) {
        repository.deleteById(bookId);
        return repository.findAll();
    }
}