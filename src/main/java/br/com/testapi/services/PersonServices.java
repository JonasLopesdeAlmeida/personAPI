package br.com.testapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.testapi.converter.DozerConverter;
import com.br.testapi.vo.PersonVO;

import br.com.testapi.exception.ResourceNotFoundException;
import br.com.testapi.model.Person;
import br.com.testapi.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	public PersonVO create(PersonVO person) {
		// CAN BE USED VAR INSTED OF PERSON AN PERSONVO AS WELL.
		var entityPerson = DozerConverter.parseObject(person, Person.class);
		// CONVERTING PERSON TO PERSONVO
		var personVO = DozerConverter.parseObject(repository.save(entityPerson), PersonVO.class);
		return personVO;
	}

	public List<PersonVO> findAll() {
		// AFTER GOING TO THE DATABASE WITH repository.findAll () TAKE A LIST OF PERSONS
		// ENTITY AND DOZER CONVERT TO VO AND RETURN
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {

		var personEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		return DozerConverter.parseObject(personEntity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		var entityPerson = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entityPerson.setFirstName(person.getFirstName());
		entityPerson.setLastName(person.getLastName());
		entityPerson.setAddress(person.getAddress());
		entityPerson.setGender(person.getGender());

		var personVO = DozerConverter.parseObject(repository.save(entityPerson), PersonVO.class);
		return personVO;
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
