package hr.com.vgv.verano.http;

import java.io.IOException;
import java.util.Map;

public interface Wire
{
    Map<String, String> send(Map<String, String> message) throws IOException;
}
