package se.lexicon.adam.photowall.service;

import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();
    Optional<Person> findByPersonId(String id);
    List<Person> findByFirstName(String firstName);
    Optional<Person> findByPictures(Picture picture);

    Person create(Person person);
    Person update(Person person);

    void delete(Person person);
}
