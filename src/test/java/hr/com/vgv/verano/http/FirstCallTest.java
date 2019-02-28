package hr.com.vgv.verano.http;

import org.junit.Test;

import hr.com.vgv.verano.http.request.Body;
import hr.com.vgv.verano.http.request.Post;
import hr.com.vgv.verano.http.response.JsonResponse;
import hr.com.vgv.verano.http.wire.ApacheWire;

public class FirstCallTest
{
    @Test
    public void postReq() {
        new JsonResponse(
            new ApacheWire("http://example.com"),
            new Post(new Body("Hello World"))
        ).json();
    }
}
