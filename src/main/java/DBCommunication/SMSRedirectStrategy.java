package DBCommunication;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.protocol.HttpContext;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

/**
 * Created by Yerodin on 10/3/2015.
 */
public class SMSRedirectStrategy extends DefaultRedirectStrategy
{
    private List<NameValuePair> params;

    public SMSRedirectStrategy(List<NameValuePair> params)
    {
        this.params = params;
    }
    @Override
    protected boolean isRedirectable(String method)
    {
        return true;
    }

    @Override
    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException
    {
        URI uri = this.getLocationURI(request, response, context);
        String method = request.getRequestLine().getMethod();
        if(method.equalsIgnoreCase("HEAD")) {
            return new HttpHead(uri);
        } else if(method.equalsIgnoreCase("GET")) {
            return new HttpGet(uri);
        }
        else if(method.equalsIgnoreCase("POST"))
        {
            HttpPost httpPost = new HttpPost(uri);
            try {httpPost.setEntity(new UrlEncodedFormEntity(params));                          }catch (UnsupportedEncodingException e)
                                                                                                            {e.printStackTrace();}
            return httpPost;
        }
        else
         {
            int status = response.getStatusLine().getStatusCode();
            return status == 307? RequestBuilder.copy(request).setUri(uri).build():new HttpGet(uri);
        }
    }
}
