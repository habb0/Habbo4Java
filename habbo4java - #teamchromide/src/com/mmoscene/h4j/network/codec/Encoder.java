package com.mmoscene.h4j.network.codec;

import com.mmoscene.h4j.communication.Response;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.nio.charset.Charset;

public class Encoder extends SimpleChannelHandler {

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) {
        try {
            if (e.getMessage() instanceof String) {
                Channels.write(ctx, e.getFuture(), ChannelBuffers.copiedBuffer((String) e.getMessage(), Charset.forName("UTF-8")));
            } else {
                Response response = (Response) e.getMessage();

                Channels.write(ctx, e.getFuture(), response.get());
            }
        } catch (Exception ignored) {}
    }
}
