package com.mmoscene.h4j.network.codec;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class Decoder extends FrameDecoder {

    @Override
    protected Object decode(ChannelHandlerContext ctx, org.jboss.netty.channel.Channel channel, ChannelBuffer buffer) throws Exception {
        try {
            if (buffer.readableBytes() < 5) {
                return null;
            }

            int index = buffer.readerIndex();

            if (buffer.readByte() == 0x3C) {
                buffer.clear();

                channel.write("<?xml version=\"1.0\"?>\r\n<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n<cross-domain-policy>\r\n<allow-access-from domain=\"*\" to-ports=\"1-31111\" />\r\n</cross-domain-policy>\0").addListener(ChannelFutureListener.CLOSE);

                H4J.getLogger(Decoder.class.getName()).info("Sent Habbo Policy to: " + channel.getRemoteAddress());
                return null;
            } else {
                buffer.readerIndex(index);

                int length = buffer.readInt() - 2;

                if (buffer.readableBytes() < length) {
                    buffer.readerIndex(index);
                    return null;
                }

                return new Request(buffer.readShort(), buffer.readBytes(length));
            }
        } catch (Exception ignored) {
            H4J.getLogger(Decoder.class.getName()).error("Error in packet");
            return null;
        }
    }
}
