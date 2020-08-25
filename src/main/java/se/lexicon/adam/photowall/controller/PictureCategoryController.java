package se.lexicon.adam.photowall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.service.PictureCategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/categories")
public class PictureCategoryController {

    private final PictureCategoryService pictureCategoryService;

    @Autowired
    public PictureCategoryController(PictureCategoryService pictureCategoryService) {
        this.pictureCategoryService = pictureCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<PictureCategory>> findAll() {
        return ResponseEntity.ok().body(pictureCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureCategory> findById(@PathVariable("id") String id) {
        Optional<PictureCategory> optional = pictureCategoryService.findByCategoryId(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


        //return ResponseEntity.ok().body(pictureCategoryService.findByCategoryId(id));
    }
}
