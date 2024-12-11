package com.gildedrose.businessrules.item.quality;


// Main interface for item quality manager for calculation of new "quality" value.
// New item's "quality" value is dependent on current "sellIn" and "quality" values.
// Business layer logic is done to calculate new values only and separates calculation logic from setting/updating logic,
// to not to introduce dependency into this layer on the structure of items.
public interface ItemQualityManager {

    int calculateNewItemQuality(int sellIn, int quality);

}
