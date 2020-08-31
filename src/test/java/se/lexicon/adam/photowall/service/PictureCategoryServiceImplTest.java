package se.lexicon.adam.photowall.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.repository.PictureCategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PictureCategoryServiceImplTest {

    @TestConfiguration
    static class PictureCategoryServiceImplTestContextConfiguration {

        @Bean
        public PictureCategoryService pictureCategoryService() {
            return new PictureCategoryServiceImpl();
        }
    }

    @Autowired
    private PictureCategoryService pictureCategoryService;

    @MockBean
    private PictureCategoryRepository pictureCategoryRepository;

    @BeforeEach
    void setUp() {

        Optional<PictureCategory> test = Optional.of(new PictureCategory("category1", "Test"));

        Mockito.when(pictureCategoryRepository.findById(test.get().getPictureCategoryId()))
                .thenReturn(test);

        //Mockito.when(pictureCategoryRepository.)
    }

    @Test
    void findAll() {

    }

    @Test
    void findByCategoryId() {
        String id = "category1";

        Optional<PictureCategory> categoryFound = pictureCategoryService.findByCategoryId(id);
        assertEquals(categoryFound.get().getPictureCategoryId(), id);
    }

    @Test
    void update() {
    }
}