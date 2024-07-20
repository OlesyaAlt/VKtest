import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class EditPhotoTest extends BaseTest{
    private static final String APPLICATION_ID = "512002092925";
    private static final String APPLICATION_KEY = "COHQOELGDIHBABABA";
    private static final String APPLICATION_SECRET_KEY = "CA39E6A08A3E31D72D1CC279";
    private static final String SESSION_KEY = "?";
    private static final String SESSION_SECRET_KEY = "?";

    //я создала приложение согласно инструкции, но не получилось его запустить...
    //поэтому пишу в слепую и запустить/проверить их не получится
    //Предполагаю, что для изменения описания к фото нужно
    @Before
    //получить id фото
   //получить описание фото

    @Test //проверяем, что описание изменилось
    public void changeDescriptionPhoto(){
        String json = "{\"photo_id\": \"photo_id\", \"description\": \"новое описание фото\"}";
Response response =
        given()
               .header("Content-type", "application/json")
               .and()
               .header("application_id", APPLICATION_ID)
               .and()
               .header("application_key", APPLICATION_KEY)
               .and()
               .header("application_secret_key", APPLICATION_SECRET_KEY)
               .auth().basic("session_key", SESSION_KEY)
               .and()
               .auth().basic("session_secret_key", SESSION_SECRET_KEY)
                .body(json)
                .when()
                .post("/api/photos.editPhoto");
        response.then().assertThat()
                .statusCode(200)
                .body("description", equalTo("новое описание фото"));

   }
    @Test //проверяем, что описание обнулилось, если не передали описание
    public void DescriptionPhotoIsNull(){
        String json = "{\"photo_id\": \"photo_id\", \"description\": \"\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .header("application_id", APPLICATION_ID)
                        .and()
                        .header("application_key", APPLICATION_KEY)
                        .and()
                        .header("application_secret_key", APPLICATION_SECRET_KEY)
                        .auth().basic("session_key", SESSION_KEY)
                        .and()
                        .auth().basic("session_secret_key", SESSION_SECRET_KEY)
                        .body(json)
                        .when()
                        .post("/api/photos.editPhoto");
        response.then().assertThat()
                .statusCode(200)
                .body("description", null);
    }
    // далее нужно провести негативные проверки:
    // что если в теле запроса не передать photo_id вернется ошибка - 40*
    // что нельзя изменить описание фото без указания всех обязательных ключей в заголовке
    // что нельзя изменить описание фото без авторизации
    // можно применить параметризацию, а также создать модель объекта (POJO)
    // но тупо не успела, провозившись с приложением
    //сорри
}
