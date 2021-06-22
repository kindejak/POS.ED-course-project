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
    private static final App app = new App();
    private static final UIHelper uiHelper = new UIHelper(app);
    private static final Logger logger = Logger.getLogger(TextUI.class.getName());
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("app.log");
            logger.addHandler(fh);

            boolean cont = true;
            while (cont){
                uiHelper.printMainMenu();
                    int choice = loadInt();
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

            }
        } catch (IOException e){
            logger.log(Level.INFO, "Problem with saving/loading file.");
        } catch (PrinterException e){
            logger.log(Level.INFO, "Problem with printing bill occurred." + e.getMessage());
        } catch (Exception e){
            logger.log(Level.INFO, "Exception occurred." + e.getMessage());
        }
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


    private static void cashRegisterMenu() throws IOException, PrinterException {
        boolean cont = true;
        System.out.println("Enter EAN codes for products, type \"exit\" to end input, \"print\" to process and print bill:");
        String input = sc.nextLine();
        while (!input.equals("exit")) {
            ArrayList<String> idArr = new ArrayList<>();
            while (!input.equals("exit") && !input.equals("print")) {
                int productId = app.getProductId(input);
                idArr.add(productId + "");
                input = sc.nextLine();
            }
            int id = 0;
            id = app.createOrder(idArr);
            if (input.equals("print")) {
                app.printReceipt(id);
            }
        }
    }

    private static void ordersMenu() {
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        while (cont){
            uiHelper.printOrdersMenu();
            int choice = loadInt();
            StringBuilder sb = new StringBuilder();
            ArrayList<String> idArr = new ArrayList<>();
            int id;
            switch (choice){
                case 0:
                    cont = false;
                    break;
                case 1:
                    uiHelper.createOrder();
                    break;
                case 2:
                    uiHelper.addToOrder();
                    break;
                case 3:
                    System.out.println(app.getLastOrder());
                    break;
                case 4:
                    System.out.println(app.getAllOrders());
                    break;
                case 5:
                    uiHelper.processOrder();
                    break;
                default:
                    break;
            }
        }
    }

    private static void productsMenu() {
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        while (cont){
            uiHelper.printProductsMenu();
            int choice = loadInt();
            switch (choice){
                case 0:
                    cont = false;
                    break;
                case 1:
                    uiHelper.createProduct();
                    break;
                case 2:
                    uiHelper.editProduct();
                    break;
                case 3:
                    uiHelper.removeProduct();
                    break;
                case 4:
                    uiHelper.scanEanOfProduct();
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
        while (cont){
            uiHelper.printSaveMenu();
            int choice = loadInt();
            try {
                switch (choice){
                    case 0:
                        cont = false;
                        break;
                    case 1:
                        app.saveFile("products.bin","product");
                        app.saveFile("orders.bin","order");
                    case 2:
                        app.loadFile("products.bin","product");
                        app.loadFile("orders.bin","order");
                        break;
                    case 3:
                        app.saveFile("products.json","product");
                        app.saveFile("orders.json","order");
                        break;
                    case 4:
                        app.loadFile("products.json","product");
                        app.loadFile("orders.json","order");
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                logger.log(Level.INFO, "Problem with loading file occurred.");
            } catch (ClassNotFoundException e) {
                logger.log(Level.INFO, "Class tht was supposed to be saved does not exist");
            }
        }
    }

}
