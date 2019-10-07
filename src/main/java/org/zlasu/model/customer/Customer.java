package org.zlasu.model.customer;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.util.crud.ModelInterface;

import java.util.Date;

@Getter
@Setter
public class Customer {
    private int id;
    private String name;
    private String lastname;
    private Date date_of_birth;
}
