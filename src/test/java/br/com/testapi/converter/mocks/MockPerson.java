package br.com.testapi.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import com.br.testapi.vo.PersonVO;

import br.com.testapi.model.Person;

public class MockPerson {

	public Person mockEntity() {
		//generating only one mock because of position 0
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		//generating only one mock because of position 0
		return mockVO(0);
	}
	
	
	public List<Person> mockEntityList(){
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
			
		}
		return persons;
	}
	
	
	public List<PersonVO> mockVOList(){
		List<PersonVO> persons = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockVO(i));
			
		}
		return persons;
	}
	
	
	
	private Person mockEntity(Integer number) {
		
		Person person = new Person();
		person.setAddress("address test" + number);
		person.setFirstName("first name test" + number);
		person.setGender(((number % 2)==0) ? "male" : "famale");
		person.setLastName("last name test" + number);
		
		return person;
	
	}
	
    
	private PersonVO mockVO(Integer number) {
		
		PersonVO person = new PersonVO();
		person.setAddress("address test" + number);
		person.setFirstName("first name test" + number);
		person.setGender(((number % 2)==0) ? "male" : "famale");
		person.setLastName("last name test" + number);
		
		return person;
	
	}
	
	
	
	
}
