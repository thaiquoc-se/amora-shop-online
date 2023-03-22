/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import staffLogs.StaffLogDAO;
import user.UserDAO;
import user.UserDTO;
import utils.Encode;

/**
 *
 * @author thaiq
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
//    private static final String ADMIN_PAGE = "admin.jsp";
//    private static final String STAFF_PAGE = "staff.jsp";
    private static final String USER_PAGE = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeIn = sdf.format(date);
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            password = Encode.toSHA1(password);

            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(email, password);
            //validate user in here
            if (loginUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                session.setMaxInactiveInterval(60 * 60); // 1 hour
                int roleID = loginUser.getRoleID();
                String status = loginUser.getStatus();
                if (status.equalsIgnoreCase("ACTIVE")) {
                    if (roleID == 1) {
                        url = "/AdminDashboardController";
                    } else if (roleID == 2) {
                        StaffLogDAO logDao = new StaffLogDAO();
                        logDao.insertLog(loginUser.getEmail(), timeIn);
                        url = USER_PAGE;
                    } else if (roleID == 3) {
                        StaffLogDAO logDao = new StaffLogDAO();
                        logDao.insertLog(loginUser.getEmail(), timeIn);
                        url = "/ShowProductAdminController";
                    } else {
                        request.setAttribute("ERROR", "Your role is not support:");
                    }
                } else {
                    request.setAttribute("ERROR", "Your account does not have access to system!!!");
                }

            } else {
                request.setAttribute("ERROR", "Incorrect Email or Password, Please try again.");
            }
        } catch (SQLException e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
