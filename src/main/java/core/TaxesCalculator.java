package core;

import model.Item;
import model.TaxableItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TaxesCalculator {

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

    public static double calculateRoundedTaxes(int quantity, double itemPrice, double taxRate, double importTaxRate, boolean isImported){
        double result = calculateTaxes(quantity,itemPrice,taxRate,importTaxRate,isImported);
        return roundToNearest(result, 0.05);
    }

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

    public static double calculateRoundedTaxesOnItemList(List<TaxableItem> items){
        double result = calculateTaxesOnItemList(items);
        return roundToNearest(result, 0.05);
    }

    public static double calculateTaxesOnItem(TaxableItem good){
        return calculateTaxes(good.getQuantity(),good.getPrice(),good.getTaxesRate(), good.getImportTaxesRate(),good.isImported());
    }

    public static double calculateRoundedTaxesOnItem(TaxableItem good){
        double result = calculateTaxes(good.getQuantity(),good.getPrice(),good.getTaxesRate(), good.getImportTaxesRate(),good.isImported());
        return roundToNearest(result, 0.05);
    }

    public static double roundToNearest(double value, double tick){
        BigDecimal toRound = new BigDecimal(value);
        BigDecimal toTick = new BigDecimal(tick);
        BigDecimal Price2 = toRound.divide(toTick, 9, RoundingMode.HALF_EVEN);
        Price2 = Price2.setScale(0, RoundingMode.HALF_UP).multiply(toTick);
        return Price2.setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    public static String printReceipt(List<TaxableItem> items){
        StringBuffer output = new StringBuffer();
        double taxesAmount = TaxesCalculator.calculateRoundedTaxesOnItemList(items);
        double totalAmount = TaxesCalculator.calculateRawTotalAmountOnItemList(items) + taxesAmount;

        for(TaxableItem item : items){
            output.append(item).append("\n");
        }
        output.append("Sales Taxes: " + taxesAmount).append("\n");
        output.append("Total: " + toPlainStrinNoTrailingZeroes(totalAmount) );
        return output.toString();
    }

    public static String toPlainStrinNoTrailingZeroes(double value){
        return new BigDecimal(value).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
}
