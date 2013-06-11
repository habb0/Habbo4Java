package com.mmoscene.h4j.network;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;
import org.jboss.netty.channel.*;

public class NetworkManager extends SimpleChannelUpstreamHandler {
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        H4J.getNetwork().getSessionManager().create(ctx.getChannel());
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        H4J.getNetwork().getSessionManager().kill(ctx.getChannel());
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
        H4J.getNetwork().getSessionManager().kill(ctx.getChannel());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        H4J.getCommunications().parse((Session) ctx.getChannel().getAttachment(), (Request) e.getMessage());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        //
    }
}
