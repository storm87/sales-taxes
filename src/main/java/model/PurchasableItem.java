package model;

/**
 * This class represents a purchasable item.
 * It provides informations on the item, such as:
 *   - its description
 *   - its price per unit
 *   - the tax rate to apply
 */
public class PurchasableItem {
    private String description = "generic item";
    private double price = 0;
    /**
     * Default tax rate is 10% but can vary basing on the item type.
     * For example books, food and medical items are exempted.
     */
    private double taxRate = 10;

    /**
     * Instantiates a new Purchasable item.
     *
     * @param description the description
     * @param price       the price
     * @param taxRate     the tax rate
     */
    public PurchasableItem(String description, double price, double taxRate) {
        this.description = description;
        this.price = price;
        this.taxRate = taxRate;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets taxes rate.
     *
     * @return the taxes rate
     */
    public double getTaxesRate() {
        return taxRate;
    }

    /**
     * Sets tax rate.
     *
     * @param taxRate the tax rate
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
