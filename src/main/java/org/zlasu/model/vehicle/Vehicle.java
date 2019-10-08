package org.zlasu.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

import java.util.Date;

@Getter
@Setter
public class Vehicle extends MainModel implements MainModelInterface {
    private int id;
    private int customer_id;
    private String model;
    private String brand;
    private int year_of_production;
    private String registration_number;
    private Date next_technical_inspection;
}
