package pos.app;

import java.io.Serializable;

public class Product implements Serializable {
    private final int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private int vat;

    private static final int ATTRIBUTE_NAME_LENGTH0 = 5;
    private static final int ATTRIBUTE_NAME_LENGTH1 = 12;
    private static final int ATTRIBUTE_NAME_LENGTH2 = 6;
    private static final int ATTRIBUTE_NAME_LENGTH3 = 6;
    private static final int ATTRIBUTE_NAME_LENGTH4 = 4;


    public Product(int id){
        this.id = id;
    }
    // name=String;description=String;price=double;stock=int;vat=int
    public Product(int id,String attributes){
        this.id = id;
        String[] splitAttributes = attributes.split(";");
        this.name = splitAttributes[0].substring(ATTRIBUTE_NAME_LENGTH0);
        this.description = splitAttributes[1].substring(ATTRIBUTE_NAME_LENGTH1);
        this.price = Double.parseDouble(splitAttributes[2].substring(ATTRIBUTE_NAME_LENGTH2));
        this.stock = Integer.parseInt(splitAttributes[3].substring(ATTRIBUTE_NAME_LENGTH3));
        this.vat = Integer.parseInt(splitAttributes[4].substring(ATTRIBUTE_NAME_LENGTH4));


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getVat() {
        return vat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", vat=" + vat
                ;
    }
}
