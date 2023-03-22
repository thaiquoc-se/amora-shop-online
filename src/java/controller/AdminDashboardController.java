/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Chart.ChartDTO;
import Date.DateDAO;
import Date.DateDTO;
import category.CategoryDAO;
import feedBack.FeedBackDAO;
import feedBackImage.FeedBackImageDTO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.ProductDAO;
import product.ProductDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import orderDetail.OrderDetailDAO;
import orders.OrderDAO;
import product.ProductImageDTO;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author long
 */
public class AdminDashboardController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            ProductDAO pd = new ProductDAO();
            UserDAO ud = new UserDAO();
            OrderDAO od = new OrderDAO();
            FeedBackDAO fd = new FeedBackDAO();
            CategoryDAO cd = new CategoryDAO();
            DateDAO dd = new DateDAO();

            DateDTO date = dd.get7day();

            String start = date.getStart().toString();
            String end = date.getEnd().toString();
            String start_raw = request.getParameter("start");
            String end_raw = request.getParameter("end");
            if (start_raw != null) {
                start = start_raw;
                end = end_raw;
            }
            int day = dd.CountDayByStartEnd(start, end);

            List<ChartDTO> listChartRevenueArea = od.getChartRevenueArea(start, day);
            float maxListChartRevenueArea = -1;
            for (ChartDTO o : listChartRevenueArea) {
                if (o.getValue() > maxListChartRevenueArea) {
                    maxListChartRevenueArea = o.getValue();
                }
            }
            maxListChartRevenueArea = (maxListChartRevenueArea / 100 + 1) * 100;

            List<ChartDTO> listChartAvgStar = fd.getChartAvgStar(start, day);

            request.setAttribute("listChartAvgStar", listChartAvgStar);
            request.setAttribute("listChartRevenueArea", listChartRevenueArea);
            request.setAttribute("maxListChartRevenueArea", maxListChartRevenueArea);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
