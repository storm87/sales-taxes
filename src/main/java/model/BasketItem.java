package model;

import core.TaxesCalculator;

public class BasketItem implements TaxableItem{
    private PurchasableItem purchasableItem = null;
    private int quantity = 0;
    private boolean imported = false;
    private double taxesAmount = 0;
    final private double importTaxesRate = 5;

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public PurchasableItem getPurchasableItem() {
        return purchasableItem;
    }

    public void setPurchasableItem(PurchasableItem purchasableItem) {
        this.purchasableItem = purchasableItem;
    }

    public void setTaxesAmount(double taxesAmount) {
        this.taxesAmount = taxesAmount;
    }

    public String getDescription() {
        return ( purchasableItem != null ? purchasableItem.getDescription() : "unknown basket purchasableItem");
    }

    public String toString(){
        return getQuantity() + ( isImported() ? " imported" : "" ) + " " + getDescription() + ": " + TaxesCalculator.formatValue(getPrice() + getTaxesAmount());
    }
}
