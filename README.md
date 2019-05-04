<img src="logo.png" alt="drawing" height="100"/>

[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/Vatavuk/verano-http)](http://www.rultor.com/p/Vatavuk/verano-http)

[![Build Status](https://travis-ci.org/Vatavuk/verano-http.svg?branch=master)](https://travis-ci.org/Vatavuk/verano-http)
[![Test Coverage](https://codecov.io/gh/Vatavuk/verano-http/branch/master/graph/badge.svg)](https://codecov.io/gh/Vatavuk/verano-http)
[![Hits-of-Code](https://hitsofcode.com/github/Vatavuk/verano-http)](https://hitsofcode.com/view/github/Vatavuk/verano-http)
[![SonarQube](https://img.shields.io/badge/sonar-ok-green.svg)](https://sonarcloud.io/dashboard/index/hr.com.vgv.verano:verano-http)

[![Javadocs](http://javadoc.io/badge/hr.com.vgv.verano/verano-http.svg)](http://javadoc.io/doc/hr.com.vgv.verano/verano-http)
[![Maven Central](https://img.shields.io/maven-central/v/hr.com.vgv.verano/verano-http.svg)](https://maven-badges.herokuapp.com/maven-central/hr.com.vgv.verano/verano-http)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Vatavuk/verano-http/blob/master/LICENSE.txt)


HTTP client that provides object-oriented interface for building HTTP requests. 
The benefits of this library are that it is easily customizable, declarative and immutable.
It can also be [tested](#testing) without mock servers which gives a performance edge.

Similar libraries: [cactoos-http](https://github.com/yegor256/cactoos-http), [jcabi-http](https://github.com/jcabi/jcabi-http)

```xml
<dependency>
    <groupId>hr.com.vgv.verano</groupId>
    <artifactId>verano-http</artifactId>
    <version>1.1</version>
</dependency>
```

## Get a Url
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

## Post to a Server
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
`Touch` method executes the `HTTP` request towards the server.

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

## Response handling
Extraction of response parameters can be done using `*.Of` classes:
```java
Response response = new Response(
    "http://example.com",
    new Get("/items")
);
Map<String, List<String>> headers = new Headers.Of(response).asMap(); // Extraction of headers from response
String cookie = new Cookie.Of("cookieName", response).asString();
String body = new Body.Of(response).asString();
```

You can make assertions on received responses like this:
```java
new Response(
    "http://exmpl.com",
    new Get("/items"),
    new ExpectedStatus(
        200, 
        new FailWith("Cannot fetch from exmpl")
    )
).touch();
```

## Serialization and Deserialization
The library provides following types of request body serialization:
- [JsonBody](https://github.com/Vatavuk/verano-http/blob/master/src/main/java/hr/com/vgv/verano/http/parts/body/JsonBody.java#L42) - javax.json
- [XmlBody](https://github.com/Vatavuk/verano-http/blob/master/src/main/java/hr/com/vgv/verano/http/parts/body/XmlBody.java#37)  - [jcabi-xml](https://github.com/jcabi/jcabi-xml)
- [DtoBody](https://github.com/Vatavuk/verano-http/blob/master/src/main/java/hr/com/vgv/verano/http/parts/body/DtoBody.java#37)  - jackson object mapper
- [HtmlBody](https://github.com/Vatavuk/verano-http/blob/master/src/main/java/hr/com/vgv/verano/http/parts/body/HtmlBody.java#37) - [jsoup](https://github.com/jhy/jsoup)

Response body deserialization can be achieved using their accompanied `*.Of` classes.

## Wire
Verano-http runs on `ApacheWire` which encapsulates [Apache http client](https://github.com/apache/httpcomponents-client).
You can provide additional configuration to the wire:
```java
Wire wire = new ApacheWire(
    "http://exmpl.com",
    new Proxy("127.0.0.1", 8000),
    new SslTrusted()
);
new Response(
    wire, new Get("/items")
).touch();
```
You can also provide http parameters to the wire:
```java
new Response(
    new ApacheWire(
        "http://exmpl.com", 
        new ContentType("application/json"),
        new Header("foo", "bar"),
    ),
    new Get("/items")
).touch();
```

## Testing
Http requests can be tested through `MockWire` without using a http server.
`MockWire` works in conjunction with [hamcrest matchers](http://hamcrest.org/JavaHamcrest/) in a following way:
```java
MockWire wire = new MockWire(
    new MockAnswer(
        new PathMatch(MatchesPattern.matchesPattern("/*")),
        new Response(new Status(201))
    )
);
sendRequest(wire);
wire.verify(
    new PostMatch(
        new PathMatch(new IsEqual<>("/items")),
        new BodyMatch(new StringContains("text"))
    )
);
```

## Contribution
You can contribute by forking the repo and sending a pull request.
Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```
