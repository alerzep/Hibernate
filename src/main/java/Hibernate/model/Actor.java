package Hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = "movies")
@ToString(exclude = "movies")
@Entity
@Table(name = "actors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor extends BaseEntity {

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;


    @ManyToMany
    @JoinTable(name = "actors_to_movies",
    joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Movie> movies = new ArrayList<>();

    @OneToOne
    private Address address;
    // TODO OneToOne address
}
