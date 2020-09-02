package se.lexicon.adam.photowall.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.entity.PictureCategory;

import java.util.Optional;

public interface PictureCategoryRepository extends CrudRepository<PictureCategory, String> {

    Optional<PictureCategory> findByPicture(Picture picture);
}
