package se.lexicon.adam.photowall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.service.PersonService;
import se.lexicon.adam.photowall.service.PictureService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/pictures")
public class PictureController {

    private final PictureService pictureService;
    private final PersonService personService;

    @Autowired
    public PictureController(PictureService pictureService, PersonService personService) {
        this.pictureService = pictureService;
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Picture>> findAll() {
        return ResponseEntity.ok().body(pictureService.findAllByOrOrderByDateCreatedDesc());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> findById(@PathVariable("id") String id) {
        Optional<Picture> optional = pictureService.findByPictureId(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<Picture>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok().body(pictureService.findByName(name));
    }

    @GetMapping("person/{person}")
    public ResponseEntity<List<Picture>> findByPerson(@PathVariable("person") Person person) {
        return ResponseEntity.ok().body(pictureService.findByPerson(person));
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Picture>> findByCategory(@PathVariable("category") PictureCategory pictureCategory) {
        return ResponseEntity.ok().body(pictureService.findByPictureCategory(pictureCategory));
    }

    @PostMapping
    public ResponseEntity<Picture> create(@RequestBody Picture picture) {
        System.out.println(picture.toString());
        System.out.println(pictureService.create(picture).toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(pictureService.create(picture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Picture> update(@RequestBody Picture updated, @PathVariable("id") String id) {

        if (!updated.getPictureId().equals(id)) return ResponseEntity.notFound().build();

        Picture original = pictureService.findByPictureId(id).get();
        original.setUrl(updated.getUrl());
        original.setName(updated.getName());

        original = pictureService.update(original);

        return ResponseEntity.ok(original);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable("id") String pictureId) {
        Picture picture = pictureService.findByPictureId(pictureId).get();

        //Person person = personService.findByPictures(picture).get();

        pictureService.delete(picture);

    return ResponseEntity.noContent().build();
    }
}
