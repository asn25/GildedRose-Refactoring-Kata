package com.gildedrose.businessrules.item.quality;


// AbstractItemQualityManager class introduces quality limits and increment/decrement methods with limit
public abstract class AbstractItemQualityManager implements ItemQualityManager  {

    // could be moved to configuration file, if needed
    protected static final int QUALITY_LIMIT_MAX = 50;

    // could be moved to configuration file, if needed
    protected static final int QUALITY_LIMIT_MIN = 0;


    // get incremented quality;
    // quality can't be higher than 50;
    // (with exception for "Sulfuras" with Quality=80 that is handled by SulfurasQualityManager class)
    protected int getIncrementedQualityWithLimit(int quality, int increment) {
        return ensureQualityInsideLimits(quality + increment);

    }


    // get decremented quality;
    // quality can't be lower than 0
    protected int getDecrementedQualityWithLimit(int quality, int decrement) {
        return ensureQualityInsideLimits(quality - decrement);
    }


    private int ensureQualityInsideLimits(int quality) {
        if (quality < QUALITY_LIMIT_MIN)
            return QUALITY_LIMIT_MIN;
        else if (quality > QUALITY_LIMIT_MAX)
            return QUALITY_LIMIT_MAX;
        return quality;
    }

}
