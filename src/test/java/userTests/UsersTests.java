package userTests;

import model.CreateUserModel;
import model.ListUsersModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static model.Specs.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTests {

    @Test
    @DisplayName("Список всех пользователей со 2 страницы c использованием Groovy")
    void getListUsersByPageNumberPositiveTest() {
        ListUsersModel listUsers = given()
                .spec(request)
                .when()
                .param("page", 2)
                .get("/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("data.findAll{it.id == 11}.last_name.flatten()",
                        hasItem("Edwards"))
                .extract().as(ListUsersModel.class);
        assertEquals(2, listUsers.getPage());
    }

    @Test
    @DisplayName("Прмиер с урока")
    public void checkEmailUsingGroovy() {
        given()
                .spec(request)
                .when()
                .get("/users")
                .then()
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("eve.holt@reqres.in"));
    }

    @DisplayName("Создание пользователя с использованием модели Lombok")
    @Test
    void createUserWithModelTest() {
        CreateUserModel body = new CreateUserModel();
        body.setName("Neo");
        body.setJob("elected");

        given()
                .spec(request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec201)
                .body("name", is("Neo"))
                .body("job", is("elected"))
                .extract().path("id");
    }


}


