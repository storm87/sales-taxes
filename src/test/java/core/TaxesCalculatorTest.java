package core;

import junit.framework.TestCase;
import model.ItemFactory;
import model.ItemType;
import model.TaxableItem;

import java.util.ArrayList;

public class TaxesCalculatorTest extends TestCase {

    public void testCalculateRoundedTaxes() {
        System.out.println( "Begin testing taxes calculations!" );
        double result = TaxesCalculator.calculateRoundedTaxes(1,12.49,0,5,false);
        assertEquals(result, 0.0d);
        result = TaxesCalculator.calculateRoundedTaxes(1,14.99,10,5,false);
        assertEquals(result, 1.5d);
        result = TaxesCalculator.calculateRoundedTaxes(1,0.85,0,5,false);
        assertEquals(result, 0.0d);
        result = TaxesCalculator.calculateRoundedTaxes(1,11.25d,0,5,true);
        assertEquals(result, 0.55d);
        System.out.println( "Completed testing taxes calculations!" );
    }

    public void testRoundToNearest() {
        System.out.println( "Begin testing roundings!" );
        double result = TaxesCalculator.roundToNearest(0,0.05);
        assertEquals(result,0.0d);
        result = TaxesCalculator.roundToNearest(0.0425,0.05);
        assertEquals(result,0.05d);
        result = TaxesCalculator.roundToNearest(0.6245,0.05);
        assertEquals(result,0.6d);
        result = TaxesCalculator.roundToNearest(1,0.05);
        assertEquals(result,1.0d);
        result = TaxesCalculator.roundToNearest(1.649,0.05);
        assertEquals(result,1.65d);
        result = TaxesCalculator.roundToNearest(55.546,0.05);
        assertEquals(result,55.55d);
        result = TaxesCalculator.roundToNearest(232.573,0.05);
        assertEquals(result,232.55d);
        result = TaxesCalculator.roundToNearest(1455.546,0.05);
        assertEquals(result,1455.55d);
        System.out.println( "Completed testing roundings!" );
    }

    public void testCalculateRoundedTaxesOnItem() {
        System.out.println( "Begin testing taxes calculations on single item!" );
        double result = TaxesCalculator.calculateRoundedTaxesOnItem(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,false));
        assertEquals(result, 1.65d);
        result = TaxesCalculator.calculateRoundedTaxesOnItem(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,true));
        assertEquals(result, 2.45d);
        System.out.println( "Completed taxes calculations on single item!" );
    }

    public void testCalculateRoundedTaxesOnItemList() {
        System.out.println( "Begin testing rounded taxes calculations on list of items!" );
        ArrayList<TaxableItem> items = new ArrayList<>();
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,false));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,false));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,false));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,false));
        double result = TaxesCalculator.calculateRoundedTaxesOnItemList(items);
        assertEquals(result, 6.6d);

        items.clear();
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",4,16.49d,false));
        result = TaxesCalculator.calculateRoundedTaxesOnItemList(items);
        assertEquals(result, 6.6d);

        items.clear();
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",4,16.49d,true));
        result = TaxesCalculator.calculateRoundedTaxesOnItemList(items);
        assertEquals(result, 9.9d);

        items.clear();
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,16.49d,true));
        result = TaxesCalculator.calculateRoundedTaxesOnItemList(items);
        assertEquals(result, 9.9d);
        System.out.println( "Completed testing rounded taxes calculations on list of items!" );
    }

    public void testPrintReceipt() {
        System.out.println( "Begin testing receipt printing!" );
        ArrayList<TaxableItem> items = new ArrayList<>();
        items.add(ItemFactory.getItem(ItemType.BOOK,"book",1,12.49d,false));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,14.99d,false));
        items.add(ItemFactory.getItem(ItemType.FOOD,"chocolate bar",1,0.85d,false));
        String receipt = TaxesCalculator.printReceipt(items);
        assertEquals(receipt, "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.5\n" +
                "Total: 29.83");

        System.out.println( "Testing receipt for basket 2:" );
        items.clear();
        items.add(ItemFactory.getItem(ItemType.FOOD,"box of chocolates",1,10.00d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",1,47.50d,true));
        receipt = TaxesCalculator.printReceipt(items);
        assertEquals(receipt, "1 imported box of chocolates: 10.5\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15");

        System.out.println( "Testing receipt for basket 3:" );
        items.clear();
        items.add(ItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",1,27.99d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",1,18.99d,false));
        items.add(ItemFactory.getItem(ItemType.MEDICAL,"packet of headache pills",1,9.75d,false));
        items.add(ItemFactory.getItem(ItemType.FOOD,"box of chocolates",1,11.25d,true));
        receipt = TaxesCalculator.printReceipt(items);
        assertEquals(receipt, "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.8\n" +
                "Sales Taxes: 6.65\n" +
                "Total: 74.63");

        System.out.println( "Completed testing receipt printing!" );
    }
}