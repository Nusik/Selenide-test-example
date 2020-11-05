package API;

import API.models.Employee;
import API.models.EmployeeResponse;
import API.models.PostEmployeeModel;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;

public class RestApiTest {

    @BeforeClass
    public void start() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void positiveGetAllEmployeeTest() {
        given().log().all()
                .when()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("1", "2", "3"));
    }

    @Test
    public void negativeAllEmployeesWrongIdTest() {
        given().log().all()
                .when()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("0"));
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee expectedEmployee = new Employee("Tiger Nixon", 320800, 61, "");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .when()
                .get("/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);

        assertEquals(response, expectedResponse);
    }

    @Test
    public void negativeGetEmployeeByIdWrongDataTest() {
        Employee expectedEmployee = new Employee("Tiger", 32000, 61, "");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .when()
                .get("/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);

        assertEquals(response, expectedResponse);
    }

    @Test
    public void postEmployeeTest() {
        PostEmployeeModel employee = new PostEmployeeModel("Test", "990099", "12");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been fetched.");

        EmployeeResponse response = given()
                .with()
                .body(employee)
                .get("/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);

        assertEquals(response, expectedResponse);
    }

    @Test
    public void negativePostEmployeeStatusCodeTest() {
        PostEmployeeModel employee = new PostEmployeeModel(" ", "", "");
        EmployeeResponse expectedResponse = new EmployeeResponse("unsuccess", new Employee(), "UnSuccessfully! Record hasn't been fetched.");

        EmployeeResponse response = given()
                .with()
                .body(employee)
                .get("/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);

        assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteEmployeeIdTest() {
        Employee employee = new Employee(2);
        EmployeeResponse expectedResponse = new EmployeeResponse("success", employee, "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .delete("/delete/" + employee)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void negativeDeleteEmployeeIdTest() {
        Employee employee = new Employee(2);
        EmployeeResponse expectedResponse = new EmployeeResponse("success", employee, "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .delete("/delete/" + 22)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void putEmployeeTest() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "21");
        request.put("employee_name", "New test");
        request.put("employee_salary", "50000");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .with()
                .body(request)
                .put("/update/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void negativePutStringInEmployeeIdTest() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "Test");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .with()
                .body(request)
                .put("/update/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, expectedResponse);
    }

}
