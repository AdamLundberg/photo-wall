package se.lexicon.adam.photowall.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.adam.photowall.entity.PictureCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<PictureCategory, String> {

    List<PictureCategory> findAll();
    Optional<PictureCategory> findByCategoryId(String id);
    // Do i need findByCategoryName??
    PictureCategory findByCategoryName(String name);

}
