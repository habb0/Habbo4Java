package com.mmoscene.h4j.communication.composers.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.catalog.CatalogItem;

public class PurchaseFromCatalogMessageComposer {
    public static Response compose(CatalogItem item) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("PurchaseFromCatalogMessageComposer"));
        response.addInt(item.getId());
        response.addString(item.getName());
        response.addBool(false);
        response.addInt(item.getCost());
        response.addInt(0);
        response.addInt(0);
        response.addBool(false);
        response.addInt(0);
        response.addInt(0);
        response.addBool(false);

        return response;
    }
}
