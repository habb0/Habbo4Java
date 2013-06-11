package com.mmoscene.h4j.habbohotel.rooms.actors;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;

public class WalkTask implements Runnable {

    private RoomActor actor;

    public WalkTask(RoomActor actor) {
        this.actor = actor;
    }

    @Override
    public void run() {
        while(true) {
            if (actor.isMoving()) {

                if (actor.getCurrentPosition().getX() == actor.getGoalPosition().getX() && actor.getCurrentPosition().getY() == actor.getGoalPosition().getY()) {
                    actor.setMoving(false);
                    actor.getGoalPosition().setX(0);
                    actor.getGoalPosition().setY(0);

                    actor.removeStatus("mv");
                    actor.sendRoomActorStatusMessageComposer();
                } else {
                    Position p = H4J.getHabboHotel().getPathfinder().getNextNode(
                            actor.getCurrentPosition(), actor.getGoalPosition(), actor.getCurrentRoom());

                    int rotation = actor.getCurrentPosition().calculateRotation(p.getX(), p.getY());

                    actor.addStatus("mv", p.getX() + "," + p.getY() + "," + Double.toString(0.0));

                    actor.getCurrentPosition().setRotation(rotation);
                    actor.sendRoomActorStatusMessageComposer();

                    actor.getCurrentPosition().setX(p.getX());
                    actor.getCurrentPosition().setY(p.getY());
                }
                try {
                    Thread.sleep(500);
                } catch(Exception ex) { }
            }
        }
    }
}
