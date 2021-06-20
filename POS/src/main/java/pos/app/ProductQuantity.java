package pos.app;

import pos.app.Product;

import java.io.Serializable;

public class ProductQuantity extends Order implements Serializable {
    private Product product;
    private int quantity;

    public ProductQuantity()
    {
        super();
    }

    public ProductQuantity(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public ProductQuantity(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void quantityPlusOne() {
        this.quantity++;
    }

    @Override
    public String toString() {
        return "ProductQuantity{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
