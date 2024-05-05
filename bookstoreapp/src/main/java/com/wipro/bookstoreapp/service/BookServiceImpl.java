package com.wipro.bookstoreapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bookstoreapp.entity.Book;
import com.wipro.bookstoreapp.exception.ResourceNotFoundException;
import com.wipro.bookstoreapp.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;


	@Override
	public List<Book> getAllBooks() {
		List<Book> listBook = bookRepository.findAll();
		return listBook;

	}

	@Override
	public Book getBookById(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isEmpty()) {
			throw new  ResourceNotFoundException("Book not existing with id: "+bookId);

		}
		Book book = optionalBook.get();
		return book;
	}


	@Override
	public Book addBook(Book book) {
		bookRepository.save(book);
		return book;

	}

	@Override
	public Book updateBookDetails(Book book) {
		Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
		if(optionalBook.isEmpty()) {
	throw new  ResourceNotFoundException("Book not existing with id: "+book.getBookId());

		}
		bookRepository.save(book);
		return book;
	}


	@Override
	public void deleteBook(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isEmpty()) {
	throw new  ResourceNotFoundException("Book not existing with id: "+bookId);

		}		
		Book book = optionalBook.get();		
		bookRepository.delete(book);	
		
	}
	
}