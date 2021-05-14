package pos.app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order implements Serializable {
    private final int id;
    private LocalDateTime time;
    private ArrayList<ProductQuantity> items;
    private double totalPrice;
    private boolean canceled = false;

    public Order(int id, LocalDateTime time) {
        this.id = id;
        this.time = time;
    }

    public Order(int id, LocalDateTime time, ArrayList<ProductQuantity> items) {
        this.id = id;
        this.time = time;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ArrayList<ProductQuantity> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", time=" + time +
                ", items=" + items.toString() +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
