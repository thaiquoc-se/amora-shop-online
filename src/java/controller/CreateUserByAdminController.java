/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.UserDAO;
import utils.Encode;

/**
 *
 * @author thaiq
 */
public class CreateUserByAdminController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String fullName = request.getParameter("fullName");

            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String password = request.getParameter("password");

            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
//            String status = request.getParameter("status");

//            int roleID = Integer.parseInt(request.getParameter("roleID"));
            UserDAO dao = new UserDAO();
            boolean user = dao.checkDuplicate(email);
            if (user == false) {
                password = Encode.toSHA1(password);

                dao.insertNewUserByAdmin(fullName, password, gender, email, phoneNumber, address, "ACTIVE", 3);
                request.setAttribute("successfully", "successfully added new user");
                request.getRequestDispatcher("create-user.jsp").forward(request, response);
            } else {
                request.setAttribute("erorr", "Email was existed");
                request.getRequestDispatcher("create-user.jsp").forward(request, response);
            }

            response.sendRedirect("/ShowUserController");
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserByAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserByAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserByAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
