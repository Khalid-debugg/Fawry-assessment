import models.*;
import behaviors.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Product cheese = new Product("Cheese", 100, 5, new CanBeShipped("Cheese", 200), new CanExpire(LocalDate.of(2025, 10    , 1)));
        Product biscuits = new Product("Biscuits", 150, 2, new CanBeShipped("Biscuits", 700));
        Customer customer = new Customer("Ali", 10000);
        Cart cart1 = new Cart();
        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);
        cart1.checkout(customer);
    }
}