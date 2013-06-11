package com.mmoscene.h4j.communication.composers.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.catalog.CatalogItem;
import com.mmoscene.h4j.habbohotel.catalog.CatalogPage;
import gnu.trove.map.hash.THashMap;

public class LoadCatalogPageMessageComposer {
    public static Response compose(CatalogPage page, THashMap<Integer, CatalogItem> items) {
        Response response = new Response();

        if (!page.isEnabled()) {
            return response;
        }

        response.init(H4J.getHeaders().getInt("LoadCatalogPageMessageComposer"));

        response.addInt(page.getId());

        if (page.isUsingRealData()) {
            response.addString(page.getLayout());

            response.addInt(page.getHeaders().size());

            for(String head : page.getHeaders()) {
                response.addString(head);
            }

            response.addInt(page.getTexts().size());

            for(String text : page.getTexts()) {
                response.addString(text);
            }
        } else {
            H4J.getHabboHotel().getCatalogManager().renderLayout(page, response);
        }

        response.addInt(items.size());

        for(CatalogItem item : items.values()) {
            response.addInt(item.getId());

            response.addString(item.getBase().getPublicName());

            response.addBool(false);

            response.addInt(item.getCost());

            if (item.getPixelCost() > 0) {
                response.addInt(item.getPixelCost());
                response.addInt(0);
            } else if(item.getCurrencyCost() > 0) {
                response.addInt(item.getCurrencyCost());
                response.addInt(0);
            } else {
                response.addInt(0);
                response.addInt(0);
            }

            response.addBool(true);
            response.addInt(1);

            response.addString(item.getBase().getType());
            response.addInt(item.getBase().getSprite());
            response.addString(item.getPresetFlag());

            response.addInt(item.getQuantity());

            response.addBool(item.getLtd());

            if (item.getLtd()) {
                response.addInt(item.getLtdStock());
                response.addInt(item.getLtdPurchased());
            }

            response.addInt(0);
            response.addBool(item.getName().contains("cf_") || !item.getLtd());
        }

        response.addInt(-1);
        response.addBool(false);

        return response;
    }
}
