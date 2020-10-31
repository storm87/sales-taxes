package model;

public class ItemFactory {
    public static Item getItem(ItemType type, String description, int quantity, double price, boolean imported){
        switch (type){
            case BOOK:
            case FOOD:
            case MEDICAL:
                return new Item(description,quantity,price,0,imported);
            default:
                return new Item(description,quantity,price,10,imported);
        }
    }
}
