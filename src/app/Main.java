package app;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("===TESTE 1: Seller findById===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println("===TESTE 2: Seller findByDepartment===");
        List<Seller> sellers = sellerDao.findByDepartment(2);
        sellers.forEach(System.out::println);
    }
}
