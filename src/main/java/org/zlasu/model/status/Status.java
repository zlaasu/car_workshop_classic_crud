package org.zlasu.model.status;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

@Getter
@Setter
public class Status extends MainModel implements MainModelInterface {

    private int id;
    private String name;

}
