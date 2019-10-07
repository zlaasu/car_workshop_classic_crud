package org.zlasu.util.crud;

import org.zlasu.util.db.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoMain implements DaoInterface {

    protected abstract ModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract ArrayList<String> getObjectParams(ModelInterface item);

    protected abstract String getReadByIdQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getFindAllQuery();

    @Override
    public ModelInterface readById(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(getReadByIdQuery())) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return newObjectFromResultSet(resultSet);
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public boolean delete(ModelInterface item) {
        try (Connection connection = DbUtil.getConnection()) {
            if (item.getId() != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery());
                preparedStatement.setInt(1, item.getId());
                preparedStatement.executeUpdate();
                item.setId(0);
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public ModelInterface create(ModelInterface item) {
        try (Connection connection = DbUtil.getConnection()) {
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
            System.err.println(e);
        }
        return null;
    }

    @Override
    public ModelInterface update(ModelInterface item) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

            ArrayList<String> params = getObjectParams(item);

            for (int i = 1; i < params.size(); i++) {
                preparedStatement.setString(i, params.get(i));
            }
            preparedStatement.setString(params.size(), params.get(0));

            preparedStatement.executeUpdate();

            return readById(Integer.parseInt(params.get(0)));
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public List<ModelInterface> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<ModelInterface> modelInterfaces = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(getFindAllQuery());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                modelInterfaces.add(newObjectFromResultSet(resultSet));
            }

            return modelInterfaces;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
