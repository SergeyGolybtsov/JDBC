package jdbs;
import java.sql.*;

public class Application {
    public static final String url = "jdbc:postgresql://localhost:5432/skypro";
    public static final String user = "postgres";
    public static final String password = "1234";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = 2");){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getInt(5));
                System.out.println(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EmployeeDAOImpl test = new EmployeeDAOImpl();
        test.create(new Employee("Гадкий", "Я", "Хто Я", 100, 3));
        test.readAll().forEach(System.out::println);
        test.updateById(7, new Employee("Жук", "Жукович", "Жуков", 55, 2));
        test.deleteById(7);
    }

    private static Connection getConnection() {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

