/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.donghieu;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.Role;
import model.User;

/**
 *
 * @author dell
 */
public class PostDBContext extends DBContext {

    public ArrayList<Blog> getBlogsByCategoryID(User user, int categoryId, int page, int PAGE_SIZE) {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Blog\n"
                    + "WHERE categoryId = ?\n"
                    + "ORDER BY id\n"
                    + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            if (user.getRole().getId() == 2) {
                sql = "SELECT * FROM dbo.Blog\n"
                        + "WHERE categoryId = ? and createdBy = ?\n"
                        + "ORDER BY id\n"
                        + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (user.getRole().getId() == 2) {
                stm.setInt(1, categoryId);
                stm.setString(2, user.getUserName());
                stm.setInt(3, page);
                stm.setInt(4, PAGE_SIZE);
                stm.setInt(5, PAGE_SIZE);
            } else {
                stm.setInt(1, categoryId);
                stm.setInt(2, page);
                stm.setInt(3, PAGE_SIZE);
                stm.setInt(4, PAGE_SIZE);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setContent(rs.getString(3));
                blog.setCreatedDate(rs.getDate(4));
                blog.setCategoryId(rs.getInt(5));
                blog.setCreatedBy(rs.getString(6));
                blog.setIsActived(rs.getBoolean(7));
                blog.setImage(rs.getString(8));
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Blog> getAllBlog(User user, int page, int PAGE_SIZE) {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Blog\n"
                    + "ORDER BY id\n"
                    + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            if (user.getRole().getId() == 2) {
                sql = "SELECT * FROM dbo.Blog\n"
                        + "where createdBy = ?\n"
                        + "ORDER BY id\n"
                        + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (user.getRole().getId() == 2) {
                stm.setString(1, user.getUserName());
                stm.setInt(2, page);     
                stm.setInt(3, PAGE_SIZE);
                stm.setInt(4, PAGE_SIZE);
            } else {
                stm.setInt(1, page);
                stm.setInt(2, PAGE_SIZE);
                stm.setInt(3, PAGE_SIZE);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setContent(rs.getString(3));
                blog.setCreatedDate(rs.getDate(4));
                blog.setCategoryId(rs.getInt(5));
                blog.setCreatedBy(rs.getString(6));
                blog.setIsActived(rs.getBoolean(7));
                blog.setImage(rs.getString(8));
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Blog> getBlogsBySearch(User user, String search, int page, int PAGE_SIZE) {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Blog\n"
                    + "WHERE FREETEXT(title,?)\n"
                    + "ORDER BY id\n"
                    + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            if (user.getRole().getId() == 2) {
                sql = "SELECT * FROM dbo.Blog\n"
                        + "WHERE FREETEXT(title,?) and  createdBy = ?\n"
                        + "ORDER BY id\n"
                        + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (user.getRole().getId() == 2) {
                stm.setString(1, search);
                stm.setString(2, user.getUserName());
                stm.setInt(3, page);
                stm.setInt(4, PAGE_SIZE);
                stm.setInt(5, PAGE_SIZE);
            } else {
                stm.setString(1, search);
                stm.setInt(2, page);
                stm.setInt(3, PAGE_SIZE);
                stm.setInt(4, PAGE_SIZE);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setContent(rs.getString(3));
                blog.setCreatedDate(rs.getDate(4));
                blog.setCategoryId(rs.getInt(5));
                blog.setCreatedBy(rs.getString(6));
                blog.setIsActived(rs.getBoolean(7));
                blog.setImage(rs.getString(8));
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getTotalRowByCategoryId(User user, int categoryId) {
        int totalRow = 0;
        try {
            String sql = "SELECT COUNT(*) FROM dbo.Blog\n"
                    + "WHERE categoryId = ?";
            if (user.getRole().getId() == 2) {
                sql = "SELECT COUNT(*) FROM dbo.Blog\n"
                        + "                    WHERE categoryId = ? and createdBy = ? ";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, categoryId);
            if (user.getRole().getId() == 2) {
                stm.setString(2, user.getUserName());
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalRow += rs.getInt(1);
            }
            return totalRow;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRow;
    }

    public int getTotalRow(User user) {
        int totalRow = 0;
        try {
            String sql = "SELECT COUNT(*) FROM dbo.Blog \n";
             if (user.getRole().getId() == 2) {
                 sql = "SELECT COUNT(*) FROM dbo.Blog WHERE createdBy = ?\n";
              
            }
            PreparedStatement stm = connection.prepareStatement(sql);
             if (user.getRole().getId() == 2) {
                stm.setString(1, user.getUserName());
              
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalRow += rs.getInt(1);
            }
            return totalRow;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRow;
    }

    public int getTotalRowBySearch(User user, String search) {
        int totalRow = 0;
        try {
            String sql = "SELECT COUNT(*) FROM dbo.Blog\n"
                    + "WHERE FREETEXT(title,?)";
            search = "\"" + search + "\"";

            if (user.getRole().getId() == 2) {
                sql = "SELECT COUNT(*) FROM dbo.Blog\n"
                        + "WHERE FREETEXT(title,?) and createdBy = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, search);
            if (user.getRole().getId() == 2) {
                stm.setString(2, user.getUserName());
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalRow += rs.getInt(1);
            }
            return totalRow;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRow;
    }

    public Blog getBlogById(int id) {
        Blog blog = new Blog();
        try {
            String sql = "SELECT * FROM blog\n"
                    + "WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                blog.setId(rs.getInt(1));
                blog.setTitle(rs.getString(2));
                blog.setContent(rs.getString(3));
                blog.setCreatedDate(rs.getDate(4));
                blog.setCategoryId(rs.getInt(5));
                blog.setCreatedBy(rs.getString(6));
                blog.setIsActived(rs.getBoolean(7));
                blog.setImage(rs.getString(8));
            }
            return blog;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateBlog(Blog blog) {
        String sql = "UPDATE [Blog]\n"
                + "   SET [title] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[categoryId] = ?\n"
                + "      ,[isActive] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE id = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, blog.getTitle());
            stm.setString(2, blog.getContent());
            stm.setInt(3, blog.getCategoryId());
            stm.setBoolean(4, blog.isIsActived());
            stm.setString(5, blog.getImage());
            stm.setInt(6, blog.getId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void addBlog(String title, String content, int categoryId, String image, String username) {
        String sql = "INSERT INTO [Blog]\n"
                + "           ([title]\n"
                + "           ,[content]\n"
                + "           ,[createdDate]\n"
                + "           ,[categoryId]\n"
                + "           ,[createdBy]\n"
                + "           ,[isActive]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,0\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, content);
            stm.setInt(3, categoryId);
            stm.setString(4, username);
            stm.setString(5, image);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
