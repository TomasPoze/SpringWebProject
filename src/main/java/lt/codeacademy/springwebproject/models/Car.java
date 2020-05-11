package lt.codeacademy.springwebproject.models;

import lombok.Data;

@Data
public class Car {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private int kW;
}
