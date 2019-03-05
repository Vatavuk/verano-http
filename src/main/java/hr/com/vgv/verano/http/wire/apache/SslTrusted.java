package hr.com.vgv.verano.http.wire.apache;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

public class SslTrusted implements ApacheContext
{
    @Override
    public final HttpClientBuilder apply(URI uri, HttpClientBuilder builder) {
        final SSLContextBuilder ssl = SSLContexts.custom();
        final SSLContext sslContext;
        try
        {
            ssl.loadTrustMaterial((chain, type) -> true);
            sslContext = ssl.build();
        }
        catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e)
        {
            throw new IllegalStateException(e);
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            sslContext, new HostnameVerifier() {

            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        return builder.setSSLSocketFactory(sslsf);
    }
}
