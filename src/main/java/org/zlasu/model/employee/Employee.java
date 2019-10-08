package org.zlasu.model.employee;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.util.crud.ModelInterface;
import org.zlasu.util.crud.ModelMain;

@Getter
@Setter
public class Employee extends ModelMain implements ModelInterface {
    private int id;
    private String name;
    private String lastname;
    private String address;
    private String phone_number;
    private String note;
    private Double cost_per_hour;
}
