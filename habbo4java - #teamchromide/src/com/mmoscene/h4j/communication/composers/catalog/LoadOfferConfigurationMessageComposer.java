package com.mmoscene.h4j.communication.composers.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class LoadOfferConfigurationMessageComposer {
    public static Response compose() {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("LoadOfferConfigurationMessageComposer"));
        response.addInt(100); //max_offerable
        response.addInt(6); //offers_every_x
        response.addInt(1); //increment_per_offer
        response.addInt(1); //offer_incrementor ??
        response.addInt(2); //exception_length

        response.addInt(40); //exception
        response.addInt(99); //exception

        return response;
    }
}
