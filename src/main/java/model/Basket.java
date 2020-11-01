package model;

import core.TaxesCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the representation of a basket that has a name and contains a list of items.
 * Its main purpose is to print the receipt for the purchased items.
 */
public class Basket {
    private String name = "";
    private ArrayList<TaxableItem> basketItems = new ArrayList<>();

    /**
     * Instantiates a new Basket.
     *
     * @param name the name
     */
    public Basket(String name) {
        this.name = name;
    }

    /**
     * Gets the basket name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the basket name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a basket basket item.
     *
     * @param purchasableItem the purchasable item
     * @param quantity        the quantity of items of the same type
     * @param imported        tells if the item has been imported ( additional taxes will be applied)
     */
    public void addBasketItem(PurchasableItem purchasableItem, int quantity, boolean imported){
        basketItems.add(new BasketItem(purchasableItem,quantity,imported));
    }

    /**
     * Get basket items list.
     *
     * @return the list of basket items ( it should never be null)
     */
    public List<TaxableItem> getBasketItems(){
        return basketItems;
    }

    /**
     * Clear the list of basket items.
     */
    public void clearBasket(){
        basketItems.clear();
    }

    /**
     * Print the receipt of the current basket by calculating the correct amount of taxes for each basket item.
     *
     * @return the string
     */
    public String printReceipt(){

        StringBuffer output = new StringBuffer();
        double taxesAmount = 0;
        double totalAmount = 0;

        for(TaxableItem item : getBasketItems()){
            double itemTaxes = TaxesCalculator.calculateRoundedTaxesOnItem(item);
            item.setTaxesAmount(itemTaxes);
            taxesAmount += itemTaxes;
            totalAmount += (item.getPrice() + itemTaxes);
            output.append(item).append("\n");
        }
        output.append("Sales Taxes: " + TaxesCalculator.formatValue(taxesAmount)).append("\n");
        output.append("Total: " + TaxesCalculator.formatValue(totalAmount) );
        return output.toString();
    }
}
