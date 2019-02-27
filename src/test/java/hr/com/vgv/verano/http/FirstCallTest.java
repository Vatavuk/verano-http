package hr.com.vgv.verano.http;

import org.junit.Test;

import hr.com.vgv.verano.http.request.GetRequest;
import hr.com.vgv.verano.http.response.JsonResponse;
import hr.com.vgv.verano.http.wire.ApacheWire;
import hr.com.vgv.verano.http.wire.BasicAuth;

public class FirstCallTest
{
    @Test
    public void getReq() {
        String uri = "http://localhost:8080";
        new JsonResponse(
            new ApacheWire(
                uri,
                new BasicAuth("admin", "admin")
            ),
            new GetRequest(
                "/items"
            )
        ).json();
    }
}
