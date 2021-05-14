package pos.app;

import java.io.*;
import java.util.ArrayList;

public class FileHandler implements Serializable {
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
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Order> orderArrayList = (ArrayList<Order>) ois.readObject();
            return orderArrayList;
        }   catch (FileNotFoundException e){

            e.printStackTrace();
        }
        catch (IOException e){

            e.printStackTrace();
        }
        catch (ClassNotFoundException e){

            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Order> loadOrdersCsv() throws IOException, ClassNotFoundException {
        return null;
    }

    public void saveOrdersBin(ArrayList<Order> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveOrdersCsv() throws IOException, ClassNotFoundException {

    }

    public ArrayList<Product> loadProductsBin() throws IOException, ClassNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File doesn't exists.");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (ArrayList<Product>) ois.readObject();
        } catch (FileNotFoundException e){

            e.printStackTrace();
        }
        catch (IOException e){

            e.printStackTrace();
        }
        catch (ClassNotFoundException e){

            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Product> loadProductsCsv() throws IOException, ClassNotFoundException {
        return null;
    }

    public void saveProductsBin(ArrayList<Product> arr) throws IOException {
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
    }

    public void saveProductsCsv() throws IOException, ClassNotFoundException {

    }
}
