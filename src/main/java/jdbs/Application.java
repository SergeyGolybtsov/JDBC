package jdbs;
import java.sql.*;

public class Application {
    public static final String url = "jdbc:postgresql://localhost:5432/skypro";
    public static final String user = "postgres";
    public static final String password = "1234";

    private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public static void main(String[] args) {
        Employee test = new Employee("Аркадий", "Паровозов", "Муж", 32, 7);
        employeeDAO.create(test);
        System.out.println(employeeDAO.readById(4));
        employeeDAO.readAll().forEach(System.out::println);
        Employee employee = new Employee(5, "Таракан", "Тараканов", "Муж", 4,5);
        employeeDAO.update(employee);
        employeeDAO.delete(employee);
    }

    private static Connection getConnection() {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

