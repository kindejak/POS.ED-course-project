package pos.utils;

import pos.app.Order;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface Pos interface.
 */
public interface MainPosInterface {

    /**
     * allows binary files or CSV files to be loaded
     * @param path Absolute path in form of string
     * @return boolean; true if successful
     */
    void loadFile(String path, String type) throws IOException, ClassNotFoundException;

    /**
     * allows binary files or CSV files to be saved
     * @param path Absolute path in form of string
     * @param type can be set to "order" or "product"
     * @return boolean; true if successful
     */
    void saveFile(String path, String type) throws IOException, ClassNotFoundException;

    /**
     * Create product.
     *
     * @param attributes information about attributes are stored like this: name=String;description=String;price=double;stock=int;vat=int
     * @return the product
     */
    String createProduct(String attributes);

    /**
     * Edit product.
     *
     * @param id   id of the product
     * @param attributes the attributes information about attributes are stored like this: name=String;description=String;price=double;stock=int;vat=int
     * @return the product
     */
    String editProduct(int id, String attributes);

    /**
     * Remove product returns boolean.
     *
     * @param id the id of product
     * @return the boolean
     */
    boolean removeProduct(int id);

    /**
     * Gets product.
     *
     * @param id the id of product
     * @return the product
     */
    String getProduct(int id);

    /**
     * Search product by name array list.
     *
     * @param name the name
     * @return the array list
     */
    String searchProductByName(String name);

    /**
     * Gets products list.
     *
     * @return the products list
     */
    String getAllProducts();

    /**
     * Create order order.
     *
     *
     * @param idOfProductsToOrder
     * @return the order
     */
    String createOrder(ArrayList<Integer> idOfProductsToOrder);

    /**
     * Edit order order.
     *
     * @param id         the id
     * @param idOfProductsToOrder
     * @return the order
     */
    String editOrder(int id, ArrayList<Integer> idOfProductsToOrder);

    /**
     * Cancel order boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean cancelOrder(int id);

    /**
     * Search order order.
     *
     * @param numberOfOrders the id
     * @return the order
     */
    String getLastOrders(int numberOfOrders);

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    String getAllOrders();

    /**
     * Process order boolean.
     *
     * @param order the order
     * @return the boolean
     */
    boolean processOrder(Order order);

    /**
     * Print receipt boolean.
     *
     * @param order the order
     * @return the boolean
     */
    boolean printReceipt(Order order);
}

