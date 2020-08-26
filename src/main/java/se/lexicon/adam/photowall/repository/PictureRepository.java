package se.lexicon.adam.photowall.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends CrudRepository<Picture, String> {
    //List<Picture> findAll();
    //Optional<Picture> findByPictureId(String id);
    List<Picture> findByNameContainingIgnoreCase(String name);
    //Need to check if this is right
    List<Picture> findByPerson(Person person);
    List<Picture> findByPictureCategory(PictureCategory pictureCategory);
}
