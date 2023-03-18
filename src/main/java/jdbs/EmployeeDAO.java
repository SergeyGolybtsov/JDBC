package jdbs;


import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);
    Employee readById(int id);
    List<Employee> readAll();
    void updateById(int id, Employee employee);
    void deleteById(int id);
}
