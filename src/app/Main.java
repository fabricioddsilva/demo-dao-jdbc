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
        System.out.println();

        System.out.println("===TESTE 3: Seller findAll===");
        sellers = sellerDao.findAll();
        sellers.forEach(System.out::println);
        System.out.println();

//        System.out.println("===TESTE 4: Seller insert===");
//        Seller newSeller = new Seller(null,
//                "Greg",
//                "greg@gmail.com",
//                LocalDateTime.now(),
//                4000.0,
//                new Department(2, null));
//        sellerDao.insert(newSeller);
//        System.out.println("Inserido! Novo ID = " + newSeller.getId());
//        System.out.println();
//
//        System.out.println("===TESTE 5: Seller update===");
//        seller = sellerDao.findById(1);
//        seller.setName("Martha Waine");
//        seller.setEmail("martha@gmail.com");
//        sellerDao.update(seller);
//        System.out.println("Atualizado com Sucesso!");
//        System.out.println();

        System.out.println("===TESTE 6: Seller delete===");
        sellerDao.deleteById(13);
        System.out.println("Deletado com Sucesso!");
        System.out.println();
    }
}
