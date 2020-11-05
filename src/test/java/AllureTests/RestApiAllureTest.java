package AllureTests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;

public class RestApiAllureTest {

    @BeforeClass
    public void start() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void getAllEmployeesTest() {
        given()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("1", "2", "3"));
    }

    @Test
    public void getAllEmployeesNegativeTest() {
        given()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("50"));
    }

    @Test
    public void getAllEmployeeByIdTest() {
        EmployeeAllure expectedEmployee = new EmployeeAllure("Tiger Nixon", 320800, 61, "");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponseAllure employeeResponse = given()
                .get("/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void getAllEmployeeByIdNegativeTest() {
        EmployeeAllure expectedEmployee = new EmployeeAllure("Tiger Nixon", 320800, 61, "");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponseAllure employeeResponse = given()
                .get("/employee/11")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void postEmployeeTest() {
        PostEmployeeModelAllure postEmployeeModel = new PostEmployeeModelAllure("QWERTY", "123123", "123");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", new EmployeeAllure(), "Successfully! Record has been added.");
        EmployeeResponseAllure employeeResponse = given()
                .with()
                .body(postEmployeeModel)
                .post("/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void postEmployeeNegativeTest() {
        PostEmployeeModelAllure postEmployeeModel = new PostEmployeeModelAllure("16", "qwerty", "123123");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", new EmployeeAllure(), "Successfully! Record has been added.");
        EmployeeResponseAllure employeeResponse = given()
                .with()
                .body(postEmployeeModel)
                .post("/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void putAllEmployeeByIdTest() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "21");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", new EmployeeAllure(), "Successfully! Record has been updated.");
        EmployeeResponseAllure employeeResponse = given()
                .with()
                .body(request)
                .put("/update/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);

    }

    @Test
    public void putAllEmployeeByIdNegativeTest() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "21");
        request.put("employee_name", "New test");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", new EmployeeAllure(), "Successfully! Record has been updated.");
        EmployeeResponseAllure employeeResponse = given()
                .with()
                .body(request)
                .put("/update/50")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void deleteEmployeeTest() {
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", "successfully! deleted Records");
        EmployeeResponseAllure employeeResponse = given()
                .delete("/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void deleteEmployeeNegativeTest() {
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", "successfully! deleted Records");
        EmployeeResponseAllure employeeResponse = given()
                .delete("/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
        given()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("data.employee_name", hasItems("Lui Armstrong"));
    }
}
