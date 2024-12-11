package com.gildedrose.businessrules.item.quality;


// Implements business logic for Conjured Item quality calculation.
public class ConjuredItemQualityManager extends AbstractSellInPhaseDependentItemQualityManager {

    @Override
    protected int calculateNewItemQualityForPositiveSellIn(int sellIn, int quality) {
        // while sell date is not reached, decrement quality by 2 (with limit), i.e. degrades twice as Generic Item
        return getDecrementedQualityWithLimit(quality, 2);
    }

    @Override
    protected int calculateNewQualityForNegativeSellIn(int sellIn, int quality) {
        // after sell date is reached, decrement quality by 4 (with limit), i.e. degrades twice as Generic Item
        return getDecrementedQualityWithLimit(quality, 4);
    }


}
