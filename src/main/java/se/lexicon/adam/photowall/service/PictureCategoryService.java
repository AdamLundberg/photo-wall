package se.lexicon.adam.photowall.service;

import se.lexicon.adam.photowall.entity.PictureCategory;

import java.util.List;
import java.util.Optional;

public interface PictureCategoryService {

    List<PictureCategory> findAll();
    Optional<PictureCategory> findByCategoryId(String id);

    PictureCategory update(PictureCategory pictureCategory);
}
