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

    public Status() {
    }

    public Status(int id, String status) {
        this.id = id;
        this.name = status;
    }

    public Status(String[] row) {
        this.id = Integer.parseInt(row[0]);
        this.name = row[1];
    }

}
