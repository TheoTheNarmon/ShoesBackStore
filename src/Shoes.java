import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Shoe {
    public static final List<Shoe> shoes = new ArrayList<Shoe>();

    private String id;
    private String name;
    private double price;
    private int quantityStock;

    public Shoe(String id, String name, double price, int quantityStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
        shoes.add(this);
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public Double getPrice() {return price;}
    public int getStock() {return quantityStock;}

    public void addStock(int quantity) {this.quantityStock += quantity;}
    public void deleteStock(int quantity) {this.quantityStock -= quantity;}
    public void setStock(int quantity) {this.quantityStock = quantity;}
    public void setPrice(double price) {this.price = price;}
    public void setName(String name) {this.name = name;}

    public static List<Shoe> getShoes() {return shoes;}
    public void delete(){shoes.remove(this);}


}

class Botin extends Shoe{
    private int condition;
    public Botin(String id, String name, double price, int quantityStock, int condition) {
        super(id, name, price, quantityStock);
        this.condition = condition;
    }

    public int getCondition() {return condition;}
    public void setCondition(int condition) {this.condition = condition;}
}