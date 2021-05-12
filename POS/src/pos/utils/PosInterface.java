package pos.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface Pos interface.
 */
public interface PosInterface {

    /**
     * allows binary files or JSON files to be loaded
     * @param path Absolute path in form of string
     * @return boolean; true if successful
     */
    public void loadFile(String path,String type) throws IOException, ClassNotFoundException;

    /**
     * allows binary files or JSON files to be saved
     * @param path Absolute path in form of string
     * @return boolean; true if successful
     */
    public void saveFile(String path,String type) throws IOException;]

    /**
     * Create product.
     *
     * @param attributes information about attributes are stored like this: id:"int",name:"String",description:"String",price:"double",stock:"int"
     * @return the product
     */
    public Product createProduct(String attributes);

    /**
     * Edit product.
     *
     * @param product    the product
     * @param attributes the attributes information about attributes are stored like this: id:"int",name:"String",description:"String",price:"double",stock:"int"
     * @return the product
     */
    public Product editProduct(Product product, String attributes);

    /**
     * Remove product returns boolean.
     *
     * @param id the id of product
     * @return the boolean
     */
    public boolean removeProduct(int id);

    /**
     * Gets product.
     *
     * @param id the id of product
     * @return the product
     */
    public Product getProduct(int id);

    /**
     * Search product by name array list.
     *
     * @param name the name
     * @return the array list
     */
    public ArrayList<Product> searchProductByName(String name);

    /**
     * Gets products list.
     *
     * @return the products list
     */
    public ArrayList<Product> getProductsList();

    /**
     * Create order order.
     *
     * @param attributes the attributes
     * @return the order
     */
    public Order createOrder(String attributes);

    /**
     * Edit order order.
     *
     * @param id         the id
     * @param attributes the attributes
     * @return the order
     */
    public Order editOrder(int id,String attributes);

    /**
     * Cancel order boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean cancelOrder(int id);

    /**
     * Search order order.
     *
     * @param id the id
     * @return the order
     */
    public Order searchOrder(int id);

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    public ArrayList<Order> getOrdersList();

    /**
     * Process order boolean.
     *
     * @param order the order
     * @return the boolean
     */
    public boolean processOrder(Order order);

    /**
     * Print receipt boolean.
     *
     * @param order the order
     * @return the boolean
     */
    public boolean printReceipt(Order order);
}

