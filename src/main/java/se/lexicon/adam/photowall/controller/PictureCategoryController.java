package se.lexicon.adam.photowall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.adam.photowall.entity.Picture;
import se.lexicon.adam.photowall.entity.PictureCategory;
import se.lexicon.adam.photowall.service.PictureCategoryService;
import se.lexicon.adam.photowall.service.PictureService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/categories")
public class PictureCategoryController {

    private final PictureCategoryService pictureCategoryService;
    private final PictureService pictureService;

    @Autowired
    public PictureCategoryController(PictureCategoryService pictureCategoryService, PictureService pictureService) {
        this.pictureCategoryService = pictureCategoryService;
        this.pictureService = pictureService;
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

    @GetMapping("/picture/{picture}")
    public ResponseEntity<PictureCategory> findByPicture(@PathVariable("picture") Picture picture) {
        Optional<PictureCategory> optional = pictureCategoryService.findByPictures(picture);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<PictureCategory> addPicture(@RequestBody Picture picture, @PathVariable("categoryId") String categoryId) {

        System.out.println(categoryId);
        System.out.println(picture.toString());

        PictureCategory updatedCategory = pictureCategoryService.findByCategoryId(categoryId).get();
        Picture originalPicture = pictureService.findByPictureId(picture.getPictureId()).get();

        /*if (originalPicture.getPictureCategory()!= null) {
            PictureCategory originalCategory = pictureCategoryService.findByCategoryId(originalPicture.getPictureCategory().getPictureCategoryId()).get();
            updatedCategory.removePicture(originalPicture);
        }*/

        updatedCategory.addPicture(originalPicture);

        updatedCategory = pictureCategoryService.update(updatedCategory);
        pictureService.update(originalPicture);

        return ResponseEntity.ok(updatedCategory);
    }
}
