package org.zlasu.util.crud;

import java.util.ArrayList;
import java.util.List;

public interface DaoInterface {

    ModelInterface readById(int id);

    boolean delete(ModelInterface modelInterface);

    ModelInterface create(ModelInterface item);

    ModelInterface update(ModelInterface modelInterface);

    List<ModelInterface> findAll();
}
