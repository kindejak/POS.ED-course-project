package pos.app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order extends App implements Serializable {
    private int id;
    private LocalDateTime time;
    private ArrayList<ProductQuantity> items;
    private double totalPrice;
    private boolean canceled = false;
    private boolean paid = false;

    public Order()
    {
        super();
    }

    public Order(int id, LocalDateTime time) {
        this.id = id;
        this.time = time;
        updateTotalPrice();
    }

    public Order(int id, LocalDateTime time, ArrayList<ProductQuantity> items) {
        this.id = id;
        this.time = time;
        this.items = items;
        updateTotalPrice();
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
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

    public double updateTotalPrice(){
        double sum = 0;
        for (ProductQuantity item : items){
            sum += item.getProduct().getPrice()*item.getQuantity();
        }
        this.totalPrice = sum;
        return sum;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ";time=" + time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss")) +
                ";items=" + items.toString() +
                ";totalPrice=" + totalPrice +
                ";canceled=" + canceled +
                ";paid=" + paid;
    }
}
