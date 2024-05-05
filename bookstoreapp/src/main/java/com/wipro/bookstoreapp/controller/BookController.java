package com.wipro.bookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bookstoreapp.entity.Book;
import com.wipro.bookstoreapp.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/save")
	public ResponseEntity<Book> saveBook(@RequestBody Book book){
		bookService.addBook(book);
		ResponseEntity<Book> responseEntity = new ResponseEntity<>(book,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Book> getBookDetails (@PathVariable("id") int bookId) {
		 Book book = bookService.getBookById(bookId);		 		
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Book> fetchAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return books;
	}
	
	@PutMapping("/update")
	public ResponseEntity<Book> editBook(@RequestBody Book book) {
		bookService.updateBookDetails(book);
		ResponseEntity<Book> responseEntity = new ResponseEntity<>(book,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int bookId) {
		bookService.deleteBook( bookId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
}