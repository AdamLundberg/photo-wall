package se.lexicon.adam.photowall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.repository.PictureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService{

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> findAllByOrOrderByDateCreatedDesc() {
        return pictureRepository.findAllByOrderByLocalDateCreatedDesc();
    }

    @Override
    public Optional<Picture> findByPictureId(String id) {
        return pictureRepository.findById(id);
    }

    @Override
    public List<Picture> findByName(String name) {
        return pictureRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Picture> findByPerson(Person person) {
        return pictureRepository.findByPerson(person);
    }

    @Override
    public List<Picture> findByPictureCategory(PictureCategory pictureCategory) {
        return pictureRepository.findByPictureCategory(pictureCategory);
    }

    @Override
    public Picture create(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture update(Picture picture) {
        if (findByPictureId(picture.getPictureId()).isPresent()) {
            return pictureRepository.save(picture);
        }
        return null;
    }

    @Override
    public void delete(Picture picture) {
        pictureRepository.delete(picture);
    }
}
