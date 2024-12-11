package com.gildedrose.businessrules.item.quality;


// Implements business logic for "Aged Brie" item quality calculation.
// ("Aged Brie" actually increases in Quality the older it gets")
// AgedBrieQualityManager extends AbstractItemQualityManager to reuse/inherit increment-with-limit functionality.
public class AgedBrieQualityManager extends AbstractItemQualityManager{

    // always increment quality by 1 (with limit)
    @Override
    public int calculateNewItemQuality(int sellIn, int quality) {
        return getIncrementedQualityWithLimit(quality, 1);
    }
}
