/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.dai;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lesson;
import model.quizFormat;
import model.subjectTopic;

/**
 *
 * @author HDC
 */
public class lessonDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<subjectTopic> AllSubjectTopic(String sid) {

        List<subjectTopic> list = new ArrayList<>();
        //cau lenh sql
        String sql = "select* from Subject_Topic\n"
                + "where Subject_Topic.courseId='" + sid + "'";
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
                int courseId = rs.getInt(3);
                int order = rs.getInt(4);
                String type = rs.getString(5);

                subjectTopic s = new subjectTopic(id, name, courseId, order, type);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return list;
    }

    public List<Lesson> LessonBySubjectTopicId(int stid) {
        List<Lesson> list = new ArrayList<>();
        //cau lenh sql
        String sql = "select* from Lesson\n"
                + "where Lesson.subjectTopicId=" + stid + "";
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
                String video_link = rs.getString(3);
                String html_content = rs.getString(4);
                int subjectTopicId = rs.getInt(5);
                String type = rs.getString(6);
                int order = rs.getInt(7);

                Lesson s = new Lesson(id, name, video_link, html_content, subjectTopicId, type, order);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return list;
    }

    
    public List<Lesson> LessonById(String lid){
       List<Lesson> list = new ArrayList<>();
        //cau lenh sql
        String sql = "select* from Lesson\n" +
                        "where id='"+lid+"'";
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
                String video_link = rs.getString(3);
                String html_content = rs.getString(4);
                int subjectTopicId= rs.getInt(5);
                String type = rs.getString(6);
                int order =rs.getInt(7);
                
                Lesson s = new Lesson(id,name,video_link,html_content,subjectTopicId,type, order);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return list;  
    }
    
    public List<quizFormat> QuizFormatBySubjectTopicId(int stid){
       List<quizFormat> list = new ArrayList<>();

        //cau lenh sql
        String sql = "select * from QuizFormat\n"
                + "where QuizFormat.subjectTopicId=" + stid + "";
        //thuc hien cau lenh
        try {
            // tạo biến chứa câu lệnh sql
            PreparedStatement pr = connection.prepareStatement(sql);
            // thực thi câu lệnh sql và lưu kết quả vào bảng
            ResultSet rs = pr.executeQuery();
            //lặp theo dòng của bảng
            while (rs.next()) {
                int id = rs.getInt(1);
                int subjectTopicId = rs.getInt(2);
                String name = rs.getString(3);
                int duration = rs.getInt(4);
                String description = rs.getString(5);
                int number = rs.getInt(6);
                String type = rs.getString(7);

                quizFormat s = new quizFormat(id, subjectTopicId, name, duration, description, number, type);
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return list;
    }

    public void addSubjectTopic(subjectTopic s) {

        //cau lenh sql
        String sql = "INSERT INTO [Subject_Topic]\n"
                + "           ([name]\n"
                + "           ,[courseId]\n"
                + "           ,[order]\n" + "INSERT INTO [Subject_Topic]\n"
                + "           ([name]\n"
                + "           ,[type])\n"
                + "     VALUES\n"
                + "           ('" + s.getName() + "','" + s.getCourseId() + "','" + s.getOrder() + "','" + s.getType() + "')";
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

    public void addLesson(Lesson s) {

        //cau lenh sql

        String sql = "INSERT INTO [dbo].[Lesson]\n" +
"           ([name]\n" +
"           ,[video_link]\n" +
"           ,[html_content]\n" +
"           ,[subjectTopicId]\n" +
"           ,[type]\n" +
"           ,[order])\n" +
"     VALUES\n" +
"           ('"+s.getName()+"', '"+s.getVideoLink()+"', '"+s.getHtmlContent()+"', '"+s.getSubjectTopicId()+"', '"+s.getType()+"', '"+s.getOrder()+"')";
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

    public void updateSubjectTopic(int id, subjectTopic s) {

        //cau lenh sql

        String sql = "UPDATE [dbo].[Subject_Topic]\n" +
"   SET [name] = ?\n" +
"      ,[order] = ?\n" +
" WHERE id=?";
        //thuc hien cau lenh 

        try {
            // tạo biến chứa câu lệnh sql
            PreparedStatement pr = connection.prepareStatement(sql);
            //truyền tham số vào câu lệnh sql
            pr.setString(1, s.getName());
            pr.setInt(2, s.getOrder());
            pr.setInt(3, id);
            //chạy câu lệnh thao thác đến dữ liệu add ,update ,delete
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    
    public void updateLesson(int id, Lesson l) {

        //cau lenh sql
        String sql = "UPDATE [dbo].[Lesson]\n" +
"   SET [name] = ?\n" +
"      ,[video_link] = ?\n" +
"      ,[html_content] = ?\n" +
"      ,[type] = 'lesson'\n" +
"      ,[order] = ?\n" +
" WHERE id=?";
        //thuc hien cau lenh 
        try {
            // tạo biến chứa câu lệnh sql 
            PreparedStatement pr = connection.prepareStatement(sql);
            //truyền tham số vào câu lệnh sql
            pr.setString(1, l.getName());
            pr.setString(2, l.getVideoLink());
            pr.setString(3, l.getHtmlContent());
            pr.setInt(5, l.getOrder());
            pr.setInt(6, id);
            //chạy câu lệnh thao thác đến dữ liệu add ,update ,delete
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }
    
        public void deleteSubjectTopic(int id) {

        //cau lenh sql
        String sql = "DELETE FROM [dbo].[Subject_Topic]\n"
                + "      WHERE id=?";
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

    public void deleteLesson(int id) {
        //cau lenh sql
        String sql = "DELETE FROM [dbo].[Lesson]\n"
                + "      WHERE id=?";
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

    public void deleteQuizFormat(int id) {
        //cau lenh sql
        String sql = "DELETE FROM [dbo].[QuizFormat]\n"
                + "      WHERE id=?";
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

            
    public boolean checkOrder(int order, String checkname) {
        String sql = "select * from "+checkname+" where [order]='"+order+"'";
        
        boolean check = false;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
}
         

}
