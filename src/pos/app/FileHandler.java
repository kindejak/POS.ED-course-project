package pos.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHandler implements Serializable {
    File file;

    public static void main(String[] args) {
        FileHandler fh = new FileHandler("data\\products.json");

        try {
            ArrayList<Product> p = fh.loadProductsJson();
            fh.setFile(new File("data\\products.txt"));
            fh.saveToBin(p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ArrayList<Product> arr = fh.loadFromBin();
            for (Product p : arr){
                System.out.println(p.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileHandler(String path) {
        this.file = new File(path);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<Order> loadOrdersBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            ArrayList<Order> orderArrayList = (ArrayList<Order>) ois.readObject();
            return orderArrayList;
        }
    }
    public ArrayList<Order> loadOrdersJson() throws IOException {
        //Jackson is auto closing stream after the call
        ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());// `java.time.LocalDateTime` not supported by default Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" enables to work properly
        List<Order> orders = mapper.reader()
                .forType(new TypeReference<List<Order>>() {})
                .readValue(file);
        return new ArrayList<Order>(orders);
    }

    public void saveToBin(ArrayList<Product> arr) throws IOException {
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file))) {
            for (Product p : arr){
                writer.write(p.toString().getBytes());
                writer.write(System.lineSeparator().getBytes());
            }
        }
    }

    public ArrayList<Product> loadFromBin()throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
            while (reader.available() > 0){
                int length = reader.read(buffer);
                String s = new String(buffer,0,length, StandardCharsets.UTF_8);
                sb.append(s);
            }
        }
        String products = sb.toString();
        ArrayList<Product> arr = new ArrayList<Product>();
        for (String line : products.split(System.lineSeparator())){
            Product p = new Product(line);
            arr.add(p);
        }
        return arr;
    }

    public void saveToTxt(ArrayList<Product> arr) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Product p : arr){
                writer.write(p.toString());
                writer.newLine();
            }
        }
    }


    public ArrayList<Product> loadFromTxt() throws IOException {
        String line = "";
        ArrayList<Product> arr = new ArrayList<Product>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((line = reader.readLine()) != null){
                Product p = new Product(line);
                arr.add(p);
            }
        }
        return arr;
    }

    public void saveOrdersBin(ArrayList<Order> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveOrdersJson(ArrayList<Order> arr) throws IOException, ClassNotFoundException {
        //Jackson is auto closing stream after the call
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        mapper.writeValue(file,arr);
    }

    public ArrayList<Product> loadProductsBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ){
            return (ArrayList<Product>) ois.readObject();
        }
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
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                ){
            oos.writeObject(arr);
        }
    }

    public void saveProductsJson(ArrayList<Product> arr) throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        mapper.writeValue(file,arr);
    }
}
