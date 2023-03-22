package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author thaiq
 */
public class ProductDAO {

    public List<ProductDTO> items;

    public List<ProductDTO> getItems() {
        return items;
    }

    private static final String GET_ALL_PRODUCT = "SELECT [Product].*, [ProductImage].image\n"
            + "FROM [Product]\n"
            + "INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id;";

    private static final String GET_ALL_PRODUCT_BY_CATEGORYID = "SELECT [Product].*, [ProductImage].image FROM [Product] INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id where category_id = ?";

    private static final String GET_ALL_PRODUCT_BY_PAGGINGSIZE = "SELECT [Product].*, [ProductImage].image\n"
            + "FROM [Product]\n"
            + "INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id order by price offset (?-1)*? row fetch next ? rows only";

    private static final String COUNT_ALL_PRODUCT = "select count(product_id) from Product";

    private static final String GET_ALL_PRODUCT_BY_ID = "SELECT [Product].*, [ProductImage].image\n"
            + "FROM [Product]\n"
            + "INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id\n"
            + "where [Product].product_id = ?";

    private static final String DELETE = "DELETE FROM [dbo].[ProductImage] WHERE [product_id] = ?\n"
            + "DELETE FROM [dbo].[Hashtag] WHERE [product_id] = ?\n"
            + "DELETE FROM [dbo].[Product] WHERE [product_id] = ?";
    private static final String INSERT = "INSERT INTO [dbo].[Product]([product_name],[quantity] , [status],[description],[capacity],[brand],[price],[category_id]) VALUES(?,?,?,?,?,?,?,?)";

    private static final String GET_NEWEST_PRODUCT_INSERTED = "  SELECT TOP 1  [product_id] FROM [ShopNuocHoa].[dbo].[Product] ORDER BY [product_id] DESC";

    private static final String INSERT2 = "INSERT INTO [dbo].[ProductImage]([image],[product_id]) VALUES (?,?)";

    private static final String UPDATE = "UPDATE [dbo].[Product] SET [product_name] = ?,[quantity] =?, [status]=?,[description]=?, [capacity]=?, [brand]=?,[price]=?,[category_id]=? where [product_id] like ?";

    private static final String UPDATE2 = "UPDATE [dbo].[ProductImage] SET [image] = ? where [product_id] like ?";

    private static final String SEARCH = "SELECT [Product].product_id, [Product].product_name, Product.quantity, Product.status, Product.description, Product.capacity, Product.brand, Product.price, c.[category_name], i.image,[dbo].[Product].category_id\n"
            + "FROM ([dbo].[Product] Inner Join [dbo].[Category] As c\n"
            + "On  [Product].category_id= c.category_id)\n"
            + "Inner Join [dbo].[ProductImage] As i\n"
            + "On Product.product_id = i.product_id\n"
            + "Where product_name like ?";
    private static final String SEARCH_PRODUCT_BY_ID_USER = "SELECT [Product].*,[Category].category_name, [ProductImage].image\n"
            + "FROM [Product]\n"
            + "INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id\n"
            + "INNER JOIN [Category] ON [Product].category_id = [Category].category_id\n"
            + "WHERE [Product].product_id = ?;";

    //play ground 
    private static final String SEARCH_NAME_PRODUCT_BY_PAGGINGSIZE = "SELECT [Product].*, [ProductImage].image\n"
            + "FROM [Product]\n"
            + "INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id\n"
            + "WHERE [Product].product_name LIKE ?\n"
            + "order by price offset (?-1)*? row fetch next ? rows only";
    private static final String GET_PRODUCT_BY_ID = "SELECT [Product].*, [ProductImage].image FROM [Product] INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id where [Product].product_id = ?";

    private static final String COUNT_PRODUCT_SEARCH_BY_NAME = "select count(product_id) from Product WHERE product_name LIKE ?";

    //moi
    private static final String NEW_ARRIVALS_PRODUCT_BY_PAGGINGSIZE = "  SELECT [Product].*, [ProductImage].image FROM [Product] INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id WHERE [Product].status = 'NEW' order by price offset (?-1)*? row fetch next ? rows only";

    private static final String COUNT_NEW_ARRIVALS_PRODUCT = "select count(product_id) from Product WHERE status = 'NEW'";

    private static final String SHOW_PRODUCT_BY_BRAND
            = "SELECT Product.*, ProductImage.image\n"
            + "FROM Product INNER JOIN ProductImage ON Product.product_id = ProductImage.product_id\n"
            + "WHERE brand = ? order by price offset (?-1)*? row fetch next ? rows only";

    private static final String SHOW_PRODUCT_BY_BRAND_AND_CATEGORY
            = "SELECT Product.*, ProductImage.image\n"
            + "FROM Product INNER JOIN ProductImage ON Product.product_id = ProductImage.product_id\n"
            + "WHERE brand = ? AND category_id = ? order by price offset (?-1)*? row fetch next ? rows only";

    private static final String SHOW_PRODUCT_BY_CATEGORY
            = "SELECT Product.*, ProductImage.image\n"
            + "FROM Product INNER JOIN ProductImage ON Product.product_id = ProductImage.product_id\n"
            + "WHERE category_id = ?";

    private static final String GET_PRODUCT_HOME_PAGE = "SELECT [Product].product_id, product_name, quantity, [Product].status, description, capacity, brand, price, category_id, [ProductImage].image \n"
            + "FROM Product \n"
            + "INNER JOIN ProductImage ON Product.product_id = ProductImage.product_id\n"
            + "WHERE product_name LIKE '%Infusion%'\n"
            + "OR product_name LIKE '%Amber intese%' \n"
            + "OR product_name LIKE '%di Fiori%' \n"
            + "OR product_name LIKE '%Chance Eau fr%'\n"
            + "OR product_name LIKE '%Mademoiselle%'\n"
            + "OR product_name LIKE '%Jasmine%'";

    private static final String COUNT_ALL_PRODUCT_BY_BRAND_AND_CATEGORY = "select count(product_id) from Product WHERE brand = ? AND category_id = ?";
    
    private static final String COUNT_ALL_PRODUCT_BY_BRAND = "select count(product_id) from Product WHERE brand = ?";
    
    private static final String COUNT_ALL_PRODUCT_BY_CATEGORY = "select count(product_id) from Product WHERE category_id = ?";
    
    private static final String GET_ALL_PRODUCT_BY_CATEGORYID_WITH_PAGGING = "SELECT [Product].*, [ProductImage].image FROM [Product] INNER JOIN [ProductImage] ON [Product].product_id = [ProductImage].product_id where category_id = ? order by price offset (?-1)*? row fetch next ? rows only";


    public List<ProductDTO> getProductHomePage() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_HOME_PAGE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    public List<ProductDTO> getAllProductByCategory(int cateID) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_PRODUCT_BY_CATEGORY);
                ptm.setInt(1, cateID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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
//sửa getAllProductByBrandAndCategory

    public List<ProductDTO> getAllProductByBrandAndCategory(String brand, int cateID, int page, int PAGE_SIZE) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_PRODUCT_BY_BRAND_AND_CATEGORY);
                ptm.setString(1, brand);
                ptm.setInt(2, cateID);
                ptm.setInt(3, page);
                ptm.setInt(4, PAGE_SIZE);
                ptm.setInt(5, PAGE_SIZE);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    
    public List<ProductDTO> getAllProductByBrand(String brand, int page, int PAGE_SIZE) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_PRODUCT_BY_BRAND);
                ptm.setString(1, brand);
                ptm.setInt(2, page);
                ptm.setInt(3, PAGE_SIZE);
                ptm.setInt(4, PAGE_SIZE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    public List<ProductDTO> getAllProductByStatusArrivals(int page, int PAGE_SIZE) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(NEW_ARRIVALS_PRODUCT_BY_PAGGINGSIZE);
                ptm.setInt(1, page);
                ptm.setInt(2, PAGE_SIZE);
                ptm.setInt(3, PAGE_SIZE);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    public int getNewArrivalsProductPage() throws SQLException {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_NEW_ARRIVALS_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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

        return 0;
    }

    public int getProductPage(String prdName) throws SQLException {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_PRODUCT_SEARCH_BY_NAME);
                ptm.setString(1, "%" + prdName + "%");

                rs = ptm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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

        return 0;
    }

    public boolean updateQuantity(String id, int quantity, int userQuans) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement st = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[Product]\n"
                        + "   SET [quantity] = ?\n"
                        + "     WHERE product_id = ?";
                st = conn.prepareStatement(sql);
                st.setInt(1, quantity - userQuans);
                st.setString(2, id);
                int effectedRow = st.executeUpdate();
                if (effectedRow > 0) {
                    result = true;
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return result;
    }

    public void showAllProducts() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                st = con.prepareStatement(GET_ALL_PRODUCT);
                rs = st.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    ProductDTO dto = new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image);
                    if (this.items == null) {
                        this.items = new ArrayList<>();
                    }
                    this.items.add(dto);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }

    }

    public ProductDTO getProductByIdTypeString(String product_id) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_BY_ID);
                ptm.setString(1, product_id);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    product = new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image);
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
        return product;
    }

    public List<ProductDTO> SearchNameProductsWithPagging(String prdName, int page, int PAGE_SIZE) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_NAME_PRODUCT_BY_PAGGINGSIZE);
                ptm.setString(1, "%" + prdName + "%");
                ptm.setInt(2, page);
                ptm.setInt(3, PAGE_SIZE);
                ptm.setInt(4, PAGE_SIZE);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    //End of playground
    public List<ProductDTO> getProductDetailByID(int product_id) throws SQLException, ClassNotFoundException {

        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(SEARCH_PRODUCT_BY_ID_USER);
                stm.setInt(1, product_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    
        public List<ProductDTO> getAllProductByCategoryId(int categoryID) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT_BY_CATEGORYID);
                ptm.setInt(1, categoryID);
                
                  

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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
    
    public List<ProductDTO> getAllProductByCategoryIdWithPagging(int categoryID, int page, int PAGE_SIZE) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT_BY_CATEGORYID_WITH_PAGGING);
                ptm.setInt(1, categoryID);
                 ptm.setInt(2, page);
                  ptm.setInt(3, PAGE_SIZE);
                   ptm.setInt(4, PAGE_SIZE);
                  

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    public List<ProductDTO> getProductsWithPagging(int page, int PAGE_SIZE) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT_BY_PAGGINGSIZE);
                ptm.setInt(1, page);
                ptm.setInt(2, PAGE_SIZE);
                ptm.setInt(3, PAGE_SIZE);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    int categoryID = rs.getInt("category_id");
                    String image = rs.getString("image");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    public int getTotalProducts() throws SQLException {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_PRODUCT);

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

    public ProductDTO getProductById(int productID) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT_BY_ID);
                ptm.setInt(1, productID);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    int categoryID = rs.getInt("category_id");
                    product = new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image);
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
        return product;
    }
//    public int getTotalProducts() throws SQLException {
//
//        ProductDTO product = null;
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(COUNT_ALL_PRODUCT);
//
//                rs = ptm.executeQuery();
//                while (rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//        return 0;
//    }

    public boolean delete(String productID) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, productID);
                ptm.setString(2, productID);
                ptm.setString(3, productID);
                checkDelete = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {

        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkDelete;
    }

    public boolean insert(ProductDTO product, ProductImageDTO productImage) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Insert Product
                ptm = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ptm.setString(1, product.getName());
                ptm.setInt(2, product.getQuantity());
                ptm.setString(3, product.getStatus());
                ptm.setString(4, product.getDescription());
                ptm.setString(5, product.getCapacity());
                ptm.setString(6, product.getBrand());
                ptm.setFloat(7, product.getPrice());
                ptm.setInt(8, product.getCategoryID());
                checkInsert = ptm.executeUpdate() > 0;
                if (checkInsert) {
                    //Lay ID cua cai product vua insert vo
                    PreparedStatement ptm2 = conn.prepareStatement(GET_NEWEST_PRODUCT_INSERTED);
                    ResultSet rs = ptm2.executeQuery();
                    if (rs.next()) {
                        // Insert ID vua lay + link image vo table ProductImage
                        int newestKey = rs.getInt("product_id");
                        PreparedStatement ptm3 = conn.prepareStatement(INSERT2);
                        ptm3.setString(1, productImage.getImage());
                        ptm3.setInt(2, newestKey);
                        checkInsert = ptm3.executeUpdate() > 0;
                    }
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
        return checkInsert;
    }

    public boolean update(ProductDTO product, ProductImageDTO productImage) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Update Product
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getName());
                ptm.setInt(2, product.getQuantity());
                ptm.setString(3, product.getStatus());
                ptm.setString(4, product.getDescription());
                ptm.setString(5, product.getCapacity());
                ptm.setString(6, product.getBrand());
                ptm.setFloat(7, product.getPrice());
                ptm.setInt(8, product.getCategoryID());
                ptm.setInt(9, product.getProductID());
                checkUpdate = ptm.executeUpdate() > 0;
                if (checkUpdate) {
                    PreparedStatement ptm2 = conn.prepareStatement(UPDATE2);
                    ptm2.setString(1, productImage.getImage());
                    ptm2.setInt(2, product.getProductID());
                    checkUpdate = ptm2.executeUpdate() > 0;
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
        return checkUpdate;
    }

    public List<ProductDTO> getListProducts() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("Select P.*,I.image\n"
                        + "from dbo.Product P Inner join dbo.ProductImage I ON P.product_id = I.product_id");
                while (rs.next()) {
                    int productID = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String brand = rs.getString("brand");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    int categoryID = rs.getInt("category_id");
                    list.add(new ProductDTO(productID, name, quantity, status, description, capacity, brand, price, categoryID, image));
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

    public int getTotalProductsByBrandAndCategory(String brand, int cateID) throws SQLException {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_PRODUCT_BY_BRAND_AND_CATEGORY);
                ptm.setString(1, brand);
                ptm.setInt(2, cateID);

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
      public int getTotalProductsByBrand(String brand) throws SQLException {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_PRODUCT_BY_BRAND);
                ptm.setString(1, brand);
               

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
      
      public int getTotalProductsByCategory(int CateID) throws SQLException {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_PRODUCT_BY_CATEGORY);
                ptm.setInt(1, CateID);
               

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
}

