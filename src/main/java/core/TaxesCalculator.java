package core;

import model.Basket;
import model.TaxableItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Utility class that exposes useful methods for taxes calculation.
 */
public class TaxesCalculator {

    /**
     * Pattern to format value before printing as string
     */
    private static DecimalFormat df=new DecimalFormat("###,##0.00########");

    /**
     * Calculate raw taxes from an item's informations .
     *
     * @param quantity      the quantity
     * @param itemPrice     the item price
     * @param taxRate       the tax rate
     * @param importTaxRate the import tax rate
     * @param isImported    the is imported
     * @return the raw taxes amount as double
     */
    public static double calculateTaxes(int quantity, double itemPrice, double taxRate, double importTaxRate, boolean isImported){
        double result = 0;
        try{
            double totalAmount = (quantity * itemPrice);
            result += ( totalAmount * taxRate) / 100 ;
            if(isImported){
                result += ( totalAmount * importTaxRate) / 100 ;
            }
        }catch(Exception e){
            System.out.println("Exception while calculating taxes");
        }
        return result;
    }

    /**
     * Calculate rounded taxes from an item's informations .
     *
     * @param quantity      the quantity
     * @param itemPrice     the item price
     * @param taxRate       the tax rate
     * @param importTaxRate the import tax rate
     * @param isImported    the is imported
     * @return the rounded taxes amount as double
     */
    public static double calculateRoundedTaxes(int quantity, double itemPrice, double taxRate, double importTaxRate, boolean isImported){
        double result = calculateTaxes(quantity,itemPrice,taxRate,importTaxRate,isImported);
        return roundToNearest(result, 0.05);
    }

    /**
     * Calculate raw total amount for a list of items.
     *
     * @param items the items
     * @return the raw total amount as double
     */
    public static double calculateRawTotalAmountOnItemList(List<TaxableItem> items){
        double result = 0;
        try{
            for(TaxableItem item : items){
                result += item.getPrice();
            }
        }catch(Exception e){
            System.out.println("Exception while calculating total taxes amount on list of items");
        }
        return result;
    }

    /**
     * Calculate raw taxes for a list of items.
     *
     * @param items the items
     * @return the raw taxes amount as double
     */
    public static double calculateTaxesOnItemList(List<TaxableItem> items){
        double result = 0;
        try{
            for(TaxableItem item : items){
                result += calculateTaxesOnItem(item);
            }
        }catch(Exception e){
            System.out.println("Exception while calculating total taxes amount on list of items");
        }
        return result;
    }

    /**
     * Calculate rounded taxes for a list of items.
     *
     * @param items the items
     * @return the rounded taxes amount as double
     */
    public static double calculateRoundedTaxesOnItemList(List<TaxableItem> items){
        double result = calculateTaxesOnItemList(items);
        return roundToNearest(result, 0.05);
    }

    /**
     * Calculate rounded taxes for a basket.
     *
     * @param basket the basket
     * @return the rounded taxes amount as double
     */
    public static double calculateRoundedTaxesOnBasket(Basket basket){
        double result = calculateTaxesOnItemList(basket.getBasketItems());
        return roundToNearest(result, 0.05);
    }

    /**
     * Calculate raw taxes amount for an item .
     *
     * @param item the item
     * @return the raw taxes amount as double
     */
    public static double calculateTaxesOnItem(TaxableItem item){
        return calculateTaxes(item.getQuantity(),item.getPrice(),item.getTaxesRate(), item.getImportTaxesRate(),item.isImported());
    }

    /**
     * Calculate rounded taxes amount for an item.
     *
     * @param item the item
     * @return the rounded taxes amount as double
     */
    public static double calculateRoundedTaxesOnItem(TaxableItem item){
        double result = calculateTaxes(item.getQuantity(),item.getPrice(),item.getTaxesRate(), item.getImportTaxesRate(),item.isImported());
        return roundToNearest(result, 0.05);
    }

    /**
     * Round a value to the nearest tick.
     *
     * @param value the value
     * @param tick  the tick
     * @return the rounded value
     */
    public static double roundToNearest(double value, double tick){
        BigDecimal toRound = new BigDecimal(value);
        BigDecimal toTick = new BigDecimal(tick);
        BigDecimal Price2 = toRound.divide(toTick, 9, RoundingMode.HALF_EVEN);
        Price2 = Price2.setScale(0, RoundingMode.HALF_UP).multiply(toTick);
        return Price2.setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Format the value accordingly to specific patter.
     *
     * @see: DecimalFormat
     *
     * @param value the value
     * @return the formatted value as string
     */
    public static String formatValue(double value){
        return df.format(new BigDecimal(value).setScale(10, RoundingMode.HALF_UP).doubleValue());
    }
}
