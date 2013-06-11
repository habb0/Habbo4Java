package com.mmoscene.h4j.habbohotel.catalog;

import org.magicwerk.brownies.collections.GapList;

import java.sql.ResultSet;

public class CatalogPage {
    private int id, parent, order, icon_color, icon_image, rank;

    private CatalogLayout layout;

    private String title, header, description;

    private boolean enabled, use_real_data;

    private GapList<String> real_headers = new GapList<>();
    private GapList<String> real_texts = new GapList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIconColor() {
        return icon_color;
    }

    public void setIconColor(int icon_color) {
        this.icon_color = icon_color;
    }

    public int getIconImage() {
        return icon_image;
    }

    public void setIconImage(int icon_image) {
        this.icon_image = icon_image;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public CatalogLayout getLayout() {
        return layout;
    }

    public void setLayout(CatalogLayout layout) {
        this.layout = layout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isUsingRealData() {
        return use_real_data;
    }

    public void setUsingRealData(boolean use_real_data) {
        this.use_real_data = use_real_data;
    }

    public GapList<String> getHeaders() {
        return real_headers;
    }

    public void setHeaders(GapList<String> real_headers) {
        this.real_headers = real_headers;
    }

    public GapList<String> getTexts() {
        return real_texts;
    }

    public void setTexts(GapList<String> real_texts) {
        this.real_texts = real_texts;
    }
}
