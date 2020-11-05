package API.models;

import java.util.Objects;

public class Employee {
    Integer id;
    String employee_name;
    Integer employee_salary;
    Integer employee_age;
    String profile_image;

    public Employee(String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
        this.employee_name = employeeName;
        this.employee_salary = employeeSalary;
        this.employee_age = employeeAge;
        this.profile_image = profileImage;
    }

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return
                Objects.equals(employee_name, employee.employee_name) &&
                Objects.equals(employee_salary, employee.employee_salary) &&
                Objects.equals(employee_age, employee.employee_age) &&
                Objects.equals(profile_image, employee.profile_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_name, employee_salary, employee_age, profile_image);
    }
}
