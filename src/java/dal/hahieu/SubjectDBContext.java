/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.hahieu;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.haha.Lesson;
import model.haha.Subject_Topic;

/**
 *
 * @author LAPTOP D&N
 */
public class SubjectDBContext extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Lesson> getAllLesson() {
        List<Lesson> list = new ArrayList<>();
        String query = "select [Subject_Topic].[subject_id], [Subject_Topic].[subject_name], [Subject_Topic].[courseId],\n"
                + "[Subject_Topic].[order], [Lesson].id, [Lesson].[name], [Lesson].[video_link], [Lesson].[html_content], \n"
                + "[Lesson].subjectTopicId, [Lesson].[type]\n"
                + "from Lesson\n"
                + "Join Subject_Topic on [Subject_Topic].[courseId] = [Lesson].[subjectTopicId]\n"
                + "Where [Lesson].[type] = 'Active'";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Lesson(
                        rs.getInt("subject_id"),
                        rs.getString("subject_name"),
                        rs.getInt("courseId"),
                        rs.getInt("order"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("video_link"),
                        rs.getString("html_content"),
                        rs.getInt("subjectTopicId"),
                        rs.getString("type")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Lesson getLessonByLessonId(int id) {
        String query = "select [Subject_Topic].[subject_id], [Subject_Topic].[subject_name], [Subject_Topic].[courseId],\n"
                + "[Subject_Topic].[order], [Lesson].[id], [Lesson].[name], [Lesson].[video_link], [Lesson].[html_content], \n"
                + "[Lesson].subjectTopicId, [Lesson].[type]\n"
                + "from Lesson\n"
                + "Join Subject_Topic on [Subject_Topic].[courseId] = [Lesson].[subjectTopicId]\n"
                + "Where [Lesson].[id] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setHtml_content(rs.getString("html_content"));
                lesson.setId(rs.getInt("id"));
                lesson.setCourseId(rs.getInt("courseId"));
                lesson.setName(rs.getString("name"));
                lesson.setOrder(rs.getInt("order"));
                lesson.setSubjectTopicId(rs.getInt("subjectTopicId"));
                lesson.setSubject_id(rs.getInt("courseId"));
                lesson.setSubject_name(rs.getString("subject_name"));
                lesson.setType(rs.getString("type"));
                lesson.setVideo_link(rs.getString("video_link"));
                return lesson;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Subject_Topic> getAllSubjectTopic() {
        List<Subject_Topic> list = new ArrayList<>();
        String query = "select * from [Subject_Topic]";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject_Topic(
                        rs.getInt("subject_id"),
                        rs.getString("subject_name"),
                        rs.getInt("courseId"),
                        rs.getInt("order")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void editSubject(String name, String video_link, String html_content,
            int subjectTopicId, String type, int pid) {
        String query = "update [Lesson] \n"
                + "set [name] = ?, [video_link] = ?, \n"
                + "[html_content] = ?, [subjectTopicId] = ?, \n"
                + "[type] = ?\n"
                + "where id = ?";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, video_link);
            ps.setString(3, html_content);
            ps.setInt(4, subjectTopicId);
            ps.setString(5, type);
            ps.setInt(6, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editCourseDetail(String name, int isActive, String description, int categoryId, String img, int courseId, int packageId) {
        String query = "update [course]\n"
                + "SET course.name = ?,\n"
                + "course.isActive = ?,\n"
                + "course.description = ?,\n"
                + "course.categoryId = ?,\n"
                + "course.img = ?\n"
                + "FROM [course]\n"
                + "JOIN Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "WHERE course.id = ? and Price_Package.packageId = ?";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, isActive);
            ps.setString(3, description);
            ps.setInt(4, categoryId);
            ps.setString(5, img);
            ps.setInt(6, courseId);
            ps.setInt(7, packageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCourseDetailPrice(int price, int priceSale, int courseId, int packageId) {
        String query = "update Price_Package\n"
                + "SET\n"
                + "	Price_Package.price = ?,\n"
                + "	Price_Package.priceSale = ?\n"
                + "FROM [course]\n"
                + "JOIN Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "WHERE course.id = ? and Price_Package.packageId = ?";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, price);
            ps.setInt(2, priceSale);
            ps.setInt(3, courseId);
            ps.setInt(4, packageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SubjectDBContext sd = new SubjectDBContext();
//        List<Subject_Topic> list = sd.getAllSubjectTopic();
//        for (Subject_Topic subject_Topic : list) {
//            System.out.println(subject_Topic.toString());
//        }
        sd.editCourseDetailPrice(1, 1, 200, 100);
    }

}
