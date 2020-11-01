package core;

import junit.framework.TestCase;
import model.*;

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
        double result = TaxesCalculator.calculateRoundedTaxesOnItem(new BasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,false));
        assertEquals(result, 1.65d);
        result = TaxesCalculator.calculateRoundedTaxesOnItem(new BasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,true));
        assertEquals(result, 2.45d);
        System.out.println( "Completed taxes calculations on single item!" );
    }

    public void testCalculateRoundedTaxesOnItemList() {
        System.out.println( "Begin testing rounded taxes calculations on list of items!" );
        Basket firstBasket = new Basket("Basket 1");
        firstBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,false);
        firstBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,false);
        firstBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,false);
        firstBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,false);
        double result = TaxesCalculator.calculateRoundedTaxesOnBasket(firstBasket);
        assertEquals(result, 6.6d);
        firstBasket.clearBasket();

        Basket secondBasket = new Basket("Basket 2");
        secondBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),4,false);
        result = TaxesCalculator.calculateRoundedTaxesOnBasket(secondBasket);
        assertEquals(result, 6.6d);
        secondBasket.clearBasket();

        Basket thirdBasket = new Basket("Basket 3");
        thirdBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),4,true);
        result = TaxesCalculator.calculateRoundedTaxesOnBasket(thirdBasket);
        assertEquals(result, 9.9d);

        Basket fourthBasket = new Basket("Basket 4");
        fourthBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,true);
        fourthBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,true);
        fourthBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,true);
        fourthBasket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",16.49d),1,true);
        result = TaxesCalculator.calculateRoundedTaxesOnBasket(fourthBasket);
        assertEquals(result, 9.9d);
        System.out.println( "Completed testing rounded taxes calculations on list of items!" );
    }

    public void testPrintReceipt() {
        System.out.println( "Begin testing receipt printing!" );
        Basket basket = new Basket("Basket");

        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.BOOK,"book",12.49d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",14.99d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.FOOD,"chocolate bar",0.85d),1,false);
        String receipt = basket.printReceipt();
        assertEquals("1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83", receipt);

        System.out.println( "Testing receipt for basket 2:" );
        basket.clearBasket();
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.FOOD,"box of chocolates",10.00d),1,true);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",47.50d),1,true);
        receipt = basket.printReceipt();
        assertEquals("1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15",receipt);

        System.out.println( "Testing receipt for basket 3:" );
        basket.clearBasket();
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",27.99d),1,true);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",18.99d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.MEDICAL,"packet of headache pills",9.75d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.FOOD,"box of chocolates",11.25d),1,true);
        receipt = basket.printReceipt();
        assertEquals("1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.80\n" +
                "Sales Taxes: 6.65\n" +
                "Total: 74.63",receipt);

        System.out.println( "Completed testing receipt printing!" );
    }
}