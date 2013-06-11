package com.mmoscene.h4j.habbohotel.cache;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.cache.rules.UpdateOnlineCount;
import com.mmoscene.h4j.habbohotel.cache.rules.UpdateProperties;
import com.mmoscene.h4j.habbohotel.cache.rules.UpdateServerPromotionals;
import org.magicwerk.brownies.collections.GapList;

import java.util.Date;

public class CacheTask implements Runnable {
    GapList<CacheRule> rules = new GapList<>();

    private Thread t;

    public CacheTask() {

        rules.add(new UpdateServerPromotionals());
        //rules.add(new UpdateProperties());
        rules.add(new UpdateOnlineCount());

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while(rules.size() >= 1) {

            Date start = new Date();

            for(CacheRule rule : rules) {
                rule.execute();
            }

            H4J.getLogger(CacheTask.class.getName()).debug("CacheTask took " + (new Date().getTime() - start.getTime()) + "ms to execute!");

            try {
                Thread.sleep(new Integer(H4J.getConfig().get("cache.timeout")));
            } catch (Exception ex) {
                H4J.getLogger(CacheTask.class.getName()).info(ex.getMessage());
            }
        }
    }
}
