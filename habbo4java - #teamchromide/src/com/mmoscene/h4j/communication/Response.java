package com.mmoscene.h4j.communication;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;

import java.io.IOException;
import java.nio.charset.Charset;

public class Response {
    private int Id;
    private ChannelBufferOutputStream bodystream;
    private ChannelBuffer body;
    public String SimpleResponse;

    public Response init(int id) {
        this.Id = id;
        this.body = ChannelBuffers.dynamicBuffer();
        this.bodystream = new ChannelBufferOutputStream(body);

        SimpleResponse = "";

        try {
            this.bodystream.writeInt(0);
            this.bodystream.writeShort(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Response addString(Object obj) {
        try {
            bodystream.writeUTF(obj.toString());
            SimpleResponse += "[STRING=" + obj + "]";
        } catch (IOException e) {
        }
        return this;
    }

    public void addInt(Integer obj) {
        try {
            bodystream.writeInt(obj);
            SimpleResponse += "[INT=" + obj + "]";
        } catch (IOException e) {
        }
    }

    public Response addShort(int obj) {
        try {
            bodystream.writeShort((short) obj);
            SimpleResponse += "[SHORT=" + obj + "]";
        } catch (IOException e) {
        }
        return this;
    }

    public Response addBool(Boolean obj) {
        try {
            bodystream.writeBoolean(obj);
            SimpleResponse += "[BOOLEAN=" + obj + "]";
        } catch (IOException e) {
        }
        return this;
    }

    public String body() {
        String str = body.toString(Charset.defaultCharset());

        String consoleText = str;

        for (int i = 0; i < 13; i++) {
            consoleText = consoleText.replace(Character.toString((char) i), "{" + i + "}");
        }

        return consoleText;
    }

    public int header() {
        return Id;
    }

    public ChannelBuffer get() {
        body.setInt(0, body.writerIndex() - 4);

        return this.body;
    }
}
