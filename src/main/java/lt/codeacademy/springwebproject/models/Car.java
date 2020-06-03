package lt.codeacademy.springwebproject.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @NotEmpty
    private String model;

    @Column(name = "year")
    @Min(1769)
    @Max(2020)
    private int year;

    @Column(name = "kw")
    @Min(1)
    private int kW;

    @Column(name = "review")
    @NotEmpty
    private String review;

    @Column(name = "img")
    private String img;
}
