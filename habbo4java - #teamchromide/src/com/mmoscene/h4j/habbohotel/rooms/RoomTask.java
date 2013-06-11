package com.mmoscene.h4j.habbohotel.rooms;

import com.mmoscene.h4j.H4J;

public class RoomTask implements Runnable {
    private Thread t;

    public RoomTask() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while(true) {
            for(Room r : H4J.getHabboHotel().getRoomManager().getLivingAsList()) {
                r.onCycle();
            }

            try {
                Thread.sleep(500);
            } catch(Exception ignored) {}
        }
    }
}
