package org.zlasu.model.status;

import org.zlasu.model.MainDao;
import org.zlasu.model.MainModelInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDao extends MainDao {
    private final String READ_BY_ID_QUERY = "SELECT id, name FROM status where id = ?";
    private final String DELETE_QUERY = "DELETE FROM status WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO status(id, name) VALUES (?, ?)";
    private final String UPDATE_QUERY = "UPDATE status SET name = ? where id = ?";
    private final String FIND_ALL_QUERY = "SELECT id, name FROM status";

    @Override
    protected MainModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Status status = new Status();
        status.setId(resultSet.getInt("id"));
        status.setName(resultSet.getString("name"));
        return status;
    }

    @Override
    public ArrayList<String> getObjectParams(MainModelInterface item) {
        ArrayList<String> params = new ArrayList();
        Status status = (Status) item;
        params.add(String.valueOf(status.getId()));
        params.add(status.getName());
        return params;
    }

    @Override
    protected String getReadByIdQuery() {
        return READ_BY_ID_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }

}
