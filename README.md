# Verano-HTTP
### Object-Oriented Java HTTP Client
[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/Vatavuk/verano-http)](http://www.rultor.com/p/Vatavuk/verano-http)

HTTP client that provides object-oriented interface for building HTTP requests. 

- Open for extension
- Declarative
- Immutable

## How to use
Latest version [here](https://github.com/Vatavuk/verano-http/releases)
```xml
<dependency>
    <groupId>hr.com.vgv.verano.http</groupId>
    <artifactId>verano-http</artifactId>
    <version>0.41</version>
</dependency>
```
### Get a Url
```java
JsonObject json = new JsonBody.Of(
    new Response(
        "http://example.com",
        new Get(
            "/items",
            new QueryParam("name", "John"),
            new Accept("application/json")
        )
    )
).json();
```

### Post to a Server
```java
new Response(
    "http://example.com",
    new Post(
        "/items",
        new Body("Hello World!"),
        new ContentType("text/html"),
    )
).touch();
```
Using form parameters:
```java
new Response(
    "http://example.com",
    new Post(
        "/items",
        new FormParam("name","John"),
        new FormParam("foo","bar"),
    )
).touch();
```

### Serialization and Deserialization
The library provides following types of request serialization:
- JsonBody - javax.json
- XmlBody  - [jcabi-xml](https://github.com/jcabi/jcabi-xml)
- DtoBody  - jackson object mapper
- HtmlBody - [jsoup](https://github.com/jhy/jsoup)

Response deserialization can be achieved using their accompanied `*.Of` classes.

### Response handling
Response parameters can be extracted using `*.Of` classes:
```java
Response response = new Response(
    "http://example.com",
    new Get("/items")
);
Map<String, List<String>> headers = new Headers.Of(response).asMap();
String cookie = new Cookie.Of("cookieName", response).asString();
String body = new Body.Of(response).asString();
```

#### Assertions
We can make assertions on received responses like this:
```java
new Response(
    "http://exmpl.com",
    new Get("/items"),
    new ExpectedStatus(
        301, 
        new FailWith("Cannot fetch from exmpl")
    )
).touch();
```
If the status code is not 301, exception will be raised with message specified
by `FailWith`. Exception message will also contain response details. Custom
exception message is optional.

### Wires
`Wire` is an interface through which requests are executed. Verano-http runs 
on `ApacheWire` which encapsulates [Apache http client](https://github.com/apache/httpcomponents-client).
If you need a different engine, you can make your custom `Wire` implementation.

Default http parameters can be specified directly on `ApacheWire` to be used
in each request:
```java
Wire wire = new ApacheWire(
    "http://exmpl.com",
    new ContentType("applicaiton/json"),
    new Header("foo", "bar"),
);
new Response(
    wire, new Get("/items")
).touch();
```

#### Configuration
- BasicAuth - Basic and Digest authentication
- Proxy - make requests through proxy
- SslTrusted - trust all certificates

```java
new Response(
    new ApacheWire(
        "http://exmpl.com", 
        new BasicAuth("userame", "password"),
        new Proxy("127.0.0.1", 8000)
        new SslTrusted()
    ),
    new Get("/items")
).touch();
```
If you need additional functionality just implement `ApacheContext` and provide
it as an argument to `ApacheWire`.

#### Custom Wires


You can also make custom `Wire` decorators to enrich http requests. Let's say
you need a custom authentication for requests towards remote api.
You can implement it in this way:

```java
public class CustomAuthWire implements Wire {
 
     private final Wire wire;
 
     public CustomAuth(final Wire wire) {
         this.wire = wire;
     }
 
     @Override
     public Dict send(final Dict request) throws IOException {
         final String token = login();
         return this.wire.send(
             new JoinedDict(
                 request,
                 new DictOf(
                     new Authorization(token),
                     new Cookie("someCookie")
                 )
             )
         );
     }
}
```
### Contribution
You can contribute by forking the repo and sending a pull request.
Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```
