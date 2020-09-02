package se.lexicon.adam.photowall.service;

import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.entity.PictureCategory;

import java.util.List;
import java.util.Optional;

public interface PictureCategoryService {

    List<PictureCategory> findAll();
    Optional<PictureCategory> findByCategoryId(String id);

    Optional<PictureCategory> findByPicture(Picture picture);
    PictureCategory update(PictureCategory pictureCategory);
}
