package com.mmoscene.h4j.habbohotel.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import gnu.trove.map.hash.THashMap;

import java.util.LinkedHashMap;

public class CatalogManager {
    private LinkedHashMap<Integer, CatalogPage> parents = new LinkedHashMap<>();
    private LinkedHashMap<Integer, CatalogPage> secondary = new LinkedHashMap<>();

    private THashMap<Integer, CatalogItem> items = new THashMap<>();

    public CatalogManager() {
        parents = H4J.getDAO().getCatalogDAO().getParents();
        H4J.getLogger(CatalogManager.class.getName()).info(String.format("Loaded %d Catalog Parents successfully!", parents.size()));

        secondary = H4J.getDAO().getCatalogDAO().getSecondary();
        H4J.getLogger(CatalogManager.class.getName()).info(String.format("Loaded %d Catalog Pages successfully!", secondary.size()));

        items = H4J.getDAO().getCatalogDAO().getItems();
        H4J.getLogger(CatalogManager.class.getName()).info(String.format("Loaded %d Catalog Items successfully!", items.size()));
    }

    public LinkedHashMap<Integer, CatalogPage> getParents() {
        return parents;
    }

    public LinkedHashMap<Integer, CatalogPage> getSecondary() {
        return secondary;
    }

    public LinkedHashMap<Integer, CatalogPage> getParentsForRank(int Rank) {
        LinkedHashMap<Integer, CatalogPage> parents = new LinkedHashMap<>();

        for(CatalogPage page : this.parents.values()) {
            if (page.getRank() <= Rank) {
                parents.put(page.getId(), page);
            }
        }

        return parents;
    }

    public LinkedHashMap<Integer, CatalogPage> getSecondaryForId(int Id) {
        LinkedHashMap<Integer, CatalogPage> secondary = new LinkedHashMap<>();

        for(CatalogPage page : this.secondary.values()) {
            if (page.getParent() == Id) {
                secondary.put(page.getId(), page);
            }
        }

        return secondary;
    }

    public CatalogPage getPageById(int Id) {
        if (parents.containsKey(Id)) {
            return parents.get(Id);
        } else {
            return secondary.get(Id);
        }
    }

    public THashMap<Integer, CatalogItem> getItemsForPage(int page) {
        THashMap<Integer, CatalogItem> list = new THashMap<>();

        for(CatalogItem item : this.items.values()) {
            if (item.getPage() == page) {
                list.put(item.getId(), item);
            }
        }

        return list;
    }

    public CatalogItem getItemById(int id) {
        CatalogItem i = null;

        for(CatalogItem item : this.items.values()) {
            if (item.getId() == id) {
                i = item;
            }
        }

        return i;
    }

    public void renderLayout(CatalogPage page, Response response) {
        switch(page.getLayout().toString()) {
            case "frontpage":
                response.addString("frontpage3");
                response.addInt(3);
                response.addString(page.getHeader());
                response.addString("");
                response.addString("");
                response.addInt(11);
                response.addString(""); //Special
                response.addString(page.getDescription()); //Text1
                response.addString("");
                response.addString(""); //Text2
                response.addString(""); //Details
                response.addString(""); //Tease2
                response.addString("Rares");
                response.addString("#FEFEFE");
                response.addString("#FEFEFE");
                response.addString("Click here for more info..");
                response.addString("magic.credits");
                break;

            case "spaces":
                response.addString("spaces_new");
                response.addInt(1);
                response.addString(page.getHeader());
                response.addInt(1);
                response.addString(page.getHeader()); //Supposed to be Text1 but who needs it?
                break;

            case "trophies":
                response.addString("trophies");
                response.addInt(1);
                response.addString(page.getHeader());
                response.addInt(2);
                response.addString(page.getDescription());
                response.addString(""); //Details
                response.addInt(0);
                response.addInt(0);
                response.addInt(-1);
                break;

            case "pets":
                response.addString("pets");
                response.addInt(2);
                response.addString(page.getHeader());
                response.addString(page.getDescription());
                response.addInt(4);
                response.addString(""); //Text1
                response.addString("Give a name:");
                response.addString("Pick a color:");
                response.addString("Pick a race:");
                response.addInt(0);
                response.addInt(0);
                response.addInt(-1);
                break;

            default:
                response.addString(page.getLayout());
                response.addInt(3);
                response.addString(page.getHeader());
                response.addString(""); //desc?
                response.addString(""); //Special
                response.addInt(3);
                response.addString(page.getDescription()); //Text1
                response.addString(""); //Details
                response.addString(""); //Teaser2
                break;
        }
    }
}
