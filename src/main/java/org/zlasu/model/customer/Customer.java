package org.zlasu.model.customer;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

import java.util.Date;

@Getter
@Setter
public class Customer extends MainModel implements MainModelInterface {

    private int id;
    private String name;
    private String lastName;
    private Date dateOfBirth;

}
