package org.zlasu.model;

import java.util.List;

public interface DaoInterface {

    MainModelInterface readById(int id);

    boolean delete(MainModelInterface mainModelInterface);

    MainModelInterface create(MainModelInterface item);

    MainModelInterface update(MainModelInterface mainModelInterface);

    List<MainModelInterface> findAll();

}
