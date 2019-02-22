package hr.com.vgv.verano.http;

import java.lang.annotation.Target;
import java.util.Map;

import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.junit.Test;

import com.oracle.webservices.internal.api.message.ContentType;

import hr.com.vgv.verano.http.wire.ApacheWire;

public class Tmp
{
    @Test
    public void tmp() {

        System.out.print(map);
    }


    /*public void getReq()
    {
        new JsonResponse(
            new GetReq(
                wire,
                "/operators",
                new Headers(
                    new Accept("application/json"),
                    new Cookie("some-cookie")
                ),
                new QueryParams(
                    new QParam("startIndex", "100")
                )
            ),
            new ExpectedStatus(200)
        ).json();
    }

    public void postReq()
    {
        //TODO: What about expectedStatus??
        new PostReq(
            wire,
            "/operators/id",
            new Headers(
                new ContentType("application/json"),
                new Cookie("some-cookie")
            ),
            "Hello World!"
        );
    }

    public void customWire() {
        new CachedWire(
            new RetryWire(
                new ApacheWire(
                    "http://localhost:8181",
                    new BasicAuth("admin", "admin"),
                    new SslTrusted()
                ),
                3
            )
        );
    }*/
}
