package Hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = "actors")
@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "actors")
public class Movie extends BaseEntity {

    private String title;

    @Column(name = "yearOfRelease")
    private int yearOfRelease;

@ManyToOne
private Genre genre;

@ManyToMany(mappedBy = "movies") // cascade = CascadeType.ALL cascade wszystko co wykona sie na movie to ten na aktorze link
private List<Actor> actors = new ArrayList<>();

}
