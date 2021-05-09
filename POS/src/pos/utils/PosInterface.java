package pos.utils;

import java.util.ArrayList;

/**
 * Interface between the POS app and the UI
 * Defines requested functionality
 *
 * @author Jakub Kindermann
 */
public interface PosInterface {

    /**
     * Creates product.
     *
     * @return the product created
     */
    public Product createProduct();

    /**
     * Edit product of chosen id.
     *
     * @param id            id of the product in database
     * @param editedProduct the edited product
     * @return the product in edited form
     */
    public Product editProduct(int id, Product editedProduct);

    /**
     * Remove product boolean.
     *
     * @param id id of the product in database
     * @return the boolean; if product was removed returns true
     */
    public boolean removeProduct(int id);


    /**
     * Gets product.
     *
     * @param id the id
     * @return the product
     */
    public Product getProduct(int id);

    /**
     * Search product by name of the product.
     *
     * @param name the name of product
     * @return the product list
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
     * @return the order
     */
    public Order createOrder();

    /**
     * Edit order order.
     *
     * @param id of the product in database
     * @return the order
     */
    public Order editOrder(int id);

    /**
     * Cancel order boolean.
     *
     * @param id of the product in database
     * @return the boolean
     */
    public boolean cancelOrder(int id);

    /**
     * Search order order.
     *
     * @param id of the product in database
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
     * @return the boolean
     */
    public boolean processOrder();

    /**
     * Prints receipt bof.
     *
     * @return the boolean; if order was printed returns true
     */
    public boolean printReceipt();
}

