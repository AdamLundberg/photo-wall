package se.lexicon.adam.photowall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Picture {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String pictureId;
    private String url;
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore
    private Person person;
    private LocalDate dateCreated;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore
    private PictureCategory pictureCategory;

    public Picture(String pictureId, String url, String name, Person person, PictureCategory pictureCategory) {
        this(pictureId, url, name);
        this.person = person;
        this.pictureCategory = pictureCategory;
    }

    public Picture(String pictureId, String url, String name) {
        this(url, name);
        this.pictureId = pictureId;
    }

    public Picture(String url, String name) {
        this.url = url;
        this.name = name;
        this.dateCreated = LocalDate.now();
    }

    public Picture() {
    }

    public String getPictureId() {
        return pictureId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    /*public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }*/

    public PictureCategory getPictureCategory() {
        return pictureCategory;
    }

    public void setPictureCategory(PictureCategory pictureCategory) {
        this.pictureCategory = pictureCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return pictureId == picture.pictureId &&
                Objects.equals(url, picture.url) &&
                Objects.equals(name, picture.name) &&
                Objects.equals(person, picture.person) &&
                Objects.equals(dateCreated, picture.dateCreated) &&
                Objects.equals(pictureCategory, picture.pictureCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureId, url, name, person, dateCreated, pictureCategory);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", person=" + person +
                ", dateCreated=" + dateCreated +
                ", category=" + pictureCategory +
                '}';
    }
}
