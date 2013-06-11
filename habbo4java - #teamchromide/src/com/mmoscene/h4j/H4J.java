package com.mmoscene.h4j;

import com.mmoscene.h4j.communication.CommunicationManager;
import com.mmoscene.h4j.database.DAO;
import com.mmoscene.h4j.habbohotel.HabboHotel;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;
import com.mmoscene.h4j.miscellaneous.Version;
import com.mmoscene.h4j.network.Network;
import com.mmoscene.h4j.properties.Config;
import com.mmoscene.h4j.properties.Header;
import com.mmoscene.h4j.storage.StorePool;
import org.apache.log4j.Logger;

import java.math.BigInteger;

public class H4J {

    private static Version version = new Version(0, 0, 4, 7);

    private static Config config = new Config();
    private static Network network = new Network();
    private static Header header = new Header();
    private static StorePool pool = new StorePool();

    private static HabboHotel habbo_hotel;
    private static DAO db_obj;
    private static CommunicationManager communication_manager;

    public static void main(String[] args) {
        getLogger().info("Habbo 4 Java - " + version.string);
        getLogger().info("#TeamChromide Edition");
        getLogger().info("Makarov, Adil, Joe, Zak, Scott, Tren, Mikkel");
        header.load();
        getLogger().info(header.get("ReleaseCode"));
        getLogger().info("");

        config.load("props/server.properties");
        network.listen();
        pool.load();
        db_obj = new DAO();
        communication_manager = new CommunicationManager();

        habbo_hotel = new HabboHotel();
    }

    public static Logger getLogger(String classpath) {
        return Logger.getLogger(classpath);
    }

    private static Logger getLogger() {
        return Logger.getLogger(H4J.class.getName());
    }

    public static Config getConfig() {
        return config;
    }

    public static Network getNetwork() {
        return network;
    }

    public static StorePool getStorage() {
        return pool;
    }

    public static DAO getDAO() {
        return db_obj;
    }

    public static HabboHotel getHabboHotel() {
        return habbo_hotel;
    }

    public static Header getHeaders() {
        return header;
    }

    public static CommunicationManager getCommunications() {
        return communication_manager;
    }
}
