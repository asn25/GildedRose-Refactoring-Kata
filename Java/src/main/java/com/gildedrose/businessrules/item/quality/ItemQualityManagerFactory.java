package com.gildedrose.businessrules.item.quality;

// ItemQualityManagerFactory is a factory class to instantiate ItemQualityManager of specific type.
// It uses analog of Singleton pattern, i.e., each type of ItemQualityManager is instantiated only once.
public class ItemQualityManagerFactory {

    private static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
    private static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED_TOKEN_LOWERCASED = "conjured";


    private static final ItemQualityManager SULFURAS_ITEM_QUALITY_MANAGER = new SulfurasQualityManager();
    private static final ItemQualityManager AGED_BRIE_ITEM_QUALITY_MANAGER = new AgedBrieQualityManager();
    private static final ItemQualityManager BACKSTAGE_PASSES_ITEM_QUALITY_MANAGER = new BackstagePassesQualityManager();
    private static final ItemQualityManager CONJURED_ITEM_QUALITY_MANAGER = new ConjuredItemQualityManager();
    private static final ItemQualityManager GENERIC_ITEM_QUALITY_MANAGER = new GenericItemQualityManager();


    // returns quality manager containing business logic for quality calculation
    public static ItemQualityManager getItemQualityManager(String itemName) {
        // TODO: later this "if / switch" on itemName could be replaced with item by-type resolving;
        // (for instance, if "type" field is introduced or support of different subtypes/implementations for Item class)
        if (SULFURAS_ITEM_NAME.equals(itemName))
            return SULFURAS_ITEM_QUALITY_MANAGER;
        else if (AGED_BRIE_ITEM_NAME.equals(itemName))
            return AGED_BRIE_ITEM_QUALITY_MANAGER;
        else if (BACKSTAGE_PASSES_ITEM_NAME.equals(itemName))
            return BACKSTAGE_PASSES_ITEM_QUALITY_MANAGER;
        // check for CONJURED_TOKEN presence case-insensitive
        else if (itemName != null && itemName.toLowerCase().contains(CONJURED_TOKEN_LOWERCASED))
            return CONJURED_ITEM_QUALITY_MANAGER;
        else
            return GENERIC_ITEM_QUALITY_MANAGER;
    }


    // forbid instantiation, use public static factory method only
    private ItemQualityManagerFactory() {
    }
}
