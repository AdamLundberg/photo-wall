package se.lexicon.adam.photowall.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.adam.photowall.repository.PictureRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PictureTest {

    Picture picture;
    String pictureId;
    Person person;
    PictureCategory pictureCategory;

    @BeforeEach
    void setUp() {
        pictureId = "picture1";

        picture = new Picture(pictureId, "test.com", "Test Picture");

        person = new Person("person1", "Adam", "Lundberg", "a@l.com", new ArrayList<>());

        person.addPicture(picture);

        pictureCategory = new PictureCategory("category1", "TestCategory", new ArrayList<>());

        pictureCategory.addPicture(picture);
    }

    @Test
    void getPictureId() {
        String id = picture.getPictureId();

        assertEquals(pictureId, id);
    }

    @Test
    void changeUrl() {
        String originalUrl = picture.getUrl();

        picture.setUrl("new.com");

        assertNotEquals(originalUrl, picture.getUrl());
        assertTrue(picture.getUrl().contains("new.com"));
    }

    @Test
    void changeName() {
        String originalName = picture.getName();

        picture.setName("New name");

        assertNotEquals(originalName, picture.getName());
        assertTrue(picture.getName().contains("New name"));
    }

    @Test
    void getPerson() {
        Person foundPerson = picture.getPerson();

        assertEquals(person, foundPerson);
        assertNotNull(picture.getPerson());
        assertTrue(picture.getPerson().getFirstName().contains("Adam"));
    }

    @Test
    void getDateCreated() {
        assertNotNull(picture.getDateCreated());
    }

    @Test
    void getPictureCategory() {
        PictureCategory foundPictureCategory = picture.getPictureCategory();

        assertEquals(pictureCategory, foundPictureCategory);
        assertNotNull(picture.getPictureCategory());
        assertTrue(picture.getPictureCategory().getPictureCategoryName().contains("TestCategory"));
    }

    @Test
    void testEquals() {
        Picture newPicture = new Picture("newUrl.com", "New picture");

        assertFalse(picture.equals(newPicture));
    }

    @Test
    void testHashCode() {
        Picture newPicture = new Picture("newUrl.com", "New picture");

        int newPictureHash = newPicture.hashCode();
        int originalPictureHash = picture.hashCode();

        assertNotEquals(originalPictureHash, newPictureHash);
    }

    @Test
    void testToString() {
        String toString = picture.toString();

        assertTrue(toString.contains(picture.getPictureId()));
        assertTrue(toString.contains(picture.getUrl()));
        assertTrue(toString.contains(picture.getName()));

    }
}