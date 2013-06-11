package com.mmoscene.h4j.communication;

import org.jboss.netty.buffer.ChannelBuffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Request implements Cloneable {
    private ChannelBuffer buffer;
    private Short header;

    public Request(Short header, ChannelBuffer buffer) {
        this.header = header;
        this.buffer = (buffer == null || buffer.readableBytes() == 0) ? org.jboss.netty.buffer.ChannelBuffers.EMPTY_BUFFER : buffer;
    }

    public byte[] readBytes(int Amount) {
        return buffer.readBytes(Amount).array();
    }

    public int readShort() {
        return ByteBuffer.wrap(readBytes(2)).asShortBuffer().get();
    }

    public ChannelBuffer readFixed() {

        return buffer.readBytes(readShort());
    }

    public Integer readInt() {
        try {
            return ByteBuffer.wrap(readBytes(4)).asIntBuffer().get();
        } catch (Exception ignored) {
        }
        return 0;
    }

    public String readString() {
        return readFixed().toString(Charset.defaultCharset());
    }

    public boolean readBoolean() {
        return (this.buffer.readableBytes() > 0 && this.buffer.readByte() == (char) 1);
    }

    public String body() {

        String consoleText = buffer.toString(Charset.defaultCharset());

        for (int i = 0; i < 13; i++) {
            consoleText = consoleText.replace(Character.toString((char) i), "{" + i + "}");
        }

        return consoleText;
    }

    public int readHeader() {
        return header;
    }

    public int length() {
        return buffer.readableBytes();
    }
}
