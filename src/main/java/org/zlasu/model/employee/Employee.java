package org.zlasu.model.employee;

import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

@Getter
@Setter
public class Employee extends MainModel implements MainModelInterface {

    private int id;
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String note;
    private Double costPerHour;
    private String email;
}
