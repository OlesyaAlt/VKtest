import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;

public abstract class BaseTest {
    public static final String BASE_URL = "https://api.ok.ru/";

    @Before
    public void setup(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = BASE_URL;
    }
}
