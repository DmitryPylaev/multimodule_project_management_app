package com.digdes.java2023.repository.employee;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.repository.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStorageJDBC implements DataStorage {
    @Override
    public void create(Employee employee) {
        String request = "INSERT INTO employee (lastname, name, patronymic, position, account, email, employee_status) VALUES (?,?,?,?,?,?,?);";
        try (Connection dbConnection = ConnectionBuilder.getDbConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request, new String[] {});
            preparedStatement.setString(1, employee.getLastName());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getPatronymic());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getAccount());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setString(7, employee.getEmployeeStatus());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) employee.setId(generatedKeys.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee changes) {
        try (Connection dbConnection = ConnectionBuilder.getDbConnection()) {
            dbConnection.setAutoCommit(false);
            if (changes.getLastName() != null) updateColumn("lastname", changes.getLastName(), changes.getAccount(), dbConnection);
            if (changes.getName() != null) updateColumn("name", changes.getName(), changes.getAccount(), dbConnection);
            if (changes.getPatronymic() != null) updateColumn("patronymic", changes.getPatronymic(), changes.getAccount(), dbConnection);
            if (changes.getPosition() != null) updateColumn("position", changes.getPosition(), changes.getAccount(), dbConnection);
            if (changes.getAccount() != null) updateColumn("account", changes.getAccount(), changes.getAccount(), dbConnection);
            if (changes.getEmail() != null) updateColumn("email", changes.getEmail(), changes.getAccount(), dbConnection);
            dbConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Employee> getById(long id) {
        String request = "SELECT * FROM employee WHERE id=?;";
        Employee employee = null;
        try (Connection dbConnection = ConnectionBuilder.getDbConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setLastName(resultSet.getString("lastname"));
                employee.setName(resultSet.getString("name"));
                employee.setPatronymic(resultSet.getString("patronymic"));
                employee.setPosition(resultSet.getString("position"));
                employee.setAccount(resultSet.getString("account"));
                employee.setEmail(resultSet.getString("email"));
                employee.setEmployeeStatus(resultSet.getString("employee_status"));
                employee.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getAll ( ) {
        String request = "SELECT * FROM employee;";
        var result = new ArrayList<Employee>();
        Employee employee;
        try (Connection dbConnection = ConnectionBuilder.getDbConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee();
                employee.setLastName(resultSet.getString("lastname"));
                employee.setName(resultSet.getString("name"));
                employee.setPatronymic(resultSet.getString("patronymic"));
                employee.setPosition(resultSet.getString("position"));
                employee.setAccount(resultSet.getString("account"));
                employee.setEmail(resultSet.getString("email"));
                employee.setEmployeeStatus(resultSet.getString("employee_status"));
                employee.setId(resultSet.getLong("id"));
                result.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteById(long id) {
        String request = "DELETE FROM employee WHERE id=?;";
        try (Connection dbConnection = ConnectionBuilder.getDbConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateColumn(String column, String value, String account, Connection dbConnection) throws SQLException {
        String request = "UPDATE employee SET " + column + " = '" + value + "' WHERE account = '" + account + "'";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(request, new String[]{});
        preparedStatement.executeUpdate();
    }
}
