package Hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {


    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private int houseNumber;

    private String city;

    @Column(name = "post_code")
    private int postCode;

}
// // Utworzyc Address
// (city, streetName, houseNumber, postCode) - osobna tabela "adresses"
