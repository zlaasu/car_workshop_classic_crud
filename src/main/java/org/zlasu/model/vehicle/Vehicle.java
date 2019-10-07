package org.zlasu.model.vehicle;

import org.zlasu.model.MainModel;
import org.zlasu.model.customer.Customer;

import java.util.Date;

public class Vehicle extends MainModel {
    private int id;
    private Customer customer;
    private String model;
    private String brand;
    private Date year_of_production;
    private String registration_number;
    private Date next_technical_inspection;
}
