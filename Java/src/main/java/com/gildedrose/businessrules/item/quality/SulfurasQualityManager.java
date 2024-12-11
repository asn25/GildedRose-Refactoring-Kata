package com.gildedrose.businessrules.item.quality;


// Implements business logic for "Sulfuras" item quality calculation.
public class SulfurasQualityManager implements ItemQualityManager{

    // could be moved to configuration file, if needed
    private static final int SULFURAS_QUALITY = 80;

    // ("Sulfuras" is a legendary item and as such its Quality is 80 and it never alters)
    @Override
    public int calculateNewItemQuality(int sellIn, int quality) {
        return SULFURAS_QUALITY;
    }
}
