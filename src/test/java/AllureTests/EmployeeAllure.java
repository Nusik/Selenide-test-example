package AllureTests;

import java.util.Objects;

public class EmployeeAllure {
    Integer id;
    String employee_name;
    Integer employee_salary;
    Integer employee_age;
    String profile_image;

    public EmployeeAllure(String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
        this.employee_name = employeeName;
        this.employee_salary = employeeSalary;
        this.employee_age = employeeAge;
        this.profile_image = profileImage;
    }

    public EmployeeAllure() {
    }

    public EmployeeAllure(Integer id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAllure employeeAllure = (EmployeeAllure) o;
        return
                Objects.equals(employee_name, employeeAllure.employee_name) &&
                Objects.equals(employee_salary, employeeAllure.employee_salary) &&
                Objects.equals(employee_age, employeeAllure.employee_age) &&
                Objects.equals(profile_image, employeeAllure.profile_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_name, employee_salary, employee_age, profile_image);
    }
}
