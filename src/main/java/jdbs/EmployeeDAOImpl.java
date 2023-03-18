package jdbs;
import static jdbs.Application.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public void create(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?),(?),(?),(?),(?))");){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setGender(resultSet.getString(4));
                employee.setAge(resultSet.getInt(5));
                employee.setCityId(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> EmployeeList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = (resultSet.getInt(1));
                String firtsName = (resultSet.getString(2));
                String lastName = (resultSet.getString(3));
                String gender = (resultSet.getString(4));
                int age = (resultSet.getInt(5));
                int cityId = (resultSet.getInt(6));

                EmployeeList.add(new Employee(id, firtsName, lastName, gender, age, cityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return EmployeeList;
    }


    @Override
    public void updateById(int id, Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) WHERE id = (?)")){
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
