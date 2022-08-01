/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.dai;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dai.subject;

/**
 *
 * @author HDC
 */
public class subjectDAO extends DBContext {
    PreparedStatement ps = null;
    ResultSet rs= null;
    
    public List<subject> getAllSubject() {
        List<subject> list = new ArrayList<>();
        //cau lenh sql
        String sql = "select * from course";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            // thực thi câu lệnh sql và lưu kết quả vào bảng
            ResultSet rs = pr.executeQuery();
            //lặp theo dòng của bảng
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                boolean isActive = rs.getBoolean(3);
                String created_by = rs.getString(4);
                String description = rs.getString(5);
                int categoryid = rs.getInt(6);
                String img = rs.getString(7);

                subject s = new subject(id, name, isActive, created_by, description, categoryid, img);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return list;
    }
    
    public List<subject> SearchSubject(String nameSearch) {
        List<subject> list = new ArrayList<>();
        //cau lenh sql
        String sql = "select * from course\n" +
                    "where course.name like '%"+nameSearch+"%'";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            // thực thi câu lệnh sql và lưu kết quả vào bảng
            ResultSet rs = pr.executeQuery();
            //lặp theo dòng của bảng
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                boolean isActive = rs.getBoolean(3);
                String created_by = rs.getString(4);
                String description = rs.getString(5);
                int categoryid = rs.getInt(6);
                String img = rs.getString(7);

                subject s = new subject(id, name, isActive, created_by, description, categoryid, img);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return list;
    }
    
    public void addSubject(subject s) {

        //cau lenh sql
        String sql = "INSERT INTO [dbo].[course]\n" +
                        "           ([name]\n" +
                    "           ,[isActive]\n" +
                    "           ,[created_by]\n" +
                    "           ,[description]\n" +
                    "           ,[categoryId]\n" +
                    "           ,[img])\n" +
                    "     VALUES\n" +
                    "           ('"+s.getName()+"','"+s.isIsActive()+"','"+s.getCreated_by()+"','"+s.getDescription()+"','"+s.getCategoryid()+"','"+s.getImg()+"')";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            //chạy câu lệnh thao thác đến dữ liệu add ,update ,delete
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }
    
    public void deleteSubject(int id) {
        //cau lenh sql
        String sql = "DELETE FROM [dbo].[course]\n" +
                    "      WHERE id=?";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            //truyền tham số vào câu lệnh sql
            pr.setInt(1, id);
            //chạy câu lệnh thao thác đến dữ liệu add ,update ,delete
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public void updateActiveSubject(int id, boolean isActive) {
        //cau lenh sql
        String sql = "UPDATE [dbo].[Subject]\n" +
                    "   SET [isActive] = ?\n" +
                    " WHERE id=?\n" ;
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            //truyền tham số vào câu lệnh sql
            pr.setBoolean(1, isActive);
            pr.setInt(2, id);
            //chạy câu lệnh thao thác đến dữ liệu add ,update ,delete
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }
    
    
}
