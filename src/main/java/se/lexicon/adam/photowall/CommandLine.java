package se.lexicon.adam.photowall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.repository.PictureCategoryRepository;

@Component
public class CommandLine implements CommandLineRunner {

    PictureCategoryRepository pictureCategoryRepository;

    @Autowired
    public CommandLine(PictureCategoryRepository pictureCategoryRepository) {
        this.pictureCategoryRepository = pictureCategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        pictureCategoryRepository.save(new PictureCategory("Nature"));
        pictureCategoryRepository.save(new PictureCategory("Portrait"));
        pictureCategoryRepository.save(new PictureCategory("Cars"));

    }
}
