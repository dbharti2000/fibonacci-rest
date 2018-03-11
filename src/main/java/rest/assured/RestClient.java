package rest.assured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by dbharti on 10/03/2018.
 */

public class RestClient {

    private static String requestURL = "";
    public static Response response;
    public static String responseString = "";


    public void setBaseURL() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7003;
    }

    public void setRequestURL(String url) {
        setBaseURL();
        requestURL = url;
    }

    public Response performGetRequest() {
        response = get(requestURL);
        responseString = response.asString();
        return response;
    }

    public void assertStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

}
