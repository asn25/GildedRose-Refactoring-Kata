package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateFooWithSellIn0AndQuantity0_shouldDecrementSellInAndKeepQuality0() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // keeps name
        assertEquals(app.items[0].name, "foo");
        // this is Generic item, sellIn is decremented
        assertEquals(app.items[0].sellIn, -1);
        // this is Generic item, quality can't be decremented lower than 0
        assertEquals(app.items[0].quality, 0);
    }


// ============= update of collection =================


    @Test
    void updateFiveItemsCollection_shouldKeepAllItemsWithSameNamesAndOrder() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 5, 1),
            new Item("Aged Brie", 3, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20),
            new Item("some conjured +5 item", -4, 40),
            new Item("aaa1 item", -20, 50),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items.length);
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals("Aged Brie", app.items[1].name);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[2].name);
        assertEquals("some conjured +5 item", app.items[3].name);
        assertEquals("aaa1 item", app.items[4].name);
    }


    @Test
    void updateEmptyCollection_shouldRunWithoutExceptionAndKeepEmptyCollection() {
        Item[] items = new Item[] { };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items.length);
    }


// ================  Sulfuras ===========================


    @Test
    void updateSulfurasWithPositiveSellInAndSmallQuality1_shouldKeepSellInAndSetQualityTo80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void updateSulfurasWithPositiveSellInAndQuality80_shouldKeepSellInAndSetQualityTo80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(5, app.items[0].sellIn, 5);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void updateSulfurasWithNegativeSellInAndForbiddenQuality90_shouldKeepSellInAndSetQualityTo80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -2, 90) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void updateSulfurasWithNegativeSellInAndForbiddenQualityMinus3_shouldKeepSellInAndSetQualityTo80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -22, -3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(-22, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }


// ================  Aged Brie ===========================


    @Test
    void updateAgedBrieWithPositiveSellInAndSmallQuality1_shouldDecrementSellInAndIncrementQualityBy1() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void updateAgedBrieWithPositiveSellInAndMaxQuality50_shouldDecrementSellInAndKeepMaxQuality50() {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void updateAgedBrieWithNegativeSellInSmallQuality10_shouldDecrementSellInAndIncrementQualityBy1() {
        Item[] items = new Item[] { new Item("Aged Brie", -4, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-5, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }


    @Test
    void updateAgedBrieWithPositiveSellInAndForbiddenQuality55_shouldDecrementSellInAndSetQualityTo50() {
        Item[] items = new Item[] { new Item("Aged Brie", 4, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void updateAgedBrieWithPositiveSellInAndForbiddenQualityMinus1_shouldDecrementSellInAndSetQualityTo0() {
        Item[] items = new Item[] { new Item("Aged Brie", 4, -1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void updateAgedBrieWithPositiveSellInAndForbiddenQualityMinus20_shouldDecrementSellInAndSetQualityTo0() {
        Item[] items = new Item[] { new Item("Aged Brie", 4, -20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }


// ================  Backstage Pass ===========================


    @Test
    void updateBackstagePassWithPositiveSellIn20AndQuality5_shouldDecrementSellInAndIncrementQualityBy1() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 20, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void updateBackstagePassWithPositiveSellIn8AndQuality5_shouldDecrementSellInAndIncrementQualityBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 8, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(7, app.items[0].sellIn);
        assertEquals(7, app.items[0].quality);
    }

    @Test
    void updateBackstagePassWithPositiveSellIn5AndQuality1_shouldDecrementSellInAndIncrementQualityBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }


    @Test
    void updateBackstagePassWithPositiveSellIn1AndQuality4_shouldDecrementSellInAndIncrementQualityBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 1, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(app.items[0].name, "Backstage passes to a TAFKAL80ETC concert");
        assertEquals(0, app.items[0].sellIn);
        assertEquals(7, app.items[0].quality);
    }

    @Test
    void updateBackstagePassWithSellIn0AndQuality4_shouldDecrementSellInAndSetQualityTo0() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        // since decrement of "sellIn" happens before "quality" update, this will be processed as "Negative" sellIn phase
        assertEquals(0, app.items[0].quality );
    }

    @Test
    void updateBackstagePassWithNegativeSellInMinus3AndQuality15_shouldDecrementSellInAndSetQualityTo0() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -3, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-4, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality );
    }


// ================  Generic item ===========================


    @Test
    void updateGenericItemWithPositiveSellIn20AndQuality5_shouldDecrementSellInAndDecrementQualityBy1() {
        Item[] items = new Item[] { new Item("Some usual item", 20, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some usual item", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void updateGenericItemWithSellIn0AndQuality5_shouldDecrementSellInAndDecrementQualityBy2() {
        Item[] items = new Item[] { new Item("Some usual item", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some usual item", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        // since decrement of "sellIn" happens before "quality" update, this will be processed as "Negative" sellIn phase
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void updateGenericItemWithNegativeSellInMinus10AndQuality5_shouldDecrementSellInAndDecrementQualityBy2() {
        Item[] items = new Item[] { new Item("Some usual item", -10, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some usual item", app.items[0].name);
        assertEquals(-11, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void updateGenericItemWithPositiveSellIn20AndQuality0_shouldDecrementSellInAndKeepQuality0() {
        Item[] items = new Item[] { new Item("Some usual item", 20, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some usual item", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void updateGenericItemWithPositiveSellIn20AndForbiddenQualityMinus5_shouldDecrementSellInAndSetQualityTo0() {
        Item[] items = new Item[] { new Item("Some usual item", 20, -5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some usual item", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }


// ================  Conjured item ===========================


    // degrades twice as Generic item
    @Test
    void updateConjuredItemWithPositiveSellIn20AndQuality5_shouldDecrementSellInAndDecrementQualityBy2() {
        Item[] items = new Item[] { new Item("Some conjured item", 20, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some conjured item", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    // degrades twice as Generic item
    @Test
    void updateConjuredItemWithSellIn0AndQuality5_shouldDecrementSellInAndDecrementQualityBy4() {
        Item[] items = new Item[] { new Item("Some +4 Conjured item", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some +4 Conjured item", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        // since decrement of "sellIn" happens before "quality" update, this will be processed as "Negative" sellIn phase
        assertEquals(1, app.items[0].quality);
    }

    // degrades twice as Generic item
    @Test
    void updateConjuredItemWithNegativeSellInMinus10AndQuality5_shouldDecrementSellInAndDecrementQualityBy4() {
        Item[] items = new Item[] { new Item("Some conjuredhardly item", -10, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some conjuredhardly item", app.items[0].name);
        assertEquals(-11, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void updateConjuredItemWithPositiveSellIn20AndQuality0_shouldDecrementSellInAndKeepQuality0() {
        Item[] items = new Item[] { new Item("Some abc-conjured item", 20, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some abc-conjured item", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void updateConjuredItemWithPositiveSellIn20AndForbiddenQualityMinus5_shouldDecrementSellInAndSetQualityTo0() {
        Item[] items = new Item[] { new Item("Some CONJURED item", 20, -5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Some CONJURED item", app.items[0].name);
        assertEquals(19, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }


}
