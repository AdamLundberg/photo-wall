package se.lexicon.adam.photowall.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.adam.photowall.repository.PersonRepository;
import se.lexicon.adam.photowall.service.PersonService;

import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonTest {

    PersonRepository personRepository;
    Person person;
    String personId;

    @Autowired
    public PersonTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @BeforeEach
    void setUp() {
        personId = "person1";

        person = new Person(personId, "Adam", "Lundberg", "a@l.com", new ArrayList<>());

    }

    @Test
    void addPicture() {
        Picture picture = new Picture("pic.com", "testPic");

        person.addPicture(picture);

        assertTrue(person.getPictures().contains(picture));
        assertTrue(person.getPictures().size() > 0);
    }

    @Test
    void removePicture() {
        Picture picture = new Picture("pic.com", "testPic");
        person.addPicture(picture);

        int pictureCount = person.getPictures().size();

        person.removePicture(picture);

        assertFalse(person.getPictures().contains(picture));
        assertNotEquals(person.getPictures().size(), pictureCount);
        assertEquals(0, person.getPictures().size());
    }

    @Test
    void getPersonId() {
        String id = person.getPersonId();

        assertEquals(personId, id);
    }

    @Test
    void changeFirstName() {
        String originalName = person.getFirstName();

        person.setFirstName("Robin");

        assertNotEquals(originalName, person.getFirstName());
        assertTrue(person.getFirstName().contains("Robin"));
    }

    @Test
    void changeLastName() {
        String originalName = person.getLastName();

        person.setLastName("Sandberg");

        assertNotEquals(originalName, person.getLastName());
        assertTrue(person.getLastName().contains("Sandberg"));
    }

    @Test
    void changeEmail() {
        String originalEmail = person.getEmail();

        person.setEmail("r@s.com");

        assertNotEquals(originalEmail, person.getEmail());
        assertTrue(person.getEmail().contains("r@s.com"));

    }

    @Test
    void testEquals() {
        Person newPerson = new Person("Robin", "Sandberg", "r@s.com");

        assertFalse(person.equals(newPerson));
    }

    @Test
    void testHashCode() {
        Person newPerson = new Person("Robin", "Sandberg", "r@s.com");

        int newPersonHash = newPerson.hashCode();
        int originalPersonHash = person.hashCode();

        assertNotEquals(originalPersonHash, newPersonHash);
    }

    @Test
    void testToString() {
        String toString = person.toString();

        assertTrue(toString.contains(person.getPersonId()));
        assertTrue(toString.contains(person.getFirstName()));
        assertTrue(toString.contains(person.getLastName()));
        assertTrue(toString.contains(person.getEmail()));
    }
}