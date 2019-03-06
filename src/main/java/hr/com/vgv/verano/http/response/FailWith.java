package hr.com.vgv.verano.http.response;

import org.cactoos.Text;

public class FailWith implements Text
{
    private final String text;

    public FailWith(String text)
    {
        this.text = text;
    }

    @Override
    public final String asString()
    {
        return this.text;
    }
}
