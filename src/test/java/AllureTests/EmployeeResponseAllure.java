package AllureTests;

import java.util.Objects;

public class EmployeeResponseAllure {

    String status;
    EmployeeAllure data;
    String message;

    public EmployeeResponseAllure(String status, EmployeeAllure data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public EmployeeResponseAllure(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResponseAllure that = (EmployeeResponseAllure) o;
        return Objects.equals(status, that.status) &&
                data.equals(that.data) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, message);
    }
}
