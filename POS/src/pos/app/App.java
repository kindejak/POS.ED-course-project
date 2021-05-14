package pos.app;


import pos.utils.MainPosInterface;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class App implements MainPosInterface {

    final static int FIRST_ID_ALLOWED = 1000;
    final static String PATH = "data" + File.separator;
    private ArrayList<Order> ordersArr;
    private ArrayList<Product> productsArr;

    public App() {
        ordersArr = new ArrayList<Order>();
        productsArr = new ArrayList<Product>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        App a = new App();
        try {
            a.loadFile("products.bin","product");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(a.getAllProducts());
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
        if (fileName.endsWith(".csv")){
            if (type.equals("product")) productsArr = fileHandler.loadProductsCsv();
            if (type.equals("order")) ordersArr = fileHandler.loadOrdersCsv();
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
        if (fileName.endsWith(".csv")){
            if (type.equals("product")) fileHandler.saveProductsCsv();
            if (type.equals("order")) fileHandler.saveOrdersCsv();
        }
    }

    private void addProduct(Product product){
        productsArr.add(product);
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
     * @param attributes the attributes information about attributes are stored like this: id:"int",name:"String",description:"String",price:"double",stock:"int"
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
     * @param id the id of product
     * @return the product String
     */
    @Override
    public String getProduct(int id) {
        for (Product product : productsArr){
            if (product.getId() == id) {
                return product.toString();
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
     */
    @Override
    public String getAllProducts() {
        StringBuilder sb = new StringBuilder();
        for (Product product : productsArr){
            sb.append(product.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Create order order.
     *
     *
     * @param idOfProductsToOrder@return the order
     */
    @Override
    public String createOrder(ArrayList<Integer> idOfProductsToOrder) {
        int orderId;
        if (ordersArr.isEmpty()){
            orderId = FIRST_ID_ALLOWED;
        } else {
            orderId = ordersArr.get(ordersArr.size() - 1).getId() + 1; //set id of new order to be grater by one than last order in list
        }

        ArrayList<ProductQuantity> pqArr = new ArrayList<ProductQuantity>();
        ArrayList<Integer> idAlreadyAdded = new ArrayList<Integer>();
        for (int productId : idOfProductsToOrder){
            
            Product product = getProductObject(productId);
            if(ordersArr.isEmpty() || !idAlreadyAdded.contains(productId)){
                idAlreadyAdded.add(productId);
                ProductQuantity pq = new ProductQuantity(product);
                pqArr.add(pq);
            } else {
                pqArr.get(idAlreadyAdded.indexOf(productId)).quantityPlusOne();
            }
        }
        idAlreadyAdded.clear();

        LocalDateTime datetime = LocalDateTime.now();
        Order order = new Order(orderId,datetime,pqArr);
        ordersArr.add(order);
        return order.toString();
    }

    /**
     * Edit order order.
     *
     * @param id         the id
     * @param idOfProductsToOrder
     * @return the order
     */
    @Override
    public String editOrder(int id, ArrayList<Integer> idOfProductsToOrder) {
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
     * @param numberOfOrders the id
     * @return the order
     */
    @Override
    public String getLastOrders(int numberOfOrders) {
        return null;
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

    private Product getProductObject(int id) {
        for (Product product : productsArr){
            if (product.getId() == id) {
                return product;
            }
        }
        throw new NoSuchElementException("Product of this ID does not exists");
    }
}
