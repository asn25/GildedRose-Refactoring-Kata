package com.gildedrose.businessrules.item.quality;


// This class introduces separate business logic for quality calculation for Positive and Negative "sellIn" phases.
// Inherits increments/decrements with limits functionality from AbstractItemQualityManager
public abstract class AbstractSellInPhaseDependentItemQualityManager extends AbstractItemQualityManager  {

    @Override
    public int calculateNewItemQuality(int sellIn, int quality) {
        if (sellIn >= 0)
            return calculateNewItemQualityForPositiveSellIn(sellIn, quality);
        else
            return calculateNewQualityForNegativeSellIn(sellIn, quality);
    }

    // business logic before required sell date;
    // to be implemented in specific subclasses
    protected abstract int calculateNewItemQualityForPositiveSellIn(int sellIn, int quality);

    // business logic after required sell date (event date);
    // to be implemented in specific subclasses
    protected abstract int calculateNewQualityForNegativeSellIn(int sellIn, int quality);

}
