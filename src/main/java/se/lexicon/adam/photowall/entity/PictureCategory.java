package se.lexicon.adam.photowall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class PictureCategory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String pictureCategoryId;

    @Column(unique = true)
    private String pictureCategoryName;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Picture> pictures;

    public PictureCategory(String pictureCategoryId, String pictureCategoryName, List<Picture> pictures) {
        this(pictureCategoryId, pictureCategoryName);
        this.pictures = pictures;
    }

    public PictureCategory(String pictureCategoryId, String pictureCategoryName) {
        this(pictureCategoryName);
        this.pictureCategoryId = pictureCategoryId;
    }

    public PictureCategory(String pictureCategoryName) {
        this.pictureCategoryName = pictureCategoryName;
    }

    public PictureCategory() {
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
        picture.setPictureCategory(this);
    }

    public void removePicture(Picture picture) {
        picture.setPictureCategory(null);
        pictures.remove(picture);
    }

    public String getPictureCategoryId() {
        return pictureCategoryId;
    }

    public String getPictureCategoryName() {
        return pictureCategoryName;
    }

    public void setPictureCategoryName(String categoryName) {
        this.pictureCategoryName = categoryName;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    /*public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureCategory pictureCategory = (PictureCategory) o;
        return Objects.equals(pictureCategoryId, pictureCategory.pictureCategoryId) &&
                Objects.equals(pictureCategoryName, pictureCategory.pictureCategoryName) &&
                Objects.equals(pictures, pictureCategory.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureCategoryId, pictureCategoryName, pictures);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + pictureCategoryId + '\'' +
                ", categoryName='" + pictureCategoryName + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
