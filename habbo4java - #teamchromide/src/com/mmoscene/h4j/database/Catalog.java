package com.mmoscene.h4j.database;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.catalog.CatalogItem;
import com.mmoscene.h4j.habbohotel.catalog.CatalogLayout;
import com.mmoscene.h4j.habbohotel.catalog.CatalogPage;
import gnu.trove.map.hash.THashMap;
import org.magicwerk.brownies.collections.GapList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

public class Catalog {
    public LinkedHashMap<Integer, CatalogPage> getParents() {
        LinkedHashMap<Integer, CatalogPage> parents = new LinkedHashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM server_store_parents ORDER BY `order` ASC")) {

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        parents.put(set.getInt("id"), this.generateAndFillPage(set, true));
                    }

                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Catalog.class.getName()).error(ex.getMessage());
        }

        return parents;
    }

    public LinkedHashMap<Integer, CatalogPage> getSecondary() {
        LinkedHashMap<Integer, CatalogPage> secondary = new LinkedHashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM server_store_pages ORDER BY `order` ASC")) {

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        secondary.put(set.getInt("id"), this.generateAndFillPage(set, false));
                    }

                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Catalog.class.getName()).error(ex.getMessage());
        }

        return secondary;
    }

    public THashMap<Integer, CatalogItem> getItems() {
        THashMap<Integer, CatalogItem> items = new THashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM server_store_items")) {

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        items.put(set.getInt("id"), this.generateAndFillItem(set));
                    }

                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Catalog.class.getName()).error(ex.getMessage());
        }

        return items;
    }

    public CatalogPage generateAndFillPage(ResultSet set, boolean isParent) {
        CatalogPage page = new CatalogPage();

        try {
            page.setId(set.getInt("id"));

            if (isParent) {
                page.setParent(-1);
            } else {
                page.setParent(set.getInt("parent"));
            }

            page.setOrder(set.getInt("order"));
            page.setIconColor(set.getInt("icon_color"));
            page.setIconImage(set.getInt("icon_image"));
            page.setRank(set.getInt("minimum_rank"));
            page.setLayout(CatalogLayout.valueOf(set.getString("layout")));
            page.setTitle(set.getString("title"));
            page.setHeader("");
            page.setDescription("");
            page.setEnabled(set.getInt("enabled") == 1);
        } catch (Exception ignored) {}

        page.setUsingRealData(true);

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM server_store_page_headers WHERE page_id = ? AND is_parent = ?")) {
                statement.setInt(1, page.getId());
                statement.setInt(2, (page.getParent() < 0 ? 1 : 0));

                GapList<String> list = new GapList<>();

                try (ResultSet headers = statement.executeQuery()) {
                    while(headers.next()) {
                        list.add(headers.getString("value"));
                    }
                }

                page.setHeaders(list);
            }
        } catch (Exception ex) {
            H4J.getLogger(Catalog.class.getName()).error(ex.getMessage());
            page.setUsingRealData(false);
            page.setHeaders(new GapList<String>());
            page.setTexts(new GapList<String>());
        }

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM server_store_page_texts WHERE page_id = ? AND is_parent = ?")) {
                statement.setInt(1, page.getId());
                statement.setInt(2, (page.getParent() < 0 ? 1 : 0));

                GapList<String> list = new GapList<>();

                try (ResultSet texts = statement.executeQuery()) {
                    while(texts.next()) {
                        list.add(texts.getString("value"));
                    }
                }

                page.setTexts(list);
            }
        } catch (Exception ex) {
            H4J.getLogger(Catalog.class.getName()).error(ex.getMessage());
            page.setUsingRealData(false);
            page.setHeaders(new GapList<String>());
            page.setTexts(new GapList<String>());
        }

        return page;
    }

    public CatalogItem generateAndFillItem(ResultSet set) {
        CatalogItem item = new CatalogItem();

        try {
            item.setId(set.getInt("id"));
            item.setPage(set.getInt("page"));
            item.setCost(set.getInt("cost"));
            item.setPixelCost(set.getInt("cost_pixels"));
            item.setCurrencyCost(set.getInt("cost_currency"));
            item.setQuantity(set.getInt("quantity"));
            item.setBaseId(set.getInt("unique_id"));
            item.setLtd(set.getInt("ltd") == 1);
            item.setName(set.getString("store_title"));

            item.setLtdStock(set.getInt("ltd_stock"));
            item.setLtdPurchased(set.getInt("ltd_purchased"));
        } catch (Exception ex) {
            H4J.getLogger(Catalog.class.getName()).error(ex.getMessage());
        }

        return item;
    }
}
