package hr.com.vgv.verano.http;

public interface Request
{
    RequestDetails details();

    Response fetch();
}
