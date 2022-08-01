package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Blog;

public class BlogDBContext extends DBContext {

//    private String sqlStatement;
//    public BlogDBContext() {
//        sqlStatement = "SELECT [id]\n"
//                + "      ,[title]\n"
//                + "      ,[content]\n"
//                + "      ,[createdDate]\n"
//                + "      ,[categoryId]\n"
//                + "      ,[isActive]\n"
//                + "      ,[createdBy]\n"
//                + "      ,[image]\n"
//                + "  FROM [SWP].[dbo].[Blog]\n";
//    }
//
//    /**
//     * Option = 0 : default, Option = 1 : get post by id, Option = 2 : get post
//     * by title, Option = 3 : get post by category
//     *
//     * @param option
//     * @param value
//     */
//    public BlogDBContext(int option, String value) {
//        sqlStatement = "SELECT [id]\n"
//                + "      ,[title]\n"
//                + "      ,[content]\n"
//                + "      ,[createdDate]\n"
//                + "      ,[categoryId]\n"
//                + "      ,[isActive]\n"
//                + "      ,[createdBy]\n"
//                + "      ,[image]\n"
//                + "  FROM [SWP].[dbo].[Blog]\n";
//        switch (option) {
//            case 1:
//                try {
//                    int id = Integer.parseInt(value);
//                    sqlStatement += "WHERE id = " + id;
//                } catch (Exception e) {
//                }
//                break;
//            case 2:
//                sqlStatement += "WHERE title LIKE N'%" + value + "%'";
//                break;
//            case 3:
//                try {
//                    int id = Integer.parseInt(value);
//                    sqlStatement += "WHERE categoryId = " + id;
//                } catch (Exception e) {
//                }
//
//                break;
//        }
//    }
    public ArrayList<Blog> find() {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sqlStatement = "SELECT [id]\n"
                + "      ,[title]\n"
                + "      ,[content]\n"
                + "      ,[createdDate]\n"
                + "      ,[categoryId]\n"
                + "      ,[isActive]\n"
                + "      ,[createdBy]\n"
                + "      ,[image]\n"
                + "  FROM [SWP].[dbo].[Blog]\n";
        try {
            System.out.println(sqlStatement);
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setCreatedDate(rs.getDate("createdDate"));
                blog.setCategoryId(rs.getInt("categoryId"));
                blog.setIsActived(rs.getBoolean("isActive"));
                blog.setCreatedBy(rs.getString("createdBy"));
                blog.setImage(rs.getString("image"));
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public Blog find(int id) {
        String sqlStatement = "SELECT [id]\n"
                + "      ,[title]\n"
                + "      ,[content]\n"
                + "      ,[createdDate]\n"
                + "      ,[categoryId]\n"
                + "      ,[isActive]\n"
                + "      ,[createdBy]\n"
                + "      ,[image]\n"
                + "  FROM [SWP].[dbo].[Blog]\n"
                + "  WHERE [id] = " + id;
        try {
            System.out.println(sqlStatement);
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setCreatedDate(rs.getDate("createdDate"));
                blog.setCategoryId(rs.getInt("categoryId"));
                blog.setIsActived(rs.getBoolean("isActive"));
                blog.setCreatedBy(rs.getString("createdBy"));
                blog.setImage(rs.getString("image"));
                return blog;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    // sua sau
    public ArrayList<Blog> findLastestByNumOfBlog(int numberOfBlog) {

        ArrayList<Blog> blogList = new ArrayList<>();
        try {
            String sql = "SELECT TOP " + numberOfBlog + " [id]\n"
                    + "      ,[title]\n"
                    + "      ,[content]\n"
                    + "      ,[createdDate]\n"
                    + "      ,[categoryId]\n"
                    + "      ,[isActive]\n"
                    + "      ,[createdBy]\n"
                    + "      ,[image]\n"
                    + "FROM [SWP].[dbo].[Blog]\n"
                    + "ORDER BY [createdDate] DESC\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setCreatedDate(rs.getDate("createdDate"));
                blog.setCategoryId(rs.getInt("categoryId"));
                blog.setIsActived(true);
                blog.setCreatedBy(rs.getString("createdBy"));
                blog.setImage(rs.getString("image"));
                blogList.add(blog);
            }
            return blogList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     *
     * @param pageSize
     * @param page
     * @return
     */
    public ArrayList<Blog> findByPage(int pageNumber, int rowsOfPage) {
        ArrayList<Blog> blogList = new ArrayList<>();
        String sqlStatement = "SELECT [id]\n"
                + "      ,[title]\n"
                + "      ,[content]\n"
                + "      ,[createdDate]\n"
                + "      ,[categoryId]\n"
                + "      ,[createdBy]\n"
                + "      ,[isActive]\n"
                + "      ,[image]\n"
                + "  FROM [dbo].[Blog]\n"
                + "  ORDER BY (SELECT NULL) \n"
                + "  OFFSET (" + pageNumber + "-1)*" + rowsOfPage + " ROWS\n"
                + "  FETCH NEXT " + rowsOfPage + " ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setCreatedDate(rs.getDate("createdDate"));
                blog.setCategoryId(rs.getInt("categoryId"));
                blog.setIsActived(true);
                blog.setCreatedBy(rs.getString("createdBy"));
                blog.setImage(rs.getString("image"));
                blogList.add(blog);
            }
            return blogList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return blogList;
    }

    public ArrayList<Blog> findByFilter(String search, String category) {
        ArrayList<Blog> blogList = new ArrayList<>();
        String sqlStatement = "SELECT [id]\n"
                + "      ,[title]\n"
                + "      ,[content]\n"
                + "      ,[createdDate]\n"
                + "      ,[categoryId]\n"
                + "      ,[createdBy]\n"
                + "      ,[isActive]\n"
                + "      ,[image]\n"
                + "  FROM [dbo].[Blog]\n"
                + "  WHERE [categoryId] like N'%" + category + "%'\n"
                + "  and [title] like N'%" + search + "%'\n"
                + "  COLLATE SQL_Latin1_General_CP1_CI_AS\n";
        System.out.println(sqlStatement);
        try {
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setCreatedDate(rs.getDate("createdDate"));
                blog.setCategoryId(rs.getInt("categoryId"));
                blog.setIsActived(true);
                blog.setCreatedBy(rs.getString("createdBy"));
                blog.setImage(rs.getString("image"));
                blogList.add(blog);
            }
            return blogList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return blogList;
    }

    public ArrayList<Blog> findByFilter(String search, String category, int pageNumber, int rowsOfPage) {
        ArrayList<Blog> blogList = new ArrayList<>();
        String sqlStatement = "SELECT [id]\n"
                + "      ,[title]\n"
                + "      ,[content]\n"
                + "      ,[createdDate]\n"
                + "      ,[categoryId]\n"
                + "      ,[createdBy]\n"
                + "      ,[isActive]\n"
                + "      ,[image]\n"
                + "  FROM [dbo].[Blog]\n"
                + "  WHERE [categoryId] like '%" + category + "%'\n"
                + "  and [title] like '%" + search + "%'\n"
                + "  ORDER BY (SELECT NULL) \n"
                + "  OFFSET (" + pageNumber + "-1)*" + rowsOfPage + " ROWS\n"
                + "  FETCH NEXT " + rowsOfPage + " ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setCreatedDate(rs.getDate("createdDate"));
                blog.setCategoryId(rs.getInt("categoryId"));
                blog.setIsActived(true);
                blog.setCreatedBy(rs.getString("createdBy"));
                blog.setImage(rs.getString("image"));
                blogList.add(blog);
            }
            return blogList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return blogList;
    }
//    public int getNoOfRecords() {
//        String statement = "SELECT COUNT(*) AS count FROM ("
//                + sqlStatement
//                + ") Temp";
//        try {
//            PreparedStatement stm = connection.prepareStatement(statement);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("count");
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return 0;
//    }
}
