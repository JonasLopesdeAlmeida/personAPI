package br.com.testapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.testapi.converter.DozerConverter;
import com.br.testapi.vo.BookVO;

import br.com.testapi.exception.ResourceNotFoundException;
import br.com.testapi.model.Book;
import br.com.testapi.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository repository;

	public BookVO create(BookVO book) {
		// CAN BE USED VAR INSTED OF PERSON AN PERSONVO AS WELL.
		var entityBook = DozerConverter.parseObject(book, Book.class);
		// CONVERTING PERSON TO PERSONVO
		var bookVO = DozerConverter.parseObject(repository.save(entityBook), BookVO.class);
		return bookVO;
	}

	public List<BookVO> findAll() {
		// AFTER GOING TO THE DATABASE WITH repository.findAll () TAKE A LIST OF PERSONS
		// ENTITY AND DOZER CONVERT TO VO AND RETURN
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}

	public BookVO findById(Long id) {

		var personEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Books found for this ID"));

		return DozerConverter.parseObject(personEntity, BookVO.class);
	}

	public BookVO update(BookVO book) {
		var entityBook = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No Books found for this ID"));

		entityBook.setAuthor(book.getAuthor());
		entityBook.setLaunchDate(book.getLaunchDate());
		entityBook.setPrice(book.getPrice());
		entityBook.setTitle(book.getTitle());

		var personVO = DozerConverter.parseObject(repository.save(entityBook), BookVO.class);
		return personVO;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Books found for this ID"));
		repository.delete(entity);
	}

}
