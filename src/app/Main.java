package app;

import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Department obj = new Department(1, "Books");

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", LocalDateTime.now() , 3000.0, obj);

        System.out.println(seller);
    }
}
