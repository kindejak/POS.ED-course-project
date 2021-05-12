package pos.app;

import pos.utils.Order;
import pos.utils.Product;

import java.io.*;
import java.util.ArrayList;

public class FileHandler implements Serializable {
    File file;

    public FileHandler(String path) {
        this.file = new File(path);
    }

    public ArrayList<Order> loadOrdersBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (ArrayList<Order>) ois.readObject();
    }
    public void loadOrdersJson() throws IOException, ClassNotFoundException {

    }

    public void saveOrdersBin(ArrayList<Order> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveOrdersJson() throws IOException, ClassNotFoundException {

    }

    public ArrayList<Product> loadProductsBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (ArrayList<Product>) ois.readObject();
    }
    public void loadProductsJson() throws IOException, ClassNotFoundException {

    }

    public void saveProductsBin(ArrayList<Product> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveProductsJson() throws IOException, ClassNotFoundException {

    }
}
