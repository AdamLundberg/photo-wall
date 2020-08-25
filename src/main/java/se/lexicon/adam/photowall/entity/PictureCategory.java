package se.lexicon.adam.photowall.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class PictureCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categoryId;

    @Column(unique = true)
    private String categoryName;

    @OneToMany
    private List<Picture> pictures;

    public PictureCategory(String categoryId, String categoryName) {
        this(categoryName);
        this.categoryId = categoryId;
    }

    public PictureCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public PictureCategory() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureCategory pictureCategory = (PictureCategory) o;
        return Objects.equals(categoryId, pictureCategory.categoryId) &&
                Objects.equals(categoryName, pictureCategory.categoryName) &&
                Objects.equals(pictures, pictureCategory.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, pictures);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
