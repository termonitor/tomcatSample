package com.xiaoming.unit3;

import com.xiaoming.unit2.ServletProcessor;
import com.xiaoming.unit2.StaticResourceProcessor;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by panxiaoming on 15/12/3.
 */
public class HttpProcessor {

    private HttpRequest request = null;
    private HttpResponse response = null;
    private HttpRequestLine requestLine = null;

    public HttpProcessor(HttpConnector httpConnector) {

    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
//            response.setHeader();

            parseRequest(input, output);
            parseHeaders(input);

            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
//                processor.process((Request)request, (Response)response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
//                processor.process((Request)request, (Response)response);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseRequest(SocketInputStream input, OutputStream output) throws IOException, ServletException {
        input.readRequestLine(requestLine);
        
    }

    private void parseHeaders(SocketInputStream input) {

    }
}
