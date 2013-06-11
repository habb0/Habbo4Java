package com.mmoscene.h4j.network.sessions;

import com.mmoscene.h4j.H4J;
import org.jboss.netty.channel.Channel;
import org.magicwerk.brownies.collections.GapList;

public class SessionManager {

    private GapList<Channel> channels = new GapList<>();

    public Session create(Channel c) {
        Session session = new Session(c);

        c.setAttachment(session);

        channels.add(c);

        if (H4J.getConfig().get("log.connections").equals("true")) {
            H4J.getLogger(SessionManager.class.getName()).info("Opened Connection with " + session.getIP());
        }

        return session;
    }

    public void kill(Channel c) {
        channels.remove(c);

        if (H4J.getConfig().get("log.connections").equals("true")) {
            H4J.getLogger(SessionManager.class.getName()).info("Closed Connection with " + ((Session) c.getAttachment()).getIP());
        }

        ((Session) c.getAttachment()).getHabbo().getMessenger().setUpdate(false);
        c.disconnect();
    }

    public boolean getOnlineStatusById(int id) {

        for(Channel c : channels) {
            if (((Session)c.getAttachment()).getHabbo().getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Session getSessionById(int id) {

        for(Channel c : channels) {
            if (((Session) c.getAttachment()).getHabbo().getId() == id) {
                return (Session) c.getAttachment();
            }
        }
        return null;
    }

    public int size() {
        return channels.size();
    }
}
