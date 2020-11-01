package model;

public class PurchasableItemFactory {
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
