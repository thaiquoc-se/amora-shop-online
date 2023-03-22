/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

import Cart.CartError;
import Cart.CartObj;
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

/**
 *
 * @author long
 */
public class AddToCartServlet extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            ProductDAO dao = new ProductDAO();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            int productID = Integer.parseInt(request.getParameter("productID"));
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            String id = String.valueOf(productID);

            String checkID = request.getParameter("productID");
            int dataPrdQuan = dao.getProductByIdTypeString(checkID).getQuantity();
            if (dataPrdQuan >= productQuantity) {
                if (productQuantity >= 1) {
                    int quantityCurrent = cart.getQuantityItemCurrent(id) + productQuantity;
                    if(quantityCurrent <= dataPrdQuan){
                    cart.addItemToCart(id, productQuantity);    
                    }else{
                        request.setAttribute("ERROR_QUANTITY_DB", "Sorry, we don't have enough product for your request!!!");
                    }//Check kiểm tra số lượng của req và số lượng sản phẩm hiện tại có trog cart không được quá SL kho                    
                } else {
                    request.setAttribute("ERROR_QUANTITY_INPUT", "Can not add product with 0 or negative number!!!");
                }
            } else{
                request.setAttribute("ERROR_QUANTITY_DB", "Sorry, we don't have enough product for your request!!!");
            } // KH không được req số lượng trog 1 lần lớn hơn SL kho


//            cart.addItemToCart(id, 1);
            session.setAttribute("CART", cart);
            request.getRequestDispatcher("ShowProductDetailUserController?product_id=" + productID).forward(request, response);
        } catch(SQLException ex){
            log("SQL " + ex.getMessage());
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
