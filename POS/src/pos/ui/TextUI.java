package pos.ui;


import pos.app.App;

import java.io.IOException;
import java.util.ArrayList;

public class TextUI {

    public static void main(String[] args) {
        App a = new App();

        System.out.println(a.getAllProducts());
        try {
            a.loadFile("products.bin","product");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(a.getAllProducts());
        ArrayList<Integer> intArr = new ArrayList<Integer>();
        intArr.add(1001);
        intArr.add(1002);
        try {
            a.createOrder(intArr);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            a.saveFile("orders.bin","order");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(a.getAllOrders());
    }
}
