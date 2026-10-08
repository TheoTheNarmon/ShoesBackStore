import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private static final List<Order> orders = new ArrayList<Order>();

    public static List<Order> getOrders() {return orders;}

    private final List<Shoe> shoesOrdered;

    public Order(List<Shoe> shoesOrdered) {
        this.id = UUID.randomUUID().toString();
        this.shoesOrdered = shoesOrdered;
        orders.add(this);
    }

    public String getId() {return id;}
    public List<Shoe> getShoesOrdered() {return this.shoesOrdered;}

    public double price() {
        double total = 0;
        for(Shoe shoe : shoesOrdered) {
            total += shoe.getPrice();
        }
        return total;
    }
}
