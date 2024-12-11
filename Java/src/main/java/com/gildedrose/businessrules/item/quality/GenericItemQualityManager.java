package com.gildedrose.businessrules.item.quality;


// Implements business logic for Generic Item quality calculation.
public class GenericItemQualityManager extends AbstractSellInPhaseDependentItemQualityManager {

    @Override
    protected int calculateNewItemQualityForPositiveSellIn(int sellIn, int quality) {
        // while sell date is not reached, decrement quality by 1 (with limit)
        return getDecrementedQualityWithLimit(quality, 1);
    }

    @Override
    protected int calculateNewQualityForNegativeSellIn(int sellIn, int quality) {
        // after sell date is reached, decrement quality by 2 (with limit)
        return getDecrementedQualityWithLimit(quality, 2);
    }


}
