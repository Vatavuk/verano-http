package hr.com.vgv.verano.http;

public interface Response
{
    Status status();

    Headers headers();

    Body body();
}
