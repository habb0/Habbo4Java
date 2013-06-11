package com.mmoscene.h4j.network.sessions;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.user.User;
import org.jboss.netty.channel.Channel;

public class Session {

    private Channel channel;

    private User user;

    public Session(Channel c) {
        channel = c;
    }

    public String getIP() {
        return channel.getRemoteAddress().toString().split(":")[0].substring(1);
    }

    public void respond(Response response) {
        channel.write(response);
    }

    public User getHabbo() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(String ticket) {
        this.user = H4J.getDAO().getUserDAO().getUserBySSO(ticket);
    }

    public Channel getChannel() {
        return channel;
    }
}
