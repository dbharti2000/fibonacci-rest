import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.assured.FibonacciSeries;
import rest.assured.RestClient;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static rest.assured.FibonacciSeries.getFibonacciFormAndTo;

/**
 * Created by dbharti on 10/03/2018.
 */


public class FibonacciTest {

    private static Logger LOG = LoggerFactory.getLogger(FibonacciTest.class);
    private Response response;
    private ValidatableResponse jsonresponse;
    private RequestSpecification request;

    RestClient restClient = new RestClient();


    @Test
    public void testResponseStatusCodeFromFibService() {
        restClient.setRequestURL("/fib");
        restClient.performGetRequest();
        restClient.assertStatusCode(200);
    }

    //Test the Fibonacci index endpoint for values 0 to 50, it'll print the index when application stops displaying incorrect Fibonacci number
    @Test
    public void testFibonacciServiceFailureWithIndexEndPoint() {

        for (int index = 0; index <= 50; index++) {
            restClient.setRequestURL("/fib/" + index);
            long actualValue = Long.parseLong(restClient.performGetRequest().asString());
            long expectedValue = FibonacciSeries.fibonacci(index);

            if (actualValue != expectedValue) {
                LOG.info("Application stopped returning Fibonacci numbers in their correct sequence at index: " + index + ", Expected Fibonacci Number:" + expectedValue + ", Actual Fibonacci Number: " + actualValue);
                break;
            }
        }
    }

    //We can change the start and final values, if expected fibonacci is not same as of actual, test will fail.
    @Test
    public void testFibonacciServiceWithRangeEndPoint() {

        int startIndex = 15;
        int finalIndex = 25;

        restClient.setRequestURL("/fib/range?startIndex=" + startIndex + "&finishIndex=" + finalIndex);

        //get the response string from endpoint and remove the unwanted things.
        String responseString = restClient.performGetRequest().asString().replaceAll("\"", "").replaceAll("]", "").replaceAll("\\[", "");

        restClient.assertStatusCode(200);

        //convert string into list
        List<Long> actualFibonacciSeriesList = Arrays.asList(responseString.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        List<Long> expectedlFibonacciSeriesList = getFibonacciFormAndTo(startIndex, finalIndex);

        LOG.info("\nActual Fibonacci Series: " + actualFibonacciSeriesList + "\n" + "Expected Fibonacci Series: " + expectedlFibonacciSeriesList);

        Assert.assertTrue("Both Fibonacci series are not same, Expected Fibonacci Series: " + expectedlFibonacciSeriesList +
                "\n Actual Fibonacci Series: " + actualFibonacciSeriesList, expectedlFibonacciSeriesList.equals(actualFibonacciSeriesList));

    }

}
