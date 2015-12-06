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
        if(closed) {
            throw new IOException(sm.getString("requestStream.read.closed"));
        }
        if((length > 0) && (count >= length))
            return -1;
        int b = stream.read();
        if(b >= 0)
            count++;
        return b;
    }

    public void close() throws IOException {
        if(closed) {
            throw new IOException(sm.getString("requestStream.close.closed"));
        }
        if(length > 0) {
            while(count < length) {
                int b = read();
                if(b < 0)
                    break;
            }
        }
        closed = true;
    }

    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        int toRead = len;
        if(length > 0) {
            if(count >= length)
                return -1;
            if((count + len) > length)
                toRead = length - count;
        }
        int actuallyRead = super.read(b, off, toRead);
        return actuallyRead;
    }
}
