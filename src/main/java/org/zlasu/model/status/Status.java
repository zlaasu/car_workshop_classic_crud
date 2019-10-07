package org.zlasu.model.status;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.util.crud.ModelInterface;
import org.zlasu.util.crud.ModelMain;

@Getter
@Setter
public class Status extends ModelMain implements ModelInterface {
    private int id;
    private String name;

}
