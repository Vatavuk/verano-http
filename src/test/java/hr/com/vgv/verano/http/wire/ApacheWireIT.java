package hr.com.vgv.verano.http.wire;

import hr.com.vgv.verano.http.parts.Body;
import hr.com.vgv.verano.http.parts.JsonBody;
import hr.com.vgv.verano.http.parts.headers.ContentType;
import hr.com.vgv.verano.http.request.Post;
import hr.com.vgv.verano.http.response.ExpectedStatus;
import hr.com.vgv.verano.http.response.FailWith;
import hr.com.vgv.verano.http.response.Response;
import hr.com.vgv.verano.http.wire.apache.BasicAuth;
import org.junit.Ignore;
import org.junit.Test;

public class ApacheWireIT
{
    @Test
    @Ignore
    public void postReq()
    {
        new JsonBody.Of(
            new Response(
                "http://example.com",
                new Post(
                    new Body("Hello World"),
                    new ContentType("text/html")
                )
            )
        ).json();
    }

    @Test
    public void googlePage()
    {
        new Response(
            new ApacheWire("http://google.com", new BasicAuth("userame", "password")),
            new ExpectedStatus(301, new FailWith("Cannot fetch from Google"))
        ).touch();
    }
}
