package model;

public interface TaxableItem {

    public double getTaxesRate();
    public double getImportTaxesRate();
    public int getQuantity();
    public double getPrice();
    public boolean isImported();
    public double getTaxesAmount();
}
