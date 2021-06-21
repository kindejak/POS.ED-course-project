package pos.ui;


import pos.app.App;

import java.awt.print.PrinterException;import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextUI {
    private static App app;
    private static final Logger logger = Logger.getLogger(TextUI.class.getName());


    public static void main(String[] args) {
        FileHandler fh = null;
        try {
            fh = new FileHandler("app.log");
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }

       app = new App();
       boolean cont = true;
       Scanner sc = new Scanner(System.in);
       while (cont){
           System.out.printf(" === MENU === \n");
           System.out.printf("       1. Save and load files \n" +
                   "       2. Products menu\n" +
                   "       3. Orders menu\n" +
                   "       4. Cash register \n" +
                   "       0. Exit \n");
           try {
               int choice = Integer.parseInt(sc.nextLine());
               switch (choice){
                   case 0:
                       cont = false;
                       break;
                   case 1:
                       saveAndLoadMenu();
                       break;
                   case 2:
                       productsMenu();
                       break;
                   case 3:
                       ordersMenu();
                       break;
                   case 4:
                       cashRegisterMenu();
                       break;
                   default:
                       System.out.println("Wrong input.");
               }
           } catch (NumberFormatException e){
               logger.log(Level.INFO, "Wrong input.");
           }

       }
    }


    private static void cashRegisterMenu() {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        System.out.println("Enter EAN codes for products, type \"exit\" to end input, \"print\" to process and print bill:");
        String input = sc.nextLine();
        while (!input.equals("exit")){
            ArrayList<String> idArr = new ArrayList<>();
            while (!input.equals("exit") && !input.equals("print")){
                try {
                    int productId = app.getProductId(input);
                    idArr.add(productId + "");
                } catch (NoSuchElementException e) {
                    System.out.printf("Product of ID %s doesn't exist %n",input);
                }
                input = sc.nextLine();
            }
            int id = 0;
            try {
                id = app.createOrder(idArr);
            } catch (NumberFormatException e) {
                logger.log(Level.INFO, "Non-valid number was entered." + idArr.toString());
            } catch (NoSuchElementException e) {
                logger.log(Level.INFO, "No such product exists.");
            }
            if (input.equals("print")){
                try {
                    app.printReceipt(id);
                } catch (PrinterException e){
                    logger.log(Level.INFO, "Problem with printing bill occurred." + e.getMessage());
                } catch (IOException e) {
                    logger.log(Level.INFO, "Problem with printing bill occurred.");
                } finally {
                    input = sc.nextLine();
                }
            }
        }
    }

    private static void ordersMenu() {
        boolean cont = true;
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (cont){
            System.out.print(" === ORDERS MENU === \n");
            System.out.print("       1. Create order \n" +
                    "       2. Add products to order \n" +
                    "       3. View last order \n" +
                    "       4. View all orders\n" +
                    "       5. Process order\n" +
                    "       0. Exit \n");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                logger.log(Level.INFO, "Wrong input.");
            }
            StringBuilder sb = new StringBuilder();
            ArrayList<String> idArr = new ArrayList<>();
            String productId;
            int id;
            switch (choice){
                case 0:
                    cont = false;
                    break;
                case 1:
                    System.out.println("To create order, enter IDs of products to add them to Order. You can add as many products as you want. If you want to quit enter 0");
                    productId = sc.nextLine();
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
                    break;
                case 2:
                    System.out.println("Order ID:");
                    id = sc.nextInt();
                    System.out.println("To add products to order, enter IDs of products to add them to Order. You can add as many products as you want. If you want to quit enter 0");
                    productId = sc.nextLine();
                    while (!productId.equals("0")){
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
                    break;
                case 3:
                    System.out.println(app.getLastOrder());
                    break;
                case 4:
                    System.out.println(app.getAllOrders());
                    break;
                case 5:
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
                    break;
                default:
                    break;
            }
        }
    }

    private static void productsMenu() {
        boolean cont = true;
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (cont){
            System.out.printf(" === PRODUCTS MENU === \n");
            System.out.printf("       1. Create product \n" +
                    "       2. Edit product \n" +
                    "       3. Remove product \n" +
                    "       4. View product information by scanning EAN \n" +
                    "       5. Search product \n" +
                    "       6. View all products sorted by attribute\n" +
                    "       0. Exit \n");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                logger.log(Level.INFO, "Wrong input.");
            }
            String[] attributeNames =  {"name=","description=","price=","stock=","vat=","ean="};
            StringBuilder sb = new StringBuilder();
            switch (choice){
                case 0:
                    cont = false;
                    break;
                case 1:
                    System.out.println("Enter attributes:");
                    sb.setLength(0);
                    for (int i = 0; i < attributeNames.length; i++) {
                        System.out.print(attributeNames[i]);
                        sb.append(attributeNames[i]);
                        sb.append(sc.nextLine());
                        sb.append(";");
                    }
                    app.createProduct(sb.toString());
                    break;
                case 2:
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
                    break;
                case 3:
                    System.out.println("Remove product - Enter ID of product");
                    try {
                        choice = Integer.parseInt(sc.nextLine());
                        app.removeProduct(choice);
                    } catch (NoSuchElementException e) {
                        logger.log(Level.INFO, "Product of this ID does not exists.");
                    }
                    break;
                case 4:
                    String ean = sc.nextLine();
                    try {
                        System.out.println(app.getProduct(ean));
                    } catch (NoSuchElementException e) {
                        logger.log(Level.INFO, "Product of this ID does not exists.");
                    }
                    break;
                case 5:
                    System.out.println("Enter name of product");
                    System.out.println(app.searchProductByName(sc.nextLine()));
                    break;
                case 6:
                    System.out.println("Enter an attribute to witch should be list sorted. Options are name, price, stock. Default/none is sorted by id ");
                    String attributes = sc.nextLine();
                    System.out.println(app.getAllProducts(attributes));
                default:
                    break;
            }
        }
    }

    private static void saveAndLoadMenu() {

        boolean cont = true;
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (cont){
            System.out.print(" === SAVE/LOAD MENU === \n");
            System.out.print("       1. Save .bin to standard path \n" +
                    "       2. Load .bin from standard path \n" +
                    "       3. Save .json to standard path \n" +
                    "       4. Load .json from standard path \n" +
                    "       0. Exit \n");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                logger.log(Level.INFO, "Wrong input.");
            }
            switch (choice){
                case 0:
                    cont = false;
                    break;
                case 1:
                    try {
                        app.saveFile("products.bin","product");
                        app.saveFile("orders.bin","order");
                    } catch (IOException e) {
                        logger.log(Level.INFO, "Problem with saving file occurred.", e);
                    } catch (ClassNotFoundException e) {
                        logger.log(Level.INFO, "Class tht was supposed to be saved does not exist", e);
                    }
                case 2:
                    try {
                        app.loadFile("products.bin","product");
                        app.loadFile("orders.bin","order");
                    } catch (IOException e) {
                        logger.log(Level.INFO, "Problem with loading file occurred.");
                    } catch (ClassNotFoundException e) {
                        logger.log(Level.INFO, "Class tht was supposed to be saved does not exist");
                    }
                    break;
                case 3:
                    try {
                        app.saveFile("products.json","product");
                        app.saveFile("orders.json","order");
                    } catch (IOException e) {
                        logger.log(Level.INFO, "Problem with saving file occurred.");
                    } catch (ClassNotFoundException e) {
                        logger.log(Level.INFO, "Class tht was supposed to be saved does not exist");
                    }
                    break;
                case 4:
                    try {
                        app.loadFile("products.json","product");
                        app.loadFile("orders.json","order");
                    } catch (IOException e) {
                        logger.log(Level.INFO, "Problem with loading file occurred.");
                    } catch (ClassNotFoundException e) {
                        logger.log(Level.INFO, "Class tht was supposed to be saved does not exist");
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
