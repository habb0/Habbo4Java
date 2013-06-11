package com.mmoscene.h4j.habbohotel.cache.rules;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.cache.CacheRule;

public class UpdateOnlineCount implements CacheRule {
    @Override
    public void execute() {
        H4J.getDAO().getUserDAO().updateOnline();
    }
}
