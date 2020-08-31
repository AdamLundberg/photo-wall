package se.lexicon.adam.photowall.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.repository.PictureCategoryRepository;
import se.lexicon.adam.photowall.repository.PictureRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PictureCategoryControllerTest {

    PictureCategoryController pictureCategoryController;
    PictureCategoryRepository pictureCategoryRepository;
    PictureCategory pictureCategory;
    String categoryId;

    @Autowired
    public PictureCategoryControllerTest(PictureCategoryController pictureCategoryController, PictureCategoryRepository pictureCategoryRepository) {
        this.pictureCategoryController = pictureCategoryController;
        this.pictureCategoryRepository = pictureCategoryRepository;
    }

    @BeforeEach
    void setUp() {
        categoryId = "category1";

        pictureCategory = new PictureCategory(categoryId, "TestCategory");

        pictureCategoryRepository.save(pictureCategory);

    }

    @Test
    void findAll() {
        List<PictureCategory> pictureCategoryList = (List<PictureCategory>) pictureCategoryController.findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void addPicture() {
    }
}