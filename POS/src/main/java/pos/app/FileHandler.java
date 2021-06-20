package pos.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FileHandler extends App implements Serializable {
    File file;

    public static void main(String[] args) {
        FileHandler fh = new FileHandler("data\\products.bin");
        try {
            ArrayList<Product> p = fh.loadProductsBin();
            for (Product product : p){
                System.out.println(product.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileHandler(String path) {
        this.file = new File(path);
    }

    public ArrayList<Order> loadOrdersBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Order> orderArrayList = (ArrayList<Order>) ois.readObject();
        return orderArrayList;
    }
    public ArrayList<Order> loadOrdersJson() throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());// `java.time.LocalDateTime` not supported by default Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" enables to work properly
        List<Order> orders = mapper.reader()
                .forType(new TypeReference<List<Order>>() {})
                .readValue(file);
        return new ArrayList<Order>(orders);
    }

    public void saveOrdersBin(ArrayList<Order> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveOrdersJson(ArrayList<Order> arr) throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        mapper.writeValue(file,arr);
    }

    public ArrayList<Product> loadProductsBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (ArrayList<Product>) ois.readObject();
    }
    public ArrayList<Product> loadProductsJson() throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());
        // `java.time.LocalDateTime` not supported by default. Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" enables to work properly
        List<Product> products = mapper.reader()
                .forType(new TypeReference<List<Product>>() {})
                .readValue(file);
        return new ArrayList<Product>(products);
    }

    public void saveProductsBin(ArrayList<Product> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveProductsJson(ArrayList<Product> arr) throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        mapper.writeValue(file,arr);
    }
}
