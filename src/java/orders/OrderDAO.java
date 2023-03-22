/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import Chart.ChartDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author long
 */
public class OrderDAO {
    private static final String CHECK_PRODUCT_ORDER_BY_USERID = "select * from Orders o join OrderDetail od on o.order_id = od.order_id where o.user_id = ? and od.product_id = ?";
    public OrderDTO getOrder(int orderID) throws SQLException {
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from Orders where order_id = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, orderID);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    orderID = rs.getInt("order_id");
                    String fullName = rs.getString("full_name");
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String orderDate = rs.getString("order_date");
                    int userID = rs.getInt("user_id");
                    float totalPrice = rs.getFloat("total_price");
                    String note = rs.getString("note");

                    order = new OrderDTO(orderID, fullName, address, status, orderDate, userID, totalPrice, note);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return order;
    }

    public void updateOrderStatus(int orderID, String status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Orders]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE order_id = ?";
                st = con.prepareStatement(sql);
                st.setString(1, status);
                st.setInt(2, orderID);

                st.executeUpdate();
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    public boolean removeOrder(int orderID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Xóa các bản ghi trên bảng Payment liên quan đến đơn hàng có orderID
                String deletePaymentSql = "DELETE FROM Payment WHERE order_id = ?";
                st = con.prepareStatement(deletePaymentSql);
                st.setInt(1, orderID);
                st.executeUpdate();

                // Xóa bản ghi trên bảng Orders có orderID
                String deleteOrderSql = "DELETE FROM Orders WHERE order_id = ?";
                st = con.prepareStatement(deleteOrderSql);
                st.setInt(1, orderID);
                int effectedRow = st.executeUpdate();
                if (effectedRow > 0) {
                    result = true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return result;
    }

    public boolean updateOrderTotal(float finalTotal, int orderID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Orders]\n"
                        + "   SET [total_price] = ?\n"
                        + " WHERE order_id = ?";
                st = con.prepareStatement(sql);
                st.setFloat(1, finalTotal);
                st.setInt(2, orderID);
                int effectedRows = st.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return result;
    }

    public int createOrder(String fullName, String address, String status, Date date, int userID, float total, String note) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Orders] ([full_name], [address], [status], [order_date], [user_id], [total_price],[note])\n"
                        + "VALUES (?, ?, ?, ?, ?, ?,?)";
                st = con.prepareStatement(sql);
                st.setString(1, fullName);
                st.setString(2, address);
                st.setString(3, status);
                st.setDate(4, date);
                st.setInt(5, userID);
                st.setFloat(6, 0);
                st.setString(7, note);
                //execute
                int effectedRow = st.executeUpdate();
                if (effectedRow > 0) {
                    String sql2 = "SELECT TOP 1 order_id FROM [Orders] ORDER BY order_id desc";
                    st2 = con.prepareStatement(sql2);
                    rs = st2.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("order_id");
                    }
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
            if (st2 != null) {
                st2.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

    public List<OrderDTO> getListOrderByUserId(int userID) throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from Orders where user_id = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, userID);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("order_id");
                    String fullName = rs.getString("full_name");
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String orderDate = rs.getString("order_date");
                    userID = rs.getInt("user_id");
                    float totalPrice = rs.getFloat("total_price");
                    String note = rs.getString("note");

                    order = new OrderDTO(orderID, fullName, address, status, orderDate, userID, totalPrice, note);
                    list.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean updateStatusOder(String status, int orderID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Orders]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE order_id = ?";
                st = con.prepareStatement(sql);
                st.setString(1, status);
                st.setInt(2, orderID);
                int effectedRows = st.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return result;
    }

    public List<OrderDTO> getOrderProcessing() throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from Orders where status = 'PROCESSING'";
                ptm = conn.prepareStatement(sql);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("order_id");
                    String fullName = rs.getString("full_name");
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String orderDate = rs.getString("order_date");
                    int userID = rs.getInt("user_id");
                    float totalPrice = rs.getFloat("total_price");
                    String note = rs.getString("note");

                    order = new OrderDTO(orderID, fullName, address, status, orderDate, userID, totalPrice, note);
                    list.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<OrderDTO> getOrderComplete() throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from Orders where status != 'PROCESSING'";
                ptm = conn.prepareStatement(sql);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("order_id");
                    String fullName = rs.getString("full_name");
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String orderDate = rs.getString("order_date");
                    int userID = rs.getInt("user_id");
                    float totalPrice = rs.getFloat("total_price");
                    String note = rs.getString("note");

                    order = new OrderDTO(orderID, fullName, address, status, orderDate, userID, totalPrice, note);
                    list.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean checkOrderByUserID(int userID, int productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PRODUCT_ORDER_BY_USERID);
                ptm.setInt(1, userID);
                ptm.setInt(2, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
//chart
//

//    public List<ChartDTO> getChartRevenueArea(int day, String start) {
//        List<ChartDTO> list = new ArrayList<>();
//
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        for (int i = 0; i < day; i++) {
//            int value = 0;
//            String sql = "select sum(total_price) from [Orders] where order_date <= DATEADD(DAY, ?, ?) and order_date >= ?";
//            try {
//                PreparedStatement st = conn.prepareStatement(sql);
//                st.setInt(1, i);
//                st.setString(2, start);
//                st.setString(3, start);
//                rs = st.executeQuery();
//                while (rs.next()) {
//                    value = rs.getInt(1);
//                }
//                sql = "select  DATEADD(DAY, ?, ?)";
//                st = conn.prepareStatement(sql);
//                st.setInt(1, i);
//                st.setString(2, start);
//                rs = st.executeQuery();
//                while (rs.next()) {
//                    ChartDTO chart = new ChartDTO();
//                    chart.setDate(rs.getDate(1));
//                    chart.setValue(value);
//                    list.add(chart);
//                }
//
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//        return list;
//    }
    public List<ChartDTO> getChartRevenueArea(String start, int day) throws SQLException, ClassNotFoundException {
        List<ChartDTO> list = new ArrayList<>();

        PreparedStatement ptm = null;
        ResultSet rs = null;
        try ( Connection conn = DBUtils.getConnection()) {
            for (int i = 0; i < day; i++) {
                int value = 0;
                String sql = "select sum(total_price) from [Orders] where order_date <= DATEADD(DAY, ?, ?) and order_date >= ? and status = 'FINISHED'";
                try {
                    PreparedStatement st = conn.prepareStatement(sql);
                    st.setInt(1, i);
                    st.setString(2, start);
                    st.setString(3, start);
                    rs = st.executeQuery();
                    while (rs.next()) {
                        value = rs.getInt(1);
                    }
                    sql = "select  DATEADD(DAY, ?, ?)";
                    st = conn.prepareStatement(sql);
                    st.setInt(1, i);
                    st.setString(2, start);
                    rs = st.executeQuery();
                    while (rs.next()) {
                        ChartDTO chart = new ChartDTO();
                        chart.setDate(rs.getDate(1));
                        chart.setValue(value);
                        list.add(chart);
                    }

                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            return list;
        }

    }
}
