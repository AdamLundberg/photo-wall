package se.lexicon.adam.photowall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.repository.PictureCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PictureCategoryServiceImpl implements PictureCategoryService{

    private final PictureCategoryRepository pictureCategoryRepository;

    @Autowired
    public PictureCategoryServiceImpl(PictureCategoryRepository pictureCategoryRepository) {
        this.pictureCategoryRepository = pictureCategoryRepository;
    }

    @Override
    public List<PictureCategory> findAll() {
        return pictureCategoryRepository.findAll();
    }

    @Override
    public Optional<PictureCategory> findByCategoryId(String id) {
        return pictureCategoryRepository.findByPictureCategoryId(id);
    }
}
