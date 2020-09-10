package se.lexicon.adam.photowall.service;

import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.entity.PictureCategory;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    List<Picture> findAllByOrOrderByDateCreatedDesc();
    Optional<Picture> findByPictureId(String id);
    List<Picture> findByName(String name);
    List<Picture> findByPerson(Person person);
    List<Picture> findByPictureCategory(PictureCategory pictureCategory);

    Picture create(Picture picture);
    Picture update(Picture picture);

    void delete(Picture picture);
}
