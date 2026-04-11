package com.Buchverwaltung.service;

import com.Buchverwaltung.entity.Book;
import com.Buchverwaltung.exception.ResourceNotFoundException;
import com.Buchverwaltung.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {

        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No book was found with the id " + id));
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No book was found with the id " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());

        return bookRepository.save(existingBook);

    }
}