package com.gildedrose;

import com.gildedrose.businessrules.item.quality.ItemQualityManager;
import com.gildedrose.businessrules.item.quality.ItemQualityManagerFactory;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    // General comment: refactoring is done to eliminate "Coding-by-exception" anti-pattern (i.e., "ladder of if-s") in the original version.
    // It's achieved by using of Polymorphism and Indirection GRASP patterns and making a set of small fine-grained rules/interfaces/implementations.
    // In particular, in this implementation it's proposed to separate "sellIn" field update from "quality" field update,
    // considering that "quality" field is dependent on "sellIn" field.
    // Also, using separation of concerns: business rules layer (calculation logic) is separated from updating logic (and "Item" class structure),
    // i.e., if "Item" class is reworked/redesigned in the future, we'll not need to change the business rules layer.
    // ===================


    // TODO: since "updateQuality" method updates not only "quality", but also "sellIn", consider method renaming or additional refactoring.
    // For now, method's name is left as "updateQuality()" for compatibility.
    // TODO: clarification is needed on "sellIn" field  and "quality" field update sequence, since "quality"-field is dependent on "sellIn"-field.
    // Comment: in the original version decrement of "sellIn"-field was built-in inside of "quality"-field update logic:
    // block-1 - do 1st update of "quality"; block-2 - do decrement of "sellIn"; block-3 - do 2nd update of "quality" with checks whether "sellIn" became negative.
    // It's not clear whether it's required by business logic to do in this way.
    // Current implementation is done with separation of "sellIn"-field update and dependent "quality"-field update, and "sellIn"-field update goes first.
    // Separation of "sellIn"-field update and "quality"-field update makes updating logic more clear.
    // If exactly original logic is needed (with first update of "quality", then decrement of "sellIn", then additional "quality" update with check for negativity of "sellIn" on the same day/iteration),
    // it's possible to replicate it too.
    // TODO: clarification for "Aged Brie" requirements is needed.
    // Based on testing of the original version, it's seen that after "sellIn" becomes negative, it increases its "quality" by 2 for "Aged Brie".
    // But here is how it's stated in the requirements: "'Aged Brie' actually increases in Quality the older it gets".
    // Regarding changing "quality" by 2, it's said for generic items: "Once the sell by date has passed, Quality degrades twice as fast".
    // I.e., it says "degrades twice", not that "Aged Brie" should "increase in quality twice as fast".
    // If needed, it's easy to make it for "Aged Brie" the same logic as in the original version - need to inherit rule from AbstractSellInPhaseDependentItemQualityManager class
    // (analogously as it is done for BackstagePassesQualityManager).
    // In the current implementation "Aged Brie" is increasing "quality" always by 1, regardless of negative "sellIn".
    // TODO: consider usage of Stream API for more clear/concise transformation pipelines.
    // TODO: consider usage of generic collections like "List<Item>" instead of arrays like "List[]".
    // TODO: consider elimination of public fields (and setters) in "Item" class and usage, for instance, of Lombok @Builder pattern.

    public void updateQuality() {

        final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";

        for (Item item : items) {
            // 1 - decrement "sellIn" field
            // TODO: need to clarify whether "sellIn" decrement for "Sulfuras" should be skipped.
            // It's skipped in the original version, but it's not stated explicitly in the requirements.
            // (it's only said that "'Sulfuras', being a legendary item, never has to be sold").
            // TODO: if it should be skipped, then we can introduce ItemSellInManager and its implementations (analogously with ItemQualityManager).
            // For now, using simple if-based skip for "Sulfuras", to make it compatible with the original version.
            if (!SULFURAS_ITEM_NAME.equals(item.name))
                item.sellIn -= 1;

            // 2 - calculate "quality" field that depends on "sellIn" field
            ItemQualityManager itemQualityManager = ItemQualityManagerFactory.getItemQualityManager(item.name);
            item.quality = itemQualityManager.calculateNewItemQuality(item.sellIn, item.quality);

        }

    }
}
