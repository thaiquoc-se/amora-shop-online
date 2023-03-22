/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

import Cart.CartObj;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.ProductDAO;
import product.ProductDTO;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UpdateItemCartServlet extends HttpServlet {

//    private static final String ERROR = "error.html";
//    private static final String UPDATE_SUCCESS = "viewCart.jsp";
//    private static final String VIEW_CART = "ViewCartController";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        int updateQuantity = Integer.parseInt(request.getParameter("updateQuantity"));
        String productID = request.getParameter("productID");
        String plusQuan = request.getParameter("plusQuan");
        String minusQuan = request.getParameter("minusQuan");
//        String URL = VIEW_CART;

        try {
            HttpSession session = request.getSession();
            ProductDAO dao = new ProductDAO();
            CartObj cart = (CartObj) session.getAttribute("CART");
            int dataPrdQuan = dao.getProductByIdTypeString(productID).getQuantity();
            
            if (cart != null) {
                //Check para not null & contain vaule = true
                if (plusQuan != null && !plusQuan.isEmpty()) {
                    int currentQuan = cart.getQuantityItemCurrent(productID) + 1;
                    if(currentQuan <= dataPrdQuan){
                    cart.updateItemCart(productID, 1);    
                    }else{
                        request.setAttribute("ERROR_QUAN_DB", "Your request has exceeded our stock");
                    }
                }
                if (minusQuan != null && !minusQuan.isEmpty()) {
                    cart.updateItemCart(productID, -1);
                }
            } else {
                request.setAttribute("UPDATE_QUANTITY_ERR", "Some thing has gone wrong on update item cart!");
            }
        } catch(SQLException ex){
            log("SQL " + ex.getMessage());
        } 
        finally {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewCartController");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
