/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.dai;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dai.slider;


/**
 *
 * @author HDC
 */
public class sliderDAO extends DBContext {
    
    PreparedStatement ps = null;
    ResultSet rs= null;
    
    public List<slider> getAllSlider() {
        List<slider> list = new ArrayList<>();
        //cau lenh sql
        String sql = "select * from Slider";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            // thực thi câu lệnh sql và lưu kết quả vào bảng
            ResultSet rs = pr.executeQuery();
            //lặp theo dòng của bảng
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String link = rs.getString(4);
                String imglink = rs.getString(5);
                boolean isActive = rs.getBoolean(6);

                slider s = new slider(id, title, content, link, imglink, isActive);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return list;
    }
    
    public void addSlider(slider s) {

        //cau lenh sql
        String sql = "insert Slider values ('"+s.getTitle()+"','"+s.getContent()+"','"+s.getLink()+"','"+s.getImg_link()+"','"+s.isIsActive()+"')";
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
    
    public void updateSlider(int id, slider s) {

        //cau lenh sql
        String sql = "update Slider \n" +
                     "set title=?,\n" +
                     "content=?,\n" +
                     "link=?,\n" +
                     "image_link=?\n" +
                     "where id=?";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            //truyền tham số vào câu lệnh sql
            pr.setString(1, s.getTitle());
            pr.setString(2, s.getContent());
            pr.setString(3, s.getLink());
            pr.setString(4, s.getImg_link());
            pr.setInt(5, id);
            //chạy câu lệnh thao thác đến dữ liệu add ,update ,delete
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }
    
    public void updateActiveSlider(int id, boolean isActive) {

        //cau lenh sql
        String sql = "UPDATE [dbo].[Slider]\n" +
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
    
    public void deleteSlider(int id) {
        //cau lenh sql
        String sql = "delete from Slider\n" +
                      "where id=?";
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
}
