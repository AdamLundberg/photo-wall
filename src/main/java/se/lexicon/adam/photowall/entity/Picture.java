package se.lexicon.adam.photowall.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pictureId;
    private String url;
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Person person;
    private LocalDate dateCreated;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Category category;

    public Picture(int pictureId, String url, String name, Person person, LocalDate dateCreated, Category category) {
        this(url, name, person, dateCreated, category);
        this.pictureId = pictureId;
    }

    public Picture(String url, String name, Person person, LocalDate dateCreated, Category category) {
        this.url = url;
        this.name = name;
        this.person = person;
        this.dateCreated = LocalDate.now();
        this.category = category;
    }

    public Picture() {
    }

    public int getPictureId() {
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

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
                Objects.equals(category, picture.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureId, url, name, person, dateCreated, category);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", person=" + person +
                ", dateCreated=" + dateCreated +
                ", category=" + category +
                '}';
    }
}
