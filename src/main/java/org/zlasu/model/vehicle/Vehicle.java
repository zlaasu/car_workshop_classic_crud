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
    private int customerId;
    private String model;
    private String brand;
    private int yearOfProduction;
    private String registrationNumber;
    private Date nextTechnicalInspection;
}
