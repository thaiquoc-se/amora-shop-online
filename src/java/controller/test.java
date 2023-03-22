/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import category.CategoryDAO;
import category.CategoryDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import orderDetail.OrderDetailDAO;
import orders.OrderDAO;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import product.ProductDAO;
import product.ProductDTO;
import staffLogs.StaffLogDAO;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author thaiq
 */
public class test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Date date = new Date(System.currentTimeMillis());
//            String dateOut = String.valueOf(date);
        StaffLogDAO dao = new StaffLogDAO();
//        dao.updateLog("Test", dateOut);
        System.out.println(dao.getAllStaffLogs().toString());
        
//        OrderDAO dao = new OrderDAO();
//        System.out.println(dao.getOrder(131).toString());
//        Test getAllUsers() function in User DAO
//        UserDAO dao = new UserDAO();
//        
//        System.out.println(dao.getUserByEmail("thaiquocse@gmail.com").toString());
//        dao.updateUserByEmail("Qshu","Male", "1", "TPHCM", "1", "Kayme@email.com");
//        System.out.println(dao.getAllUsers());
//        List<UserDTO> list = dao.getAllUsers();
//        if(list.size() > 1){
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).toString());
//            }
//        } else if(list.size() < 0){
//            System.out.println("NULL");
//    }
//}

        //Test getAllProduct() function in product DAO
        
//      ProductDAO dao = new ProductDAO();
//      List<ProductDTO> list = dao.getAllProducts();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }


//        System.out.println(dao.getListOrderDetailByOrderID().toString());
//ProductDAO dao = new ProductDAO();
//        System.out.println(dao.getAllProductByBrandAndCategory("Dior", 1));
    }
}
