package com.mmoscene.h4j.storage;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.mmoscene.h4j.H4J;

import java.sql.Connection;

public class StorePool {

    private BoneCP pool;

    public StorePool() {

    }

    public void load() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            BoneCPConfig config = new BoneCPConfig();

            config.setJdbcUrl(String.format(
                    "jdbc:mysql://%s/%s",
                    H4J.getConfig().get("storage.host"),
                    H4J.getConfig().get("storage.name")));

            config.setMinConnectionsPerPartition(2);
            config.setMaxConnectionsPerPartition(10);

            config.setPartitionCount(1);

            config.setUsername(H4J.getConfig().get("storage.user"));
            config.setPassword(H4J.getConfig().get("storage.password"));

            pool = new BoneCP(config);

            H4J.getLogger(StorePool.class.getName()).info("Storage Pool loaded successfully!");
        } catch (Exception ex) {
            H4J.getLogger(StorePool.class.getName()).error(ex.getMessage());
            System.exit(0);
        }
    }
    public Connection getConnection() {
        try {
            return pool.getConnection();
        } catch (Exception ignored) {
            return null;
        }
    }
}
