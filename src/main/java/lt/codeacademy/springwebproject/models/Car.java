package lt.codeacademy.springwebproject.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    @NotEmpty
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "kw")
    private int kW;
}
