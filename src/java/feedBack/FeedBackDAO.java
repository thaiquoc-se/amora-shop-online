/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedBack;

import Chart.ChartDTO;
import feedBackImage.FeedBackImageDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author long
 */
public class FeedBackDAO {

//    public void insertNewFeedBack(String feedBack, String fullName, int rated_star, String status, int userID, int productID, String image) throws ClassNotFoundException {
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement("INSERT INTO [dbo].[FeedBack]\n"
//                        + "([feed_back]\n"
//                        + ",[full_name]\n"
//                        + ",[rated_star]\n"
//                        + ",[status]\n"
//                        + ",[user_id]\n"
//                        + ",[product_id]\n"
//                        + ",[image])\n"
//                        + "VALUES\n"
//                        + "(?,?,?,?,?,?,?)");
//                ptm.setString(1, feedBack);
//                ptm.setString(2, fullName);
//
//                ptm.setInt(3, rated_star);
//                ptm.setString(4, status);
//                ptm.setInt(5, userID);
//                ptm.setInt(6, productID);
//                ptm.setString(7, image);
//
//                ptm.executeUpdate();
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
    public List<FeedBackDTO> getListFeedBackByUserId(int productID, int page, int PAGE_SIZE) throws SQLException, ClassNotFoundException {
        List<FeedBackDTO> list = new ArrayList<>();
        FeedBackDTO feedback = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT f.feedBack_id, f.feed_back, f.full_name, f.rated_star, f.status, f.user_id, f.product_id, f.image, f.date, i.feedBack_image \n"
                        + "  FROM Feedback f \n"
                        + "  LEFT JOIN FeedBackImage i ON f.feedBack_id = i.feedBack_id \n"
                        + "  WHERE f.product_id = ? AND f.status = 'true'\n"
                        + "  ORDER BY f.date DESC offset (?-1)*? row fetch next ? rows only";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, productID);
                ptm.setInt(2, page);
                ptm.setInt(3, PAGE_SIZE);
                ptm.setInt(4, PAGE_SIZE);
                rs = ptm.executeQuery();
                while (rs.next()) {

                    int feedBackID = rs.getInt("feedBack_id");
                    String feedBack = rs.getString("feed_back");
                    String fullName = rs.getString("full_name");
                    int rated_star = rs.getInt("rated_star");
                    String status = rs.getString("status");
                    int userID = rs.getInt("user_id");
                    productID = rs.getInt("product_id");
                    String image = rs.getString("image");
                    Date date = rs.getDate("date");
                    String feedBackImage = rs.getString("feedBack_image");

                    feedback = new FeedBackDTO(feedBackID, fullName, rated_star, feedBack, image, status, productID, userID, date, feedBackImage);
                    list.add(feedback);
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
// sua database rated_star thanh float r chuyen laij thanh public float để lấy số sao rtung bình 

    public float getAvgRatedStarByProductID(int productID) throws SQLException {

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select AVG(rated_star) from Feedback where product_id = ? AND status = 'true'";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, productID);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    // sua o day nữa 
                    return rs.getFloat(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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

        return 0;
    }

    public int getTotalFeedBackByProductID(int productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select COUNT(feedBack_id) from Feedback where product_id = ? AND status = 'true'";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, productID);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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

        return 0;
    }

//    void insertNewFeedsBack(String feedBack, String fullName, int rated_star, String status, int userID, int productID, String image, FeedBackImageDTO fbImage) {
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement("INSERT INTO [dbo].[FeedBack]\n"
//                        + "([feed_back]\n"
//                        + ",[full_name]\n"
//                        + ",[rated_star]\n"
//                        + ",[status]\n"
//                        + ",[user_id]\n"
//                        + ",[product_id]\n"
//                        + ",[image])\n"
//                        + "VALUES\n"
//                        + "(?,?,?,?,?,?,?)");
//                ptm.setString(1, feedBack);
//                ptm.setString(2, fullName);
//
//                ptm.setInt(3, rated_star);
//                ptm.setString(4, status);
//                ptm.setInt(5, userID);
//                ptm.setInt(6, productID);
//                ptm.setString(7, image);
//
//                ptm.executeUpdate();
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//    public boolean insertNewFeedBack(String feedBack, String fullName, int rated_star, String status, int userID, int productID, String image, FeedBackImageDTO fbImage) throws SQLException {
//        boolean checkUpdate = false;
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                // Update Product
//                ptm = conn.prepareStatement("INSERT INTO [dbo].[FeedBack]\n"
//                        + "([feed_back]\n"
//                        + ",[full_name]\n"
//                        + ",[rated_star]\n"
//                        + ",[status]\n"
//                        + ",[user_id]\n"
//                        + ",[product_id]\n"
//                        + ",[image])\n"
//                        + "VALUES\n"
//                        + "(?,?,?,?,?,?,?)");
//                ptm.setString(1, feedBack);
//                ptm.setString(2, fullName);
//
//                ptm.setInt(3, rated_star);
//                ptm.setString(4, status);
//                ptm.setInt(5, userID);
//                ptm.setInt(6, productID);
//                ptm.setString(7, image);
//
//                checkUpdate = ptm.executeUpdate() > 0;
//                if (checkUpdate) {
//                    PreparedStatement ptm2 = conn.prepareStatement("INSERT INTO [dbo].[FeedBackImage] ([feedBack_image], [feedBack_id])\n"
//                            + "VALUES (?, ?)");
//                    ptm2.setString(1, fbImage.getFeedBackImage());
//                    ptm2.setInt(2, fbImage.getFeedBackID());
//                    checkUpdate = ptm2.executeUpdate() > 0;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return checkUpdate;
//    }
//    public boolean insertNewFeedBack(String feedBack, String fullName, int rated_star, String status, int userID, int productID, String image, FeedBackImageDTO fbImage) throws SQLException {
//    boolean checkUpdate = false;
//    Connection conn = null;
//    PreparedStatement ptm = null;
//    ResultSet rs = null;
//    try {
//        conn = DBUtils.getConnection();
//        if (conn != null) {
//            // Update Product
//            ptm = conn.prepareStatement("INSERT INTO [dbo].[FeedBack]\n"
//                    + "([feed_back]\n"
//                    + ",[full_name]\n"
//                    + ",[rated_star]\n"
//                    + ",[status]\n"
//                    + ",[user_id]\n"
//                    + ",[product_id]\n"
//                    + ",[image])\n"
//                    + "VALUES\n"
//                    + "(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS); // added RETURN_GENERATED_KEYS option
//            ptm.setString(1, feedBack);
//            ptm.setString(2, fullName);
//            ptm.setInt(3, rated_star);
//            ptm.setString(4, status);
//            ptm.setInt(5, userID);
//            ptm.setInt(6, productID);
//            ptm.setString(7, image);
//            checkUpdate = ptm.executeUpdate() > 0;
//            if (checkUpdate) {
//                // Retrieve generated feedBack_id value
//                rs = ptm.getGeneratedKeys();
//                if (rs.next()) {
//                    int feedBackID = rs.getInt(1);
//                    // Insert into FeedBackImage table using the retrieved feedBack_id value
//                    PreparedStatement ptm2 = conn.prepareStatement("INSERT INTO [dbo].[FeedBackImage] ([feedBack_image], [feedBack_id])\n"
//                            + "VALUES (?, ?)");
//                    ptm2.setString(1, fbImage.getFeedBackImage());
//                    ptm2.setInt(2, feedBackID); // use the retrieved feedBack_id value
//                    checkUpdate = ptm2.executeUpdate() > 0;
//                }
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        if (rs != null) {
//            rs.close();
//        }
//        if (ptm != null) {
//            ptm.close();
//        }
//        if (conn != null) {
//            conn.close();
//        }
//    }
//    return checkUpdate;
//}
    public int insertNewFeedBackAndGetID(String feedBack, String fullName, int rated_star, String status, int userID, int productID, String image, FeedBackImageDTO fbImage) throws SQLException {
        int feedBackID = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("INSERT INTO [dbo].[FeedBack]\n"
                        + "([feed_back]\n"
                        + ",[full_name]\n"
                        + ",[rated_star]\n"
                        + ",[status]\n"
                        + ",[user_id]\n"
                        + ",[product_id]\n"
                        + ",[image])\n"
                        + "VALUES\n"
                        + "(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ptm.setString(1, feedBack);
                ptm.setString(2, fullName);
                ptm.setInt(3, rated_star);
                ptm.setString(4, status);
                ptm.setInt(5, userID);
                ptm.setInt(6, productID);
                ptm.setString(7, image);
                ptm.executeUpdate();
                // Get the generated feedBack_id value
                rs = ptm.getGeneratedKeys();
                if (rs.next()) {
                    feedBackID = rs.getInt(1);
                    // Insert into FeedBackImage table using the retrieved feedBack_id value
                    PreparedStatement ptm2 = conn.prepareStatement("INSERT INTO [dbo].[FeedBackImage] ([feedBack_image], [feedBack_id])\n"
                            + "VALUES (?, ?)");
                    ptm2.setString(1, fbImage.getFeedBackImage());
                    ptm2.setInt(2, feedBackID);
                    ptm2.executeUpdate();
                    ptm2.close();
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
        return feedBackID;
    }

    public String getFeedBackImage(int feedBackID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String feedBackImage = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT [feedBack_image] FROM [dbo].[FeedBackImage] WHERE [feedBack_id] = ?");
                ptm.setInt(1, feedBackID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    feedBackImage = rs.getString("feedBack_image");
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
        return feedBackImage;
    }

    List<FeedBackDTO> getAllFeedBackByStatusFalse() throws SQLException {
        List<FeedBackDTO> list = new ArrayList<>();
        FeedBackDTO feedback = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP (1000) f.[feedBack_id], f.[feed_back], f.[full_name], f.status, f.[rated_star], f.[date], fi.[feedBack_image], f.[user_id], f.[image], p.[product_id]\n"
                        + "FROM [ShopNuocHoa].[dbo].[FeedBack] f\n"
                        + "LEFT JOIN [ShopNuocHoa].[dbo].[FeedBackImage] fi ON f.[feedBack_id] = fi.[feedBack_id]\n"
                        + "LEFT JOIN [ShopNuocHoa].[dbo].[Product] p ON f.[product_id] = p.[product_id] where f.status = 'false'";
                ptm = conn.prepareStatement(sql);

                rs = ptm.executeQuery();
                while (rs.next()) {

                    int feedBackID = rs.getInt("feedBack_id");
                    String feedBack = rs.getString("feed_back");
                    String fullName = rs.getString("full_name");
                    int rated_star = rs.getInt("rated_star");
                    String status = rs.getString("status");
                    int userID = rs.getInt("user_id");
                    int productID = rs.getInt("product_id");
                    String image = rs.getString("image");
                    Date date = rs.getDate("date");
                    String feedBackImage = rs.getString("feedBack_image");

                    feedback = new FeedBackDTO(feedBackID, fullName, rated_star, feedBack, image, status, productID, userID, date, feedBackImage);
                    list.add(feedback);
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

    public void updatStatusFeedBackByID(int feedBackID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE [dbo].[FeedBack] SET [status] = 'true' WHERE [feedBack_id] = ?");

                ptm.setInt(1, feedBackID);
                int rowsAffected = ptm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Feedback status updated successfully.");
                } else {
                    System.out.println("No feedback found with the provided feedback ID.");
                }
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

    public void deleteFeedBackByID(int feedBackID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Delete corresponding records in FeedBackImage table first
                PreparedStatement ptm2 = conn.prepareStatement("DELETE FROM [dbo].[FeedBackImage] WHERE [feedBack_id] = ?");
                ptm2.setInt(1, feedBackID);
                ptm2.executeUpdate();
                ptm2.close();

                // Delete record in FeedBack table
                ptm = conn.prepareStatement("DELETE FROM [dbo].[FeedBack] WHERE [feedBack_id] = ?");
                ptm.setInt(1, feedBackID);
                int rowsAffected = ptm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Feedback deleted successfully.");
                } else {
                    System.out.println("No feedback found with the provided feedback ID.");
                }
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

    public List<ChartDTO> getChartAvgStar(String start, int day) throws SQLException, ClassNotFoundException {
        List<ChartDTO> list = new ArrayList<>();

        String sql = "select CAST(AVG(rated_star) AS DECIMAL(10,1)) from FeedBack where date < DATEADD(DAY, ?, ?) and status = 'true'";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql)) {
            for (int i = 1; i <= day; i++) {
                float value = 0;
                st.setInt(1, i);
                st.setString(2, start);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        value = rs.getFloat(1);
                    }
                }
                sql = "select DATEADD(DAY, ?, ?)";
                try (PreparedStatement st2 = conn.prepareStatement(sql)) {
                    st2.setInt(1, i);
                    st2.setString(2, start);
                    try (ResultSet rs2 = st2.executeQuery()) {
                        while (rs2.next()) {
                            ChartDTO chart = new ChartDTO();
                            chart.setDate(rs2.getDate(1));
                            chart.setValue(value);
                            list.add(chart);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
