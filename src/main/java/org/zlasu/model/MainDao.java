package org.zlasu.model;

import org.zlasu.util.db.DbConnector;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class MainDao implements DaoInterface {

    protected DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    protected DateFormat yearFormat = new SimpleDateFormat("yyyy");

    protected abstract MainModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract ArrayList<String> getObjectParams(MainModelInterface item);

    protected abstract String getReadByIdQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getFindAllQuery();

    @Override
    public MainModelInterface readById(int id) {
        try (Connection connection = DbConnector.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(getReadByIdQuery())) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return newObjectFromResultSet(resultSet);
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(MainModelInterface item) {
        try (Connection connection = DbConnector.getConnection()) {
            if (item.getId() != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery());
                preparedStatement.setInt(1, item.getId());
                preparedStatement.executeUpdate();
                item.setId(0);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MainModelInterface create(MainModelInterface item) {
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery(),
                    Statement.RETURN_GENERATED_KEYS);

            ArrayList<String> params = getObjectParams(item);

            for (int i = 0; i < params.size(); i++) {
                if (i == 0 && (params.get(0) == null || params.get(0).equals("0"))) {
                    preparedStatement.setString(i + 1, "0");
                } else {
                    preparedStatement.setString(i + 1, params.get(i));
                }
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                return readById(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MainModelInterface update(MainModelInterface item) {
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

            ArrayList<String> params = getObjectParams(item);

            for (int i = 1; i < params.size(); i++) {
                preparedStatement.setString(i, params.get(i));
            }
            preparedStatement.setString(params.size(), params.get(0));

            preparedStatement.executeUpdate();

            return readById(Integer.parseInt(params.get(0)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MainModelInterface> findAll() {
        try (Connection conn = DbConnector.getConnection()) {
            List<MainModelInterface> mainModelInterfaces = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                mainModelInterfaces.add(newObjectFromResultSet(resultSet));
            }

            return mainModelInterfaces;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
