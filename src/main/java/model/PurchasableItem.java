package model;

public class PurchasableItem {
    private String description = "generic item";
    private double price = 0;
    private double taxRate = 10;

    public PurchasableItem(String description, double price, double taxRate) {
        this.description = description;
        this.price = price;
        this.taxRate = taxRate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxesRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
