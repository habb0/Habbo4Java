package com.mmoscene.h4j.communication.composers.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.catalog.CatalogPage;

import java.util.LinkedHashMap;

public class LoadCatalogIndexMessageComposer {
    public static Response compose(LinkedHashMap<Integer, CatalogPage> parents) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("LoadCatalogIndexMessageComposer"));

        response.addBool(true);
        response.addInt(0);
        response.addInt(0);
        response.addInt(-1);
        response.addString("root");
        response.addBool(false);
        response.addBool(false);
        response.addInt(parents.size());

        for(CatalogPage p : parents.values()) {

            LinkedHashMap<Integer, CatalogPage> secondary = H4J.getHabboHotel().getCatalogManager().getSecondaryForId(p.getId());

            response.addBool(true);
            response.addInt(p.getIconColor());
            response.addInt(p.getIconImage());
            response.addInt(p.getId());
            response.addString(p.getTitle().toLowerCase().replace(" ", "_"));
            response.addString(p.getTitle());
            response.addInt(secondary.size());

            for(CatalogPage s : secondary.values()) {
                response.addBool(true);
                response.addInt(s.getIconColor());
                response.addInt(s.getIconImage());
                response.addInt(s.getId());
                response.addString(s.getTitle().toLowerCase().replace(" ", "_"));
                response.addString(s.getTitle());
                response.addInt(0);
            }
        }

        response.addBool(true);

        return response;
    }
}
