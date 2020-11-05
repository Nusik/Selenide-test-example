package API.models;

import java.util.Objects;

public class EmployeeResponse {

    String status;
    Employee data;
    String message;

    public EmployeeResponse(String status, Employee data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResponse that = (EmployeeResponse) o;
        return Objects.equals(status, that.status) &&
                data.equals(that.data) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, message);
    }
}
