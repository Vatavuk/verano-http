package hr.com.vgv.verano.http.wire;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

public class SslTrusted implements ApacheContext
{
    @Override
    public final HttpClientBuilder apply(HttpClientBuilder builder) {
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
            sslContext, new X509HostnameVerifier() {
            @Override
            public void verify(String host, SSLSocket ssl)
                throws IOException
            {
            }

            @Override
            public void verify(String host, X509Certificate cert)
                throws SSLException
            {
            }

            @Override
            public void verify(String host, String[] cns,
                String[] subjectAlts) throws SSLException {
            }

            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        return builder.setSSLSocketFactory(sslsf);
    }
}
