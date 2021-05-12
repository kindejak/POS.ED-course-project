package pos.app;


import pos.utils.Order;
import pos.utils.PosInterface;
import pos.utils.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class App implements PosInterface {

    ArrayList<Order> ordersArr;
    ArrayList<Product> productsArr;

    /**
     * @param path Absolute path in form of string
     */
    @Override
    public void loadFile(String path,String type) throws IOException, ClassNotFoundException {
        FileHandler fileHandler = new FileHandler(path);

        if (path.endsWith(".bin")){
            if (type.equals("product")) fileHandler.loadProductsBin();
            if (type.equals("order")) fileHandler.loadOrdersBin();
        }
        if (path.endsWith(".json")){
            if (type.equals("product")) fileHandler.loadProductsJson();
            if (type.equals("order")) fileHandler.loadOrdersJson();
        }
    }

    /**
     * @param path Absolute path in form of string
     */
    @Override
    public void saveFile(String path,String type) throws IOException {
        FileHandler fileHandler = new FileHandler(path);

        if (path.endsWith(".bin")){
            if (type.equals("product")) fileHandler.saveProductsBin(productsArr);
            if (type.equals("order")) fileHandler.saveOrdersBin(ordersArr);
        }
        if (path.endsWith(".json")){
            if (type.equals("product")) fileHandler.saveProductsJson(productsArr);
            if (type.equals("order")) fileHandler.saveOrdersJson(ordersArr);
        }
    }

    /**
     * Create product.
     *
     * @param attributes information about attributes are stored like this: id:"int",name:"String",description:"String",price:"double",stock:"int"
     * @return the product
     */
    @Override
    public Product createProduct(String attributes) {
        return null;
    }

    /**
     * Edit product.
     *
     * @param product    the product
     * @param attributes the attributes information about attributes are stored like this: id:"int",name:"String",description:"String",price:"double",stock:"int"
     * @return the product
     */
    @Override
    public Product editProduct(Product product, String attributes) {
        return null;
    }

    /**
     * Remove product returns boolean.
     *
     * @param id the id of product
     * @return the boolean
     */
    @Override
    public boolean removeProduct(int id) {
        return false;
    }

    /**
     * Gets product.
     *
     * @param id the id of product
     * @return the product
     */
    @Override
    public Product getProduct(int id) {
        return null;
    }

    /**
     * Search product by name array list.
     *
     * @param name the name
     * @return the array list
     */
    @Override
    public ArrayList<Product> searchProductByName(String name) {
        return null;
    }

    /**
     * Gets products list.
     *
     * @return the products list
     */
    @Override
    public ArrayList<Product> getProductsList() {
        return null;
    }

    /**
     * Create order order.
     *
     * @param attributes the attributes
     * @return the order
     */
    @Override
    public Order createOrder(String attributes) {
        return null;
    }

    /**
     * Edit order order.
     *
     * @param id         the id
     * @param attributes the attributes
     * @return the order
     */
    @Override
    public Order editOrder(int id, String attributes) {
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
        return false;
    }

    /**
     * Search order order.
     *
     * @param id the id
     * @return the order
     */
    @Override
    public Order searchOrder(int id) {
        return null;
    }

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    @Override
    public ArrayList<Order> getOrdersList() {
        return null;
    }

    /**
     * Process order boolean.
     *
     * @param order the order
     * @return the boolean
     */
    @Override
    public boolean processOrder(Order order) {
        return false;
    }

    /**
     * Print receipt boolean.
     *
     * @param order the order
     * @return the boolean
     */
    @Override
    public boolean printReceipt(Order order) {
        return false;
    }
}
