package model;

import core.TaxesCalculator;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private String name = "";
    private ArrayList<TaxableItem> basketItems = new ArrayList<>();

    public Basket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBasketItem(PurchasableItem purchasableItem, int quantity, boolean imported){
        basketItems.add(new BasketItem(purchasableItem,quantity,imported));
    }

    public List<TaxableItem> getBasketItems(){
        return basketItems;
    }

    public void clearBasket(){
        basketItems.clear();
    }

    public String printReceipt(){

        StringBuffer output = new StringBuffer();
        double taxesAmount = 0;
        double totalAmount = 0;

        for(TaxableItem item : getBasketItems()){
            double itemTaxes = TaxesCalculator.calculateRoundedTaxesOnItem(item);
            item.setTaxesAmount(itemTaxes);
            taxesAmount += itemTaxes;
            totalAmount += (item.getPrice() + itemTaxes);
            output.append(item).append("\n");
        }
        output.append("Sales Taxes: " + TaxesCalculator.formatValue(taxesAmount)).append("\n");
        output.append("Total: " + TaxesCalculator.formatValue(totalAmount) );
        return output.toString();
    }
}
