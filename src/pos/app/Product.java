package pos.app;

import pos.utils.AttributesParser;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private int vat;
    private String ean;

    public Product()
    {
        super();
    }

    public Product(int id){
        this.id = id;
    }
    // name=String;description=String;price=double;stock=int;vat=int;ean=String
    public Product(int id,String attributes){
        this.id = id;
        String[] splitAttributes = attributes.split(";");
        this.name = splitAttributes[0].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH0);
        this.description = splitAttributes[1].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH1);
        this.price = Double.parseDouble(splitAttributes[2].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH2));
        this.stock = Integer.parseInt(splitAttributes[3].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH3));
        this.vat = Integer.parseInt(splitAttributes[4].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH4));
        this.ean = splitAttributes[5].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH5);
    }

    public Product(String attributes){
        String[] splitAttributes = attributes.split(";");
        this.id = Integer.parseInt(splitAttributes[0].substring(3));
        this.name = splitAttributes[1].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH0);
        this.description = splitAttributes[2].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH1);
        this.price = Double.parseDouble(splitAttributes[3].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH2));
        this.stock = Integer.parseInt(splitAttributes[4].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH3));
        this.vat = Integer.parseInt(splitAttributes[5].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH4));
        this.ean = splitAttributes[6].substring(AttributesParser.ATTRIBUTE_NAME_LENGTH5);
    }

    public static void main(String[] args) {
        Product p = new Product(1000,"name=Kozel 10°;description=budget beer for students, today in sale in Albert;price=14.9;stock=258;vat=21;ean=2287740645762");
        System.out.println(p.toString());
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

    public String getEan() {
        return ean;
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

    public void setEan(String ean) {
        this.ean = ean;
    }

    public void increaseStockBy(int stockChange){
        stock += stockChange;
    }

    public void lowerStockBy(int stockChange){
        stock -= stockChange;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ";name=" + name +
                ";description=" + description  +
                ";price=" + price +
                ";stock=" + stock +
                ";vat=" + vat +
                ";ean=" + ean;
    }
}
