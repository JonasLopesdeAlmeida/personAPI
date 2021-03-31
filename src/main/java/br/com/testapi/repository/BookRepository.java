package br.com.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testapi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}