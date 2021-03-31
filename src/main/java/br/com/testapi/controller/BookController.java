package br.com.testapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.testapi.vo.BookVO;

import br.com.testapi.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Annotation used for customize tags titles in the swagger page 
@Api(value = "Book EndPoint Details", description = "Description for books", tags = {"Book EndPoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookServices service;
	
	//Annotation used for customize endpoints in the swagger page 
	@ApiOperation(value = "Find all books recorded")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BookVO> findAll() {
		List<BookVO> books = service.findAll();
		books.stream()
		.forEach(p -> p.add(linkTo(methodOn(PersonController.class)
				.findById(p.getKey()))
				.withSelfRel()));
		return books;
	}	
	
	@ApiOperation(value = "Find books by id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = service.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class)
				.findById(id)).withSelfRel());
		return bookVO;
	}	
	
	@ApiOperation(value = "Create books")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			     consumes = {"application/json", "application/xml","application/x-yaml"})
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = service.create(book);
		bookVO.add(linkTo(methodOn(BookController.class)
				.findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Update Information about any book")
	@PutMapping(produces = {"application/json", "application/xml","application/x-yaml"},
		        consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = service.update(book);
		bookVO.add(linkTo(methodOn(BookController.class)
				.findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}	
	
	@ApiOperation(value = "Delete a book")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}