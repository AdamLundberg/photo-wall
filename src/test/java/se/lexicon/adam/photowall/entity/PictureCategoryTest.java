package se.lexicon.adam.photowall.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.adam.photowall.repository.PictureCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PictureCategoryTest {

    PictureCategory pictureCategory;
    String categoryId;


    @BeforeEach
    void setUp() {
        categoryId = "category1";

        pictureCategory = new PictureCategory(categoryId, "TestCategory", new ArrayList<>());
    }

    @Test
    void addPicture() {
        Picture picture = new Picture("pic.com", "testPic");

        pictureCategory.addPicture(picture);

        assertTrue(pictureCategory.getPictures().contains(picture));
        assertTrue(pictureCategory.getPictures().size() > 0);
    }

    @Test
    void removePicture() {
        Picture picture = new Picture("pic.com", "testPic");
        pictureCategory.addPicture(picture);

        int pictureCount = pictureCategory.getPictures().size();

        pictureCategory.removePicture(picture);

        assertFalse(pictureCategory.getPictures().contains(picture));
        assertNotEquals(pictureCategory.getPictures().size(), pictureCount);
        assertEquals(0, pictureCategory.getPictures().size());
    }

    @Test
    void getPictureCategoryId() {
        String id = pictureCategory.getPictureCategoryId();

        assertEquals(categoryId, id);
    }

    @Test
    void changePictureCategoryName() {
        String originalName = pictureCategory.getPictureCategoryName();

        pictureCategory.setPictureCategoryName("Nature");

        assertNotEquals(originalName, pictureCategory.getPictureCategoryName());
        assertTrue(pictureCategory.getPictureCategoryName().contains("Nature"));
    }

    @Test
    void testEquals() {
        PictureCategory newCategory = new PictureCategory("Portrait");

        assertFalse(pictureCategory.equals(newCategory));
    }

    @Test
    void testHashCode() {
        PictureCategory newCategory = new PictureCategory("Portrait");

        int newCategoryHash = newCategory.hashCode();
        int originalCategoryHash = pictureCategory.hashCode();

        assertNotEquals(originalCategoryHash, newCategoryHash);
    }

    @Test
    void testToString() {
        String toString = pictureCategory.toString();

        assertTrue(toString.contains(pictureCategory.getPictureCategoryId()));
        assertTrue(toString.contains(pictureCategory.getPictureCategoryName()));
    }
}