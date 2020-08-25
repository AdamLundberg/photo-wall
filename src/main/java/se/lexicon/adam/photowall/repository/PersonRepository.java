package se.lexicon.adam.photowall.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
    List<Person> findAll();
    Optional<Person> findByPersonId(int id);

    //Only searches the firstname for now. Maybe need to use query to search both first and lastname with one input
    List<Person> findByFirstNameContains(String firstName);

    Optional<Person> findByPictures(Picture picture);

}
