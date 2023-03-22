/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewArrivals;

import category.CategoryDAO;
import category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import product.ProductDAO;
import product.ProductDTO;
import user.UserDTO;

/**
 *
 * @author long
 */
public class ShowAllArrivalsProduct extends HttpServlet {

    private final static String HOME_PAGE = "homePage.jsp";
    private final static String NEW_ARRIVALS = "new-arrivals.jsp";

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
            String isHomePage = request.getParameter("homePage");
            String newArr = request.getParameter("searchNewArr");
            String URL = HOME_PAGE;
            final int PAGE_SIZE = 12;
            int page = 1;
            String pageStr = request.getParameter("page");

            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> listProduct = productDAO.getAllProductByStatusArrivals(page, PAGE_SIZE);
            //tinh tong so san pham trongdatabase r tra ve so trang 
            //de hien len man hinh jsp 
            int totalProducts = productDAO.getNewArrivalsProductPage();
            int totalPage = totalProducts / PAGE_SIZE;
            //chia lay du totalProducts neu co du thi + 1 page cho totalPage
            if (totalProducts % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            HttpSession session = request.getSession();
            List<ProductDTO> favoritePrds = productDAO.getProductHomePage();
            request.setAttribute("favPrd", favoritePrds);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("products", listProduct);

            List<CategoryDTO> ProductSearchResult = new CategoryDAO().getAllCategory();
            request.setAttribute("PRODUCT_NAME_RESULT", ProductSearchResult);

            if (listProduct.isEmpty()) {
                request.setAttribute("SearchErrorNote", "No result!!!");
            }
            if (newArr != null) {
                URL = NEW_ARRIVALS;
            }
            request.getRequestDispatcher(URL).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ShowAllArrivalsProduct.class.getName()).log(Level.SEVERE, null, ex);
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
