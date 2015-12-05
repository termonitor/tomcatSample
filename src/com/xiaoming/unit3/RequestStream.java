package com.xiaoming.unit3;

import com.xiaoming.unit2.Constants;
import org.apache.catalina.util.StringManager;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by panxiaoming on 15/12/5.
 */
public class RequestStream extends ServletInputStream {

    protected boolean closed = false;
    protected int count = 0;
    protected int length = -1;
    protected static StringManager sm = StringManager.getManager(Constants.Package);
    protected InputStream stream = null;

    public RequestStream(HttpRequest request) {
        super();
        closed = false;
        count = 0;
        length = request.getContentLength();
        stream = request.getStream();
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
