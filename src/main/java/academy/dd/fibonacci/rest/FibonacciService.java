package academy.dd.fibonacci.rest;

import academy.dd.fibonacci.Maths;
import academy.dd.fibonacci.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevewalton on 20/07/2017.
 */
@Path("/fib")
public class FibonacciService {

    private static Logger LOGGER = LoggerFactory.getLogger(FibonacciService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStandardFibonacciSequence() throws IOException {
        List<String> fibSeq = new ArrayList();
        for(int i = 0; i < 10; i++) {
            fibSeq.add(String.valueOf(Maths.fibonacci(i)));
        }
        LOGGER.info(String.valueOf(fibSeq));
        return Response.ok(JsonUtils.toString(fibSeq)).build();
    }

    @GET
    @Path("/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFibonacciNumberAtGivenIndexInSequence(@PathParam("index") int index) throws IOException {
        return Response.ok(JsonUtils.toString(Maths.fibonacci(index))).build();
    }

    @GET
    @Path("/range")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFibonacciSequenceBetweenTwoIndexs(
            @QueryParam("startIndex") int startIndex, @QueryParam("finishIndex") int finishIndex) throws IOException {
        List<String> fibSeq = new ArrayList();
        for(int i = startIndex; i < finishIndex; i++) {
            fibSeq.add(String.valueOf(Maths.fibonacci(i)));
        }
        LOGGER.info(String.valueOf(fibSeq));
        return Response.ok(JsonUtils.toString(fibSeq)).build();
    }
}