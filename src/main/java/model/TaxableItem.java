package model;

/**
 * The interface TaxableItem.
 * Exposes all necessary informations to calculate taxes on a generic item.
 */
public interface TaxableItem {

    /**
     * Gets taxes rate.
     *
     * @return the taxes rate
     */
    public double getTaxesRate();

    /**
     * Gets import taxes rate.
     *
     * @return the import taxes rate
     */
    public double getImportTaxesRate();

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity();

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice();

    /**
     * Is imported boolean.
     *
     * @return the boolean
     */
    public boolean isImported();

    /**
     * Gets taxes amount.
     *
     * @return the taxes amount
     */
    public double getTaxesAmount();

    /**
     * Sets taxes amount.
     *
     * @param amount the amount
     */
    public void setTaxesAmount(double amount);
}
