package se.lexicon.adam.photowall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public Optional<Person> findByPersonId(String id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        return personRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    public Optional<Person> findByPictures(Picture picture) {
        return personRepository.findByPictures(picture);
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        if (findByPersonId(person.getPersonId()).isPresent()) {
            return personRepository.save(person);
        }
        return null;
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
