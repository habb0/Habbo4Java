package com.mmoscene.h4j.habbohotel.user.badges;

import com.mmoscene.h4j.H4J;
import org.magicwerk.brownies.collections.GapList;

public class BadgeManager {
    private GapList<BadgeObject> badges;

    private int user;

    public BadgeManager(int id) {
        user = id;

        badges = H4J.getDAO().getUserDAO().getBadgesById(id);
    }

    public GapList<BadgeObject> getBadges() {
        return badges;
    }

    public GapList<BadgeObject> getEquippedBadges() {
        GapList<BadgeObject> e = new GapList<>();

        for(BadgeObject b : badges) {
            if (b.isEquipped()) {
                e.add(b);
            }
        }

        return e;
    }

    public void addBadge(String code) {
        if (hasBadge(code)) {
            return;
        }
    }

    public boolean hasBadge(String code) {
        for(BadgeObject b : badges) {
            if (b.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }
}
