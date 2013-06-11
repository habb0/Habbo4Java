package com.mmoscene.h4j.habbohotel.cache.rules;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.cache.CacheRule;

public class UpdateServerPromotionals implements CacheRule {
    @Override
    public void execute() {
        try {
            H4J.getHabboHotel().reloadPromos();
        } catch(Exception ignored) {}
    }
}
