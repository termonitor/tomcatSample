package com.xiaoming.unit3;

import org.apache.catalina.util.Enumerator;
import org.apache.catalina.util.ParameterMap;
import org.apache.catalina.util.RequestUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by panxiaoming on 15/12/4.
 */
public class HttpRequest implements HttpServletRequest {

    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private InputStream input;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    protected HashMap headers = new HashMap();
    protected String authorization = null;
    protected String contextPath = null;
    protected HashMap attributes = new HashMap();
    protected ArrayList cookies = new ArrayList();
    protected ArrayList empty = new ArrayList();
    protected SimpleDateFormat formats[] = {
            new SimpleDateFormat("yyyy MM dd HH:mm:ss"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
            new SimpleDateFormat("HH:mm:ss yyyy-MM-dd")
    };
    protected ParameterMap parameters = null;
    protected boolean parsed = false;
    protected String pathInfo = null;
    protected BufferedReader reader = null;
    protected ServletInputStream stream = null;

    public HttpRequest(InputStream input) {
        this.input = input;
    }

    public void addHeader(String name, int value) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values == null) {
                values = new ArrayList();
                headers.put(name, values);
            }
            values.add(value);
        }
    }

    protected void parseParameters() {
        if (parsed)
            return;
        ParameterMap results = parameters;
        if (results == null) {
            results = new ParameterMap();
        }
        results.setLocked(false);
        String encoding = getCharacterEncoding();
        if (encoding == null)
            encoding = "UTF-8";
        //解析quaryString中的parameter
        String quaryString = getQueryString();
        try {
            RequestUtil.parseParameters(results, quaryString, encoding);
        } catch (UnsupportedEncodingException e) {
            ;
        }
        //解析inputStream中的parameter
        String contentType = getContentType();
        if (contentType == null)
            contentType = "";
        int semicolon = contentType.indexOf(";");
        if (semicolon >= 0) {
            contentType = contentType.substring(0, semicolon).trim();
        } else {
            contentType = contentType.trim();
        }
        if ("POST".equals(getMethod()) && (getContentLength() > 0) && "application/x-www-form-urlencoded".equals(contentType)) {
            try {
                int max = getContentLength();
                int len = 0;
                byte buf[] = new byte[max];
                ServletInputStream is = getInputStream();
                while (len < max) {
                    int next = is.read(buf, len, max - len);
                    if (next < 0)
                        break;
                    len += next;
                }
                is.close();
                if (len < max) {
                    throw new RuntimeException("Content length mismatch");
                }
                RequestUtil.parseParameters(results, buf, encoding);
            } catch (UnsupportedEncodingException e) {
                ;
            } catch (IOException e) {
                throw new RuntimeException("Content read fail");
            }
        }

        //存储最终results
        results.setLocked(true);
        parsed = true;
        parameters = results;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public void addCookie(Cookie cookie) {
        synchronized (cookies) {
            cookies.add(cookie);
        }
    }

    public ServletInputStream createInputStream() throws IOException {
        return (new RequestStream(this));
    }

    public InputStream getStream() {
        return input;
    }

    public void setContentLength(int length) {
        this.contentLength = length;
    }

    public void setContentType(String type) {
        this.contentType = type;
    }

    public void setInet(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setContextPath(String path) {
        if (path == null)
            this.contextPath = "";
        else
            this.contextPath = path;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setRequestedSessionCookie(boolean flag) {
        this.requestedSessionCookie = flag;
    }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public void setRequestedSessionURL(boolean flag) {
        this.requestedSessionURL = flag;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public void setServerName(String name) {
        this.serverName = name;
    }

    public void setServerPort(int port) {
        this.serverPort = port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String getAuthType() {
        return null;
    }

    @Override
    public Cookie[] getCookies() {
        synchronized (cookies) {
            if (cookies.size() < 1)
                return null;
            Cookie results[] = new Cookie[cookies.size()];
            return (Cookie[]) cookies.toArray(results);
        }
    }

    @Override
    public long getDateHeader(String name) {
        String value = getHeader(name);
        if (value == null)
            return -1L;
        value += " ";
        for (int i = 0; i < formats.length; i++) {
            try {
                Date date = formats[i].parse(value);
                return date.getTime();
            } catch (ParseException e) {
                ;
            }
        }
        throw new IllegalArgumentException(value);
    }

    @Override
    public String getHeader(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values != null)
                return (String) values.get(0);
            else
                return null;
        }
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if(values != null)
                return new Enumerator(values);
            else
                return new Enumerator(empty);
        }
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        synchronized (headers) {
            return new Enumerator(headers.entrySet());
        }
    }

    @Override
    public int getIntHeader(String name) {
        String value = getHeader(name);
        if(value == null)
            return -1;
        else
            return Integer.parseInt(value);
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getPathInfo() {
        return pathInfo;
    }

    @Override
    public String getPathTranslated() {
        return null;
    }

    @Override
    public String getContextPath() {
        return contextPath;
    }

    @Override
    public String getQueryString() {
        return queryString;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return requestURI;
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public HttpSession getSession(boolean b) {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return isRequestedSessionIdFromURL();
    }

    @Override
    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String s, String s1) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    @Override
    public Part getPart(String s) throws IOException, ServletException {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        synchronized (attributes) {
            return attributes.get(name);
        }
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        synchronized (attributes) {
            return new Enumerator(attributes.entrySet());
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if(reader != null)
            throw new IllegalStateException("getInputStream has been called");
        if(stream == null)
            stream = createInputStream();
        return stream;
    }

    @Override
    public String getParameter(String name) {
        parseParameters();
        String value[] = (String[])parameters.get(name);
        if(value != null)
            return value[0];
        else
            return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        parseParameters();
        return new Enumerator(parameters.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        parseParameters();
        String value[] = (String[]) parameters.get(name);
        if(value != null)
            return value;
        else
            return null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        parseParameters();
        return parameters;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if(stream != null)
            throw new IllegalStateException("getInputStream has been called.");
        if(reader != null) {
            String encoding = getCharacterEncoding();
            if(encoding == null)
                encoding = "UTF-8";
            InputStreamReader isr = new InputStreamReader(createInputStream(), encoding);
            reader = new BufferedReader(isr);
        }
        return reader;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
