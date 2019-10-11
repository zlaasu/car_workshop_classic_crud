package org.zlasu.model.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAuth  {

    private int id;
    private String email;
    private String password;
    private String token;

}
