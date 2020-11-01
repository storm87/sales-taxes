package model;

import core.TaxesCalculator;

/**
 * This class represents a basket item, which is a PurchasableItem wrapper with information about:
 *     - the quantity of items of the same type
 *     - the imported flag
 *     - the taxes amount for the item
 *     - the default import taxes rate (it's the same for all purchasable items)
 *
 * Implements the TaxableItem interface to allow the taxes amount calculation.
 */
public class BasketItem implements TaxableItem{

    /**
     * Default import tax rate. It's applied to all imported purchasable items with no exemptions.
     */
    final private double importTaxesRate = 5;

    /**
     * It's the wrapped purchasable item
     */
    private PurchasableItem purchasableItem = null;

    /**
     * It's the quantity of purchasable items to consider when calculating the taxes amount
     */
    private int quantity = 0;

    /**
     * If the basket items is imported then an additional import tax will be applied.
     */
    private boolean imported = false;

    /**
     * Stores the taxes amount for the wrapped purchasable item
     */
    private double taxesAmount = 0;

    /**
     * Instantiates a new Basket item.
     *
     * @param purchasableItem the purchasable item
     * @param quantity        the quantity
     * @param imported        tells if the item has been imported ( additional taxes will be applied)
     */
    public BasketItem(PurchasableItem purchasableItem, int quantity, boolean imported) {
        this.purchasableItem = purchasableItem;
        this.quantity = quantity;
        this.imported = imported;
    }


    @Override
    public double getTaxesAmount() {
        return taxesAmount;
    }

    @Override
    public double getTaxesRate() {
        return (purchasableItem != null ? purchasableItem.getTaxesRate() : 0);
    }

    @Override
    public double getImportTaxesRate() {
        return importTaxesRate;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return (purchasableItem != null ? purchasableItem.getPrice() : 0);
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean isImported() {
        return imported;
    }

    /**
     * Sets imported.
     *
     * @param imported the imported
     */
    public void setImported(boolean imported) {
        this.imported = imported;
    }

    /**
     * Gets the wrapped purchasable item.
     *
     * @return the purchasable item
     */
    public PurchasableItem getPurchasableItem() {
        return purchasableItem;
    }

    /**
     * Sets the wrapped purchasable item.
     *
     * @param purchasableItem the purchasable item
     */
    public void setPurchasableItem(PurchasableItem purchasableItem) {
        this.purchasableItem = purchasableItem;
    }

    @Override
    public void setTaxesAmount(double taxesAmount) {
        this.taxesAmount = taxesAmount;
    }

    /**
     * Gets the wrapped purchasable item description.
     *
     * @return the description
     */
    public String getDescription() {
        return ( purchasableItem != null ? purchasableItem.getDescription() : "unknown basket purchasableItem");
    }

    /**
     * Prints the wrapped purchasable item's informations in the form:
     *   - "[quantity] [imported (optional)] [description]: [price + taxes amount]"
     *
     * For example:
     *   - "1 generic item: 10.49" if the purchasable item is not imported
     *   - "1 imported generic item: 10.49" if the purchasable item is imported
     *
     * @return
     */
    @Override
    public String toString(){
        return getQuantity() + ( isImported() ? " imported" : "" ) + " " + getDescription() + ": " + TaxesCalculator.formatValue(getPrice() + getTaxesAmount());
    }
}
