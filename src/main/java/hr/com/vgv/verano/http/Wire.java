package hr.com.vgv.verano.http;

import java.io.IOException;

public interface Wire
{
    Dict send(Dict message) throws IOException;
}
