package org.zlasu.model.order;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.customer.Customer;
import org.zlasu.model.status.Status;
import org.zlasu.model.vehicle.Vehicle;
import org.zlasu.util.crud.ModelInterface;
import org.zlasu.util.crud.ModelMain;

import java.util.Date;

@Getter
@Setter
public class Order extends ModelMain implements ModelInterface {

    private int id;
    private Customer customer;
    private Vehicle vehicle;
    private Status status;
    private Date date_order_accepted;
    private Date date_repair_start;
    private String problem_description;
    private String repair_description;
    private Double cost_repair;
    private Double cost_parts;
    private Double cost_per_hour;
    private int number_of_man_hours;

}
