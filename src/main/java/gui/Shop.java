package gui;

import core.TaxesCalculator;
import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

/**
 * This class creates a basic GUI that can be used to create a basket by adding items and calculates the amount of taxes
 * by printing a receipt.
 */
public class Shop extends JFrame {

    final private String emptyReceiptAreaText = "\n-------Basket content---------\n";
    final private String defaultItemDescription = "Generic Item";
    final private String defaultItemPrice = "1.00";

    private Basket basket = new Basket("My Basket");

    /**
     * The Receipt area.
     */
    JTextArea receiptArea = new JTextArea(emptyReceiptAreaText);
    /**
     * The Price text field.
     */
    JFormattedTextField priceTextField = new JFormattedTextField(new DecimalFormat("###,##0.00########"));
    /**
     * The Quantity combo box.
     */
    JComboBox<Integer> quantityComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10});
    /**
     * The Description text field.
     */
    JTextField descriptionTextField = new JTextField(defaultItemDescription);
    /**
     * The Item type combo box.
     */
    JComboBox<ItemType> itemTypeComboBox = new JComboBox<>(ItemType.values());
    /**
     * The Is imported check box.
     */
    JCheckBox isImportedCheckBox = new JCheckBox("Imported");
    /**
     * The Add item button.
     */
    JButton addItemButton = new JButton("");
    /**
     * The Purchase button.
     */
    JButton purchaseButton = new JButton("Confirm order");
    /**
     * The Clear basket button.
     */
    JButton clearBasketButton = new JButton("Clear basket");

    /**
     * Instantiates a new Shop.
     */
    public Shop(){
        super("My Shop");
    }

    /**
     * Init gui.
     */
    public void initGUI(){
        JFrame frame = new JFrame("Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,400);
        frame.setLocation(200,200);
        frame.getContentPane().setLayout(new BorderLayout());

        receiptArea.setEditable(false);
        priceTextField.setText(defaultItemPrice);

        addItemButton.setAction(new AbstractAction("+") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToBasket();
            }
        });

        priceTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            }
            public void removeUpdate(DocumentEvent e) {
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                boolean isNumeric = isNumeric(priceTextField.getText());
                addItemButton.setEnabled(isNumeric);
                if (!isNumeric) {
                    JOptionPane.showMessageDialog(null,"Error: Please insert a number", "Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel newItemPanel = new JPanel(new GridLayout());
        newItemPanel.add(new JLabel("Quantity:"));
        newItemPanel.add(quantityComboBox);
        newItemPanel.add(new JLabel("Description:"));
        newItemPanel.add(descriptionTextField);
        newItemPanel.add(new JLabel("Price:"));
        newItemPanel.add(priceTextField);
        newItemPanel.add(new JLabel("Type:"));
        newItemPanel.add(itemTypeComboBox);
        newItemPanel.add(isImportedCheckBox);
        newItemPanel.add(addItemButton);

        frame.getContentPane().add(newItemPanel,BorderLayout.BEFORE_FIRST_LINE);
        frame.getContentPane().add(new JScrollPane(receiptArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER),BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        purchaseButton.setAction(new AbstractAction("Confirm order") {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmOrder();
            }
        });

        clearBasketButton.setAction(new AbstractAction("Clear basket") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBasket();
            }
        });
        buttonsPanel.add(purchaseButton);
        buttonsPanel.add(clearBasketButton);

        frame.getContentPane().add(buttonsPanel,BorderLayout.PAGE_END);
        frame.setVisible(true);
    }

    private void confirmOrder() {
        receiptArea.append("\n\n-------Order details---------\n");
        receiptArea.append(basket.printReceipt());
        basket.clearBasket();
    }

    private void clearBasket() {
        basket.clearBasket();
        receiptArea.setText(emptyReceiptAreaText);
    }

    private void addItemToBasket() {
        int quantity = (int) quantityComboBox.getSelectedItem();
        boolean imported = isImportedCheckBox.isSelected();
        PurchasableItem purchasedItem = PurchasableItemFactory.getItem((ItemType) itemTypeComboBox.getSelectedItem(),descriptionTextField.getText(),Double.parseDouble(priceTextField.getText()));
        if(purchasedItem != null) {
            if(basket.getBasketItems().isEmpty()){
                receiptArea.setText(emptyReceiptAreaText);
            }
            basket.addBasketItem(purchasedItem, quantity, imported);
            receiptArea.append(Integer.toString(quantity));
            receiptArea.append((imported ? " imported ": " "));
            receiptArea.append(purchasedItem.getDescription());
            receiptArea.append(" at: ");
            receiptArea.append(TaxesCalculator.formatValue(purchasedItem.getPrice()));
            receiptArea.append("\n");
        }
        resetInputFields();
    }

    private void resetInputFields(){
        quantityComboBox.setSelectedIndex(0);
        descriptionTextField.setText(defaultItemDescription);
        priceTextField.setText(defaultItemPrice);
        itemTypeComboBox.setSelectedItem(ItemType.GENERIC);
        isImportedCheckBox.setSelected(false);
    }

    /**
     * Utility method to check if a string is representing a number.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
