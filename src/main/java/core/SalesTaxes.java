package core;

import model.ItemFactory;
import model.ItemType;
import model.TaxableItem;

import java.util.ArrayList;

public class SalesTaxes {
    public static void main( String[] args )
    {
        ArrayList<TaxableItem> items = new ArrayList<>();
        items.add(ItemFactory.getItem(ItemType.BOOK,"book",1,12.49d,false));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"music CD",1,14.99d,false));
        items.add(ItemFactory.getItem(ItemType.FOOD,"chocolate bar",1,0.85d,false));
        System.out.println( "Printing receipt for basket 1:" );
        System.out.println(TaxesCalculator.printReceipt(items));

        System.out.println( "" );
        System.out.println( "Printing receipt for basket 2:" );
        items.clear();
        items.add(ItemFactory.getItem(ItemType.FOOD,"box of chocolates",1,10.00d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",1,47.50d,true));
        System.out.println(TaxesCalculator.printReceipt(items));

        System.out.println( "" );
        System.out.println( "Printing receipt for basket 3:" );
        items.clear();
        items.add(ItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",1,27.99d,true));
        items.add(ItemFactory.getItem(ItemType.GENERIC,"bottle of perfume",1,18.99d,false));
        items.add(ItemFactory.getItem(ItemType.MEDICAL,"packet of headache pills",1,9.75d,false));
        items.add(ItemFactory.getItem(ItemType.FOOD,"box of chocolates",1,11.25d,true));
        System.out.println(TaxesCalculator.printReceipt(items));
    }
}
