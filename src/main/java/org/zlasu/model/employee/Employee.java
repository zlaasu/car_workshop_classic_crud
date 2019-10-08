package org.zlasu.model.employee;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

@Getter
@Setter
public class Employee extends MainModel implements MainModelInterface {
    private int id;
    private String name;
    private String lastname;
    private String address;
    private String phone_number;
    private String note;
    private Double cost_per_hour;
}
