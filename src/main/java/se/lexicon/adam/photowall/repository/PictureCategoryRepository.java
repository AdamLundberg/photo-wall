package se.lexicon.adam.photowall.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.adam.photowall.entity.PictureCategory;

public interface PictureCategoryRepository extends CrudRepository<PictureCategory, String> {
}
