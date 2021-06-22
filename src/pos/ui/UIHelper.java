package pos.ui;

import pos.app.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UIHelper {

    private static Scanner sc = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(TextUI.class.getName());
    private static final String[] attributeNames =  {"name=","description=","price=","stock=","vat=","ean="};
    private App app;

    public UIHelper(App app) {
        this.app = app;
    }

    public void printMainMenu(){
        System.out.printf(" === MENU === \n");
        System.out.printf("       1. Save and load files \n" +
                "       2. Products menu\n" +
                "       3. Orders menu\n" +
                "       4. Cash register \n" +
                "       0. Exit \n");
    }

    public void printOrdersMenu(){
        System.out.print(" === ORDERS MENU === \n");
        System.out.print("       1. Create order \n" +
                "       2. Add products to order \n" +
                "       3. View last order \n" +
                "       4. View all orders\n" +
                "       5. Process order\n" +
                "       0. Exit \n");
    }

    public void createOrder(){
        ArrayList<String> idArr = new ArrayList<>();
        System.out.println("To create order, enter IDs of products to add them to Order. You can add as many products as you want. If you want to quit enter 0");
        String productId = sc.nextLine();
        while (!productId.equals("0")){
            idArr.add(productId);
            productId = sc.nextLine();
        }
        try {
            System.out.println(app.createOrder(idArr));
        } catch (NumberFormatException e) {
            logger.log(Level.INFO, "Non-valid number was entered.");
        } catch (NoSuchElementException e) {
            logger.log(Level.INFO, "No such product exists.");
        }
        idArr.clear();
    }

    public void addToOrder(){
        ArrayList<String> idArr = new ArrayList<>();
        System.out.println("Order ID:");
        int id = sc.nextInt();
        System.out.println("To add products to order, enter IDs of products to add them to Order. You can add as many products as you want. If you want to quit enter 0");
        String productId = sc.nextLine();
        while (!productId.equals("0")) {
            idArr.add(productId);
            productId = sc.nextLine();
        }
        try {
            System.out.println(app.addToOrder(id,idArr));
        } catch (NumberFormatException e) {
            logger.log(Level.INFO, "Got format exception.", e);
        } catch (NoSuchElementException e) {
            logger.log(Level.INFO, "No such product exists.", e);
        }
        idArr.clear();
    }

    public void processOrder(){
        int idLoad = 0;
        System.out.println("Order id:");
        try {
            idLoad = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e){
            logger.log(Level.INFO, "Wrong input.");
        }
        try {
            if (app.processOrder(idLoad)) {
                System.out.println("Order processed");
            } else {
                System.out.println("Order couldn't be processed");
            }
        } catch (IOException e) {
            logger.log(Level.INFO, "Order couldn't be processed", e);
        }
    }

    public void printProductsMenu(){
        System.out.printf(" === PRODUCTS MENU === \n");
        System.out.printf("       1. Create product \n" +
                "       2. Edit product \n" +
                "       3. Remove product \n" +
                "       4. View product information by scanning EAN \n" +
                "       5. Search product \n" +
                "       6. View all products sorted by attribute\n" +
                "       0. Exit \n");
    }

    public void createProduct(){
        StringBuilder sb = new StringBuilder();
        System.out.println("Enter attributes:");
        sb.setLength(0);
        for (int i = 0; i < attributeNames.length; i++) {
            System.out.print(attributeNames[i]);
            sb.append(attributeNames[i]);
            sb.append(sc.nextLine());
            sb.append(";");
        }
        app.createProduct(sb.toString());
    }

    public void editProduct(){
        StringBuilder sb = new StringBuilder();
        System.out.print("Product ID:");
        int id = sc.nextInt();
        sc.nextLine();
        sb.setLength(0);
        for (int i = 0; i < attributeNames.length; i++) {
            System.out.print(attributeNames[i]);
            sb.append(attributeNames[i]);
            sb.append(sc.nextLine());
            sb.append(";");
        }
        app.editProduct(id,sb.toString());
    }

    public void removeProduct(){
        System.out.println("Remove product - Enter ID of product");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            app.removeProduct(choice);
        } catch (NoSuchElementException e) {
            logger.log(Level.INFO, "Product of this ID does not exists.");
        }
    }

    public void scanEanOfProduct(){
        String ean = sc.nextLine();
        try {
            System.out.println(app.getProduct(ean));
        } catch (NoSuchElementException e) {
            logger.log(Level.INFO, "Product of this ID does not exists.");
        }
    }

    public void printSaveMenu(){
        System.out.print(" === SAVE/LOAD MENU === \n");
        System.out.print("       1. Save .bin to standard path \n" +
                "       2. Load .bin from standard path \n" +
                "       3. Save .json to standard path \n" +
                "       4. Load .json from standard path \n" +
                "       0. Exit \n");
    }

    private static int loadInt(){
        int i = -1;
        try {
            i = sc.nextInt();
        } catch (NumberFormatException e){
            logger.log(Level.INFO, "Wrong input.");
            loadInt();
        }
        sc.nextLine();
        return i;
    }
}
