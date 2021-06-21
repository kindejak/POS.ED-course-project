package pos.utils;

import java.awt.print.PrinterException;
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
     *
     * @param ean
     * @return the product
     */
    String getProduct(String ean);


    /**
     * Gets product.
     *
     *
     * @param ean
     * @return the product
     */
    int getProductId(String ean);

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
     * @param sortBy
     */
    String getAllProducts(String sortBy);

    /**
     * Create order order.
     *
     *
     * @param idOfProducts
     * @return the order
     */
    int createOrder(ArrayList<String> idOfProducts);

    /**
     * Edit order order.
     *
     * @param id         the id
     * @param idOfProducts
     * @return the order
     */
    String addToOrder(int id, ArrayList<String> idOfProducts) throws Exception;

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
     * @return the order
     */
    String getLastOrder();

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    String getAllOrders();

    /**
     * Process order boolean.
     *
     *
     * @param id@return the boolean
     */
    boolean processOrder(int id) throws IOException;

    /**
     * Print receipt boolean.
     *
     *
     * @param id
     * @return the boolean
     */
    boolean printReceipt(int id) throws IOException, PrinterException;
}

