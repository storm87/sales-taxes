package model;

import core.TaxesCalculator;

public class Item implements TaxableItem{
    private String description = "generic item";
    private int quantity = 0;
    private double price = 0;
    private boolean imported = false;
    private double taxRate = 10;
    final private double importTaxRate = 5;

    public Item(String description, int quantity, double price, double taxRate, boolean imported) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.imported = imported;
        this.taxRate = taxRate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getTaxesRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public double getImportTaxesRate() {
        return importTaxRate;
    }

    @Override
    public boolean isImported() {
        return imported;
    }

    @Override
    public double getTaxesAmount() {
        return TaxesCalculator.calculateRoundedTaxes(getQuantity(),getPrice(),getTaxesRate(),getImportTaxesRate(),isImported());
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString(){
        return getQuantity() + ( isImported() ? " imported" : "" ) + " " + getDescription() + ": " + TaxesCalculator.toPlainStrinNoTrailingZeroes(getPrice() + getTaxesAmount());
    }
}
