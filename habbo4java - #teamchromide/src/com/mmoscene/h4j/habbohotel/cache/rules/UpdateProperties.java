package com.mmoscene.h4j.habbohotel.cache.rules;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.cache.CacheRule;

public class UpdateProperties implements CacheRule {
    @Override
    public void execute() {
       /* H4J.getConfig().load("props/server.properties");
        H4J.getHeaders().load(); */
    }
}
