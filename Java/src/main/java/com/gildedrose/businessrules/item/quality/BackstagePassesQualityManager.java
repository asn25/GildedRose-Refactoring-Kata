package com.gildedrose.businessrules.item.quality;


// Implements business logic for "BackstagePasses" item quality calculation.
// BackstagePassesQualityManager extends AbstractSellInPhaseDependentItemQualityManager to reuse/inherit
// increment-with-limit functionality and separated logic for Positive/Negative sellIn phases.
public class BackstagePassesQualityManager extends AbstractSellInPhaseDependentItemQualityManager {


    // business logic for quality calculation when approaching required sell date.
    // ("Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;")
    // ("Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days")
    @Override
    protected int calculateNewItemQualityForPositiveSellIn(int sellIn, int quality) {
        if (sellIn < 6)
            // increment by 3 (with limit)
            return getIncrementedQualityWithLimit(quality, 3);
        else if (sellIn < 11)
            // increment by 2 (with limit)
            return getIncrementedQualityWithLimit(quality, 2);
        else
            // increment by 1 (with limit)
            return getIncrementedQualityWithLimit(quality, 1);

    }

    // business logic for quality calculation after required sell date was reached (after event);
    // ("Quality drops to 0 after the concert")
    @Override
    protected int calculateNewQualityForNegativeSellIn(int sellIn, int quality) {
        return 0;
    }
}
