package org.zlasu.model.order;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

import java.util.Date;

@Getter
@Setter
public class Order extends MainModel implements MainModelInterface {

    private int id;
    private int customer_id;
    private int vehicle_id;
    private int status_id;
    private Date date_order_accepted;
    private Date date_repair_start;
    private String problem_description;
    private String repair_description;
    private Double cost_repair;
    private Double cost_parts;
    private Double cost_per_hour;
    private int number_of_man_hours;

}
