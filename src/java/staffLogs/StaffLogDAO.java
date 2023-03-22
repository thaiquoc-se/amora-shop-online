/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package staffLogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import product.ProductDTO;
import utils.DBUtils;

/**
 *
 * @author thaiq
 */
public class StaffLogDAO {
    private static final String SHOW_LOG = "SELECT * FROM StaffLogs";
    private static final String INSET_LOG = "INSERT INTO StaffLogs (email, time_in) VALUES(?,?)";
    private static final String UPDATE_LOG = "UPDATE StaffLogs\n"
            + "SET time_out =?\n"
            + "WHERE email=?";
    public List<StaffLogDTO> getAllStaffLogs() throws SQLException{
        List<StaffLogDTO> log = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_LOG);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int logID = rs.getInt("log_id");
                    String email = rs.getString("email");
                    String timeIn = rs.getString("time_in");
                    String timeOut = rs.getString("time_out");
                    log.add(new StaffLogDTO(logID, email, timeIn, timeOut));
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
        return log;
    }
    public void insertLog(String email,String time_in) throws SQLException{
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Insert Product
                ptm = conn.prepareStatement(INSET_LOG);
                ptm.setString(1, email);
                ptm.setString(2, time_in);
                ptm.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void updateLog(String email,String time_out) throws SQLException{
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Insert Product
                ptm = conn.prepareStatement(UPDATE_LOG);
                ptm.setString(1, time_out);
                ptm.setString(2, email);
                ptm.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
