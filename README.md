# Verano-HTTP
### Object-Oriented Java HTTP Client
[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/Vatavuk/verano-http)](http://www.rultor.com/p/Vatavuk/verano-http)

HTTP client that provides object oriented interface for building HTTP requests. 

- Declarative
- Immutable
- Open for extension

## How to use
Latest version [here](https://github.com/Vatavuk/verano-http/releases)
```xml
<dependency>
    <groupId>hr.com.vgv.verano.http</groupId>
    <artifactId>verano-http</artifactId>
    <version>0.2</version>
</dependency>
```

### Post to a Server
```java
new Response(
    new ApacheWire("http://exmpl.com"),
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
    new ApacheWire("http://exmpl.com"),
    new Post(
        "/items",
        new FormParam("name","John"),
        new FormParam("foo","bar"),
    )
).touch();
```

### Get a Url
```java
JsonObject json = new JsonResponse(
    new ApacheWire("http://exmpl.com"),
    new Get(
        "/items",
        new QueryParam("name", "John"),
        new Accept("application/json"),
    )
).json();
```
### Response handling

#### Lazy Loading

### Assertions
We can make assertions on received response like this:
```java
new Response(
    new ApacheWire("http://google.com"),
    new ExpectedStatus(
        301, 
        new FailWith("Cannot fetch from Google")
    )
).touch();
```
If the status code is not 301, exception will be raised with message specified
by `FailWith`. Exception message will also contain response details. Custom
exception message is optional.

### Advanced Configuration

#### Authentication

#### Proxy

#### Custom Wires
