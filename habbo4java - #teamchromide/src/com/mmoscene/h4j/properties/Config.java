package com.mmoscene.h4j.properties;

import com.mmoscene.h4j.H4J;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private Properties prop;

    public Config() {
        prop = new Properties();
    }

    public void load(String path) {
        try {
            prop.load(new FileInputStream(path));
            H4J.getLogger(Config.class.getName()).info("Configuration loaded successfully!");
        } catch(Exception ignored) {
            H4J.getLogger(Config.class.getName()).error("Cannot find config file at " + path);
            System.exit(0);
        }
    }

    public String get(String key) {
        return prop.getProperty(key).trim();
    }

    public int size() {
        return prop.size();
    }

    public void refresh(String path) {
        try {
            prop.load(new FileInputStream(path));
        } catch(Exception ignored) {
            H4J.getLogger(Config.class.getName()).error("Cannot find config file at " + path);
            System.exit(0);
        }
    }
}
