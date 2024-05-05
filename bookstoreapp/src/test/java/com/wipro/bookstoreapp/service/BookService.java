package com.wipro.bookstoreapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.bookstoreapp.entity.Author;
import com.wipro.bookstoreapp.entity.Book;
import com.wipro.bookstoreapp.repository.BookRepository;

@SpringBootTest
public class BookService {

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepository bookRepository;
	
	@Test
	public void testGetAllBook() {
		Book book = new Book();
		Author author = new Author();
		book.setBookId(1);
		book.setBookTiltle("Frog and Princess");
		book.setPrice(250);
		book.setYearOfPublish(LocalDate.now());

		author.setAuthorId(1);
		author.setAuthorName("Denail Joy");
		author.setEmail("denny@gmail.com");


		Book book1 = new Book();
		Author author1 = new Author();

		book1.setBookId(2);
		book1.setBookTiltle("The Hidden Hindus");
		book1.setPrice(700);
		book1.setYearOfPublish(LocalDate.now());

		author1.setAuthorId(2);
		author1.setAuthorName("Akash Gupta");
		author1.setEmail("akash@nmail.com");

		Book book2 = new Book();
		Author author2 = new Author();

		book2.setBookId(3);
		book2.setBookTiltle("Wings of Fire");
		book2.setPrice(500);
		book2.setYearOfPublish(LocalDate.now());

		author2.setAuthorId(3);
		author2.setAuthorName("Abdul Kalam");
		author2.setEmail("apj@nmail.com");

		List<Book> books = new ArrayList<>();
		List<Author> authors = new ArrayList<>();
		books.add(book);
		authors.add(author);
		books.add(book1);
		authors.add(author1);
		books.add(book2);
		authors.add(author2);
		
	    when(bookRepository.findAll()).thenReturn(books);
		
		List<Book> bookList = bookService.getAllBooks();
		assertEquals(3,bookList.size());
		
	}
	
	@Test
	public void testGetBookById() {
		Book book = new Book();
		Author author = new Author();
		book.setBookId(1);
		book.setBookTiltle("Frog and Princess");
		book.setPrice(250);
		book.setYearOfPublish(LocalDate.now());

		author.setAuthorId(1);
		author.setAuthorName("Denail Joy");
		author.setEmail("denny@gmail.com");

		when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		
		
		/*
		 * Book actualObj = bookService.getBookById(1);
		 * 
		 * assertEquals("Frog and Princess",actualObj.getBookTiltle());
		 */
		
	}

	@Test
	public void testAddBook() {
		Book book = new Book();
		Author author = new Author();
		book.setBookId(1);
		book.setBookTiltle("Frog and Princess");
		book.setPrice(250);
		book.setYearOfPublish(LocalDate.now());

		author.setAuthorId(1);
		author.setAuthorName("Denail Joy");
		author.setEmail("denny@gmail.com");

		when(bookRepository.save(book)).thenReturn(book);
		Book savebook = bookService.addBook(book);
		verify(bookRepository, times(1)).save(book);
		assertEquals(book.getBookId(), book.getBookId());
		assertNotNull(savebook);
		
	}


	@Test
	public void testUpdateBookDetails() {

		Book book = new Book();
		Author author = new Author();
		book.setBookId(1);
		book.setBookTiltle("Frog and Princess");
		book.setPrice(250);
		book.setYearOfPublish(LocalDate.now());

		author.setAuthorId(1);
		author.setAuthorName("Denail Joy");
		author.setEmail("denny@gmail.com");

		when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		when(bookRepository.save(book)).thenReturn(book);

		book.setBookId(1);
		book.setBookTiltle("Frog and Princess");
		book.setPrice(220);
		book.setYearOfPublish(LocalDate.now());

		author.setAuthorId(1);
		author.setAuthorName("Denail Joy");
		author.setEmail("denny@gmail.com");
		
		Book updateBookDetails = bookService.updateBookDetails(book);
		verify(bookRepository, times(1)).findById(1);
		verify(bookRepository, times(1)).save(book);

		assertEquals(book, updateBookDetails);

		assertEquals(220, updateBookDetails.getPrice());
	}

	@Test
	public void testDeleteBooks() {
		Book book = new Book();
		Author author = new Author();

		book.setBookId(1);
		book.setBookTiltle("Frog and Princess");
		book.setPrice(220);
		book.setYearOfPublish(LocalDate.now());

		author.setAuthorId(1);
		author.setAuthorName("Denail Joy");
		author.setEmail("denny@gmail.com");
		
		when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		doNothing().when(bookRepository).delete(book);
		
		bookService.deleteBook(1);
		
		verify(bookRepository,times(1)).findById(1);
		verify(bookRepository,times(1)).delete(book);
	}

}
