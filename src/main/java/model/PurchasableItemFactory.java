package model;

/**
 * Factory to abstract the purchasable item creation.
 */
public class PurchasableItemFactory {
    /**
     * Get a purchasable item and sets its tax rate accordingly to its type.
     *
     * @param type        the type
     * @param description the description
     * @param price       the price
     * @return the purchasable item
     */
    public static PurchasableItem getItem(ItemType type, String description, double price){
        switch (type){
            case BOOK:
            case FOOD:
            case MEDICAL:
                return new PurchasableItem(description,price,0);
            default:
                return new PurchasableItem(description,price,10);
        }
    }
}
