package app;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("===TESTE 1: Department findById===");
        Department dep = departmentDao.findById(2);
        System.out.println(dep);
        System.out.println();

        System.out.println("===TESTE 2: Department findAll===");
        List<Department> deps = departmentDao.findAll();
        deps.forEach(System.out::println);
        System.out.println();

        System.out.println("===TESTE 3: Department insert===");
        Department newDep = new Department(null, "Cosmetics");
        departmentDao.insert(newDep);
        System.out.println("Inserido! Novo ID = " + newDep.getId());
        System.out.println();

        System.out.println("===TESTE 4: Department update===");
        newDep.setName("Food");
        departmentDao.update(newDep);
        System.out.println("Atualizado com Sucesso!");
        System.out.println();

        System.out.println("===TESTE 5: Department delete===");
        System.out.print("ID: ");
        departmentDao.deleteById(sc.nextInt());
        System.out.println("Deletado!");
        System.out.println();
    }
}
