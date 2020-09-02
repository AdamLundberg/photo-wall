package se.lexicon.adam.photowall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.adam.photowall.entity.Person;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.service.PersonService;
import se.lexicon.adam.photowall.service.PictureService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/persons")
public class PersonController {

    private final PersonService personService;
    private final PictureService pictureService;

    @Autowired
    public PersonController(PersonService personService, PictureService pictureService) {
        this.personService = personService;
        this.pictureService = pictureService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") String id) {
        Optional<Person> optional = personService.findByPersonId(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //return ResponseEntity.ok().body(personService.findByPersonId(id));
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Person>> findByFirstName(@PathVariable("firstName") String firstName) {
        return ResponseEntity.ok().body(personService.findByFirstName(firstName));
    }

    @GetMapping("/pic/{picture}")
    public ResponseEntity<Person> findByPicture(@PathVariable("picture") Picture picture) {
        Optional<Person> optional = personService.findByPictures(picture);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        //return ResponseEntity.ok().body(personService.findByPictures(picture));
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PutMapping("pic/{personId}")
    public ResponseEntity<Person> addPicture(@RequestBody Picture picture, @PathVariable("personId") String personId) {
        Person original = personService.findByPersonId(personId).get();
        original.addPicture(picture);

        pictureService.update(picture);
        original = personService.update(original);

        return ResponseEntity.ok(original);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@RequestBody Person updated, @PathVariable("id") String id) {

        if (!updated.getPersonId().equals(id)) return ResponseEntity.notFound().build();

        Person original = personService.findByPersonId(id).get();
        original.setFirstName(updated.getFirstName());
        original.setLastName(updated.getLastName());
        original.setEmail(updated.getEmail());

        original = personService.update(original);

        return ResponseEntity.ok(original);
    }

}
