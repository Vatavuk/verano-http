package hr.com.vgv.verano.http.wire;

import java.net.URI;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

public class BasicAuth implements ApacheContext
{
    private final String uri;

    private final String username;

    private final String password;

    public BasicAuth(String uri, String username, String password)
    {
        this.uri = uri;
        this.username = username;
        this.password = password;
    }

    @Override
    public final HttpClientBuilder apply(HttpClientBuilder builder) {
        final CredentialsProvider provider = new BasicCredentialsProvider();
        URI uri = URI.create(this.uri);
        provider.setCredentials(
            new AuthScope(uri.getHost(), uri.getPort()),
            new UsernamePasswordCredentials(this.username, this.password)
        );
        return builder.setDefaultCredentialsProvider(provider);
    }
}
