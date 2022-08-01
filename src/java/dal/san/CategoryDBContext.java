package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Category;

public class CategoryDBContext extends DBContext {


    public ArrayList<Category> findAll() {

        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[categoryName]\n"
                    + "      ,[description]\n"
                    + "  FROM [SWP].[dbo].[Category]\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setDescription(rs.getString("description"));

                categoryList.add(category);
            }
            return categoryList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }


    public Category findById(int id) {

        try {
            String sql = "SELECT [id]\n"
                    + "      ,[categoryName]\n"
                    + "      ,[description]\n"
                    + "  FROM [SWP].[dbo].[Category]\n"
                    + "  WHERE id = " + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setDescription(rs.getString("description"));

                return category;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
