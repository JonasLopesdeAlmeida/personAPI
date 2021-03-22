package br.com.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}