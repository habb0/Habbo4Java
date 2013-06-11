package com.mmoscene.h4j.network;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.network.codec.Decoder;
import com.mmoscene.h4j.network.codec.Encoder;
import com.mmoscene.h4j.network.sessions.SessionManager;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Network {

    private NioServerSocketChannelFactory factory = new NioServerSocketChannelFactory(
            Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool(),
            ((Runtime.getRuntime().availableProcessors() * 2) + 1));

    private ServerBootstrap bootstrap = new ServerBootstrap(factory);

    private SessionManager session_manager;

    public Network() {
        bootstrap.getPipeline().addLast("decoder", new Decoder());
        bootstrap.getPipeline().addLast("encoder", new Encoder());
        bootstrap.getPipeline().addLast("handler", new NetworkManager());
        bootstrap.getPipeline().addLast("pipelineExecutor", new ExecutionHandler
                (
                        new OrderedMemoryAwareThreadPoolExecutor(
                                200,
                                1048576,
                                1073741824,
                                100,
                                TimeUnit.MILLISECONDS,
                                Executors.defaultThreadFactory()
                        )
                ));


        session_manager = new SessionManager();
    }

    public void listen() {
        try {
            bootstrap.bind(new InetSocketAddress(H4J.getConfig().get("network.address"), new Integer(H4J.getConfig().get("network.port"))));
            H4J.getLogger(Network.class.getName()).info("Network loaded successfully!");
        } catch (Exception ex) {
            H4J.getLogger(Network.class.getName()).error(ex.getMessage());
            System.exit(0);
        }
    }

    public SessionManager getSessionManager() {
        return session_manager;
    }
}
