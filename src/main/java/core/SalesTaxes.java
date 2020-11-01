package core;

import model.*;

import java.util.ArrayList;

public class SalesTaxes {
    public static void main( String[] args )
    {
        Basket basket = new Basket("Basket");
        ArrayList<TaxableItem> items = new ArrayList<>();
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.BOOK,"book",12.49d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"music CD",14.99d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.FOOD,"chocolate bar",0.85d),1,false);
        System.out.println( "Printing receipt for basket 1:" );
        System.out.println(basket.printReceipt());

        System.out.println( "" );
        System.out.println( "Printing receipt for basket 2:" );
        basket.clearBasket();
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.FOOD,"box of chocolates",10.00d),1,true);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",47.50d),1,true);
        System.out.println(basket.printReceipt());

        System.out.println( "" );
        System.out.println( "Printing receipt for basket 3:" );
        basket.clearBasket();
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",27.99d),1,true);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",18.99d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.MEDICAL,"packet of headache pills",9.75d),1,false);
        basket.addBasketItem(PurchasableItemFactory.getItem(ItemType.FOOD,"box of chocolates",11.25d),1,true);
        System.out.println(basket.printReceipt());
    }
}
