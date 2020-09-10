package se.lexicon.adam.photowall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.repository.PersonRepository;
import se.lexicon.adam.photowall.repository.PictureCategoryRepository;
import se.lexicon.adam.photowall.repository.PictureRepository;

@Component
public class CommandLine implements CommandLineRunner {

    PictureCategoryRepository pictureCategoryRepository;
    PersonRepository personRepository;
    PictureRepository pictureRepository;

    @Autowired
    public CommandLine(PictureCategoryRepository pictureCategoryRepository, PersonRepository personRepository, PictureRepository pictureRepository) {
        this.pictureCategoryRepository = pictureCategoryRepository;
        this.personRepository = personRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        pictureCategoryRepository.save(new PictureCategory("Nature"));
        pictureCategoryRepository.save(new PictureCategory("Portrait"));
        pictureCategoryRepository.save(new PictureCategory("Cars"));
        pictureCategoryRepository.save(new PictureCategory("Dogs"));

        personRepository.save(new Person("Adam", "Lundberg", "a@l.com"));
        personRepository.save(new Person("Robin", "Sandberg", "r@s.com"));

        /*pictureRepository.save(new Picture( "https://images.unsplash.com/photo-1575425186775-b8de9a427e67?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1534&q=80", "Pug in hat"));
        pictureRepository.save(new Picture( "https://images.unsplash.com/photo-1541364983171-a8ba01e95cfc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1534&q=80", "Pug in glasses"));
*/
    }
}
