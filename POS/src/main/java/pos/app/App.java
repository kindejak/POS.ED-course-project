package pos.app;


import pos.utils.AttributesParser;
import pos.utils.MainPosInterface;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class App implements MainPosInterface {

    final static int FIRST_ID_ALLOWED = 1000;
    final static String PATH = "data" + File.separator;
    private ArrayList<Order> ordersArr;
    private ArrayList<Product> productsArr;

    public App() {
        ordersArr = new ArrayList<>();
        productsArr = new ArrayList<>();
    }


    /**
     * @param fileName Absolute path in form of string
     */
    @Override
    public void loadFile(String fileName,String type) throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler(PATH + fileName);

        if (fileName.endsWith(".bin")){
            if (type.equals("product")) productsArr = fileHandler.loadProductsBin();
            if (type.equals("order")) ordersArr = fileHandler.loadOrdersBin();
        }
        if (fileName.endsWith(".json")){
            if (type.equals("product")) productsArr = fileHandler.loadProductsJson();
            if (type.equals("order")) ordersArr = fileHandler.loadOrdersJson();
        }
    }

    /**
     * @param fileName Absolute path in form of string
     */
    @Override
    public void saveFile(String fileName,String type) throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler(PATH + fileName);

        if (fileName.endsWith(".bin")){
            if (type.equals("product")) fileHandler.saveProductsBin(productsArr);
            if (type.equals("order")) fileHandler.saveOrdersBin(ordersArr);
        }
        if (fileName.endsWith(".json")){
            if (type.equals("product")) fileHandler.saveProductsJson(productsArr);
            if (type.equals("order")) fileHandler.saveOrdersJson(ordersArr);
        }
    }


    /**
     * Create product.
     *
     * @param attributes information about attributes are stored like this: name:"String",description:"String",price:"double",stock:"int",vat:"int"
     * @return the product
     */
    @Override
    public String createProduct(String attributes) {
        int id;
        if (productsArr.isEmpty()){
            id = FIRST_ID_ALLOWED;
        } else {
            id = productsArr.get(productsArr.size() - 1).getId() + 1; //set id of new product to be grater by one than last product in list
        }
        Product p = new Product(id);
        productsArr.add(p);
        editProduct(id,attributes);
        return p.toString();
    }

    /**
     * Edit product.
     *
     * @param id    the product
     * @param attributes the attributes information about attributes are stored like this: id="int",name="String",description="String",price="double",stock="int",ean="int"
     * @return the product
     */
    public String editProduct(int id, String attributes) {
        for (Product product : productsArr){
            if (product.getId() == id) {
                AttributesParser.editProduct(product, attributes);
                return product.toString();
            }
        }
        return "";
    }

    /**
     * Remove product returns boolean.
     *
     * @param id the id of product
     * @return the boolean
     */
    @Override
    public boolean removeProduct(int id) {
        for (Product product : productsArr){
            if (product.getId() == id) {
                productsArr.remove(product);
                return true;
            }
        }
        throw new NoSuchElementException("Product of this ID does not exists");
    }

    /**
     * Gets product.
     *
     * @param ean of product
     * @return int
     */
    @Override
    public int getProductId(String ean) {
        for (Product product : productsArr){
            if (product.getEan().equals(ean)) {
                return product.getId();
            }
        }
        throw new NoSuchElementException("Product of this ID does not exists");
    }
    /**
     * Gets product.
     *
     * @param ean the id of product
     * @return the product String
     */
    @Override
    public String getProduct(String ean) {
        for (Product product : productsArr){
            if (product.getEan().equals(ean)) {
                return product.toString();
            }
        }
        throw new NoSuchElementException("Product of this ID does not exists");
    }

    private Product getProductObject(int id) {
        for (Product product : productsArr){
            if (product.getId() == id) {
                return product;
            }
        }
        throw new NoSuchElementException("Product of this ID does not exists");
    }

    /**
     * Search product by name array list.
     *
     * @param name the name
     * @return the array list
     */
    @Override
    public String searchProductByName(String name) {
        List<Product> selected = productsArr
                .stream()
                .filter(product -> product.toString().contains(name))
                .collect(Collectors.toList());
        return selected.toString();
    }

    /**
     * Gets products list.
     *
     * @return the products list
     * @param sortBy string that determines how should be the list sorted. Options: "name","price","vat"
     */
    @Override
    public String getAllProducts(String sortBy) {
        ArrayList<Product> arrForSorting = new ArrayList<>(productsArr);
        if (sortBy.equals("name")){
            Collections.sort(arrForSorting, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            }
        if (sortBy.equals("price")){
            Collections.sort(arrForSorting, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        if (sortBy.equals("vat")){
            Collections.sort(arrForSorting, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        StringBuilder sb = new StringBuilder();
        for (Object product : arrForSorting){
            sb.append(product.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Create order order.
     *
     *
     * @param idOfProducts
     * @return the order
     */
    @Override
    public int createOrder(ArrayList<String> idOfProducts) throws NumberFormatException,NoSuchElementException{
        int orderId;
        if (ordersArr.isEmpty()){
            orderId = FIRST_ID_ALLOWED;
        } else {
            orderId = ordersArr.get(ordersArr.size() - 1).getId() + 1; //set id of new order to be grater by one than last order in list
        }

        ArrayList<ProductQuantity> pqArr = new ArrayList<>();
        // set contains no duplicate elements
        Set<String> unique = new HashSet<String>(idOfProducts);
        for (String key : unique) {
            int quantity = Collections.frequency(idOfProducts,key);
            int productId = Integer.parseInt(key);
            Product product = getProductObject(productId);
            ProductQuantity pq = new ProductQuantity(product,quantity);
            pqArr.add(pq);
        }

        LocalDateTime datetime = LocalDateTime.now();
        Order order = new Order(orderId,datetime,pqArr);
        ordersArr.add(order);
        return orderId;
    }

    /**
     * Edit order order.
     *
     * @param id         the id
     * @param idOfProducts
     * @return the order
     */
    @Override
    public String addToOrder(int id, ArrayList<String> idOfProducts) throws NumberFormatException,NoSuchElementException{
        ArrayList<ProductQuantity> pqArr = new ArrayList<>();
        // set contains no duplicate elements
        Set<String> unique = new HashSet<String>(idOfProducts);
        for (String key : unique) {
            int quantity = Integer.parseInt(key);
            Product product = getProductObject(quantity);
            ProductQuantity pq = new ProductQuantity(product,quantity);
            pqArr.add(pq);
        }
        Order order = getOrder(id);
        Objects.requireNonNull(order).getItems().addAll(pqArr);
        return  order.toString();
    }

    private Order getOrder(int id) {
        for (Order o : ordersArr ){
            if (o.getId() == id){
                return o;
            }
        }
        return null;
    }

    /**
     * Cancel order boolean.
     *
     * @param id the id
     * @return the boolean
     */
    @Override
    public boolean cancelOrder(int id) {
        Order o = getOrder(id);
        if (o == null){
            return false;
        }
        o.setCanceled(true);
        return true;

    }

    /**
     * Search order order.
     *
     * @return the order
     */
    @Override
    public String getLastOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append(ordersArr.get(ordersArr.size() - 1).toString());
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    @Override
    public String getAllOrders() {
        StringBuilder sb = new StringBuilder();
        for (Order order : ordersArr){
            sb.append(order.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Process order boolean.
     *
     *
     * @param id
     * @return the boolean
     */
    @Override
    public boolean processOrder(int id) throws IOException {
        Order o = getOrder(id);
        if (o == null){
            throw new NullPointerException("This Order is null");
        }
        if (o.isCanceled() || o.isPaid()){
            return false;
        }
        for (ProductQuantity pq : o.getItems()){
            pq.getProduct().lowerStockBy(pq.getQuantity());
        }
        o.setPaid(true);
        return true;
    }

    /**
     * Print receipt boolean.
     *
     * @param id of the order
     * @return the boolean
     */
    @Override
    public boolean printReceipt(int id) throws IOException, PrinterException {
        Order o = getOrder(id);
        if (o == null){
            return false;
        }
        String dateTime = o.getTime().toString();
        String[] header = new String[3];
        header[0]=       "   ****Super Market****       ";
        header[1]=       "Date: " + dateTime;
        header[2]=       "---------------------------------";
        Print printer = new Print();
        printer.PrintReceipt();
        return true;

    }


}
