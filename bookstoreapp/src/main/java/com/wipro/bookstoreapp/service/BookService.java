package com.wipro.bookstoreapp.service;

import java.util.List;

import com.wipro.bookstoreapp.entity.Book;

public interface BookService {
	
	List<Book> getAllBooks();
    Book getBookById(int bookId);
    Book addBook(Book book);
    Book updateBookDetails(Book book);
    void deleteBook(int bookId);

}
