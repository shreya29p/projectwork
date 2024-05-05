package com.wipro.bookstoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bookstoreapp.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

}
