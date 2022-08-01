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
import model.haha.QuizFormat;
import model.haha.Subject_Topic;

/**
 *
 * @author LAPTOP D&N
 */
public class QuizFormatDBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int totalQuiz() throws SQLException {
        String query = "select COUNT(*) from QuizFormat\n";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<QuizFormat> getAllQuizWithPaging(int page) {
        List<QuizFormat> list = new ArrayList<>();
        String query = "select * from QuizFormat\n"
                + "order by QuizFormat.id\n"
                + "offset ? rows fetch next 12 rows only";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, (page - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new QuizFormat(
                        rs.getInt("id"),
                        rs.getInt("subjectTopicId"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("description"),
                        rs.getInt("number"),
                        rs.getString("type")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertQuizFormat(int subjectTopicId, String name,
            int quiz_Duration, String description, int quiz_number, String quiz_type) {
        String query = "INSERT INTO  [dbo].[QuizFormat] \n"
               + "( [subjectTopicId], [name], [duration], [description], [number], [type])\n"
               + "VALUES(?,?,?,?,?,?)";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);           
            ps.setInt(1, subjectTopicId);
            ps.setString(2, name);
            ps.setInt(3, quiz_Duration);
            ps.setString(4, description);
            ps.setInt(5, quiz_number);
            ps.setString(6, quiz_type);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }


public QuizFormat getQuizFormatDetail(int id) {
        String query = "select * from QuizFormat WHERE id = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new QuizFormat(
                        rs.getInt("id"),
                        rs.getInt("subjectTopicId"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("description"),
                        rs.getInt("number"),
                        rs.getString("type"));
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
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void editQuiz_Format(int id, int subjectTopicId, String name,
            int duration, String description, int number, String type) {
        String query = "update [QuizFormat]\n"
                + "set [subjectTopicId] = ?, [name] = ?, \n"
                + "[duration] = ?, [description] = ?, \n"
                + "[number] = ?, [type] = ? \n"
                + "where id = ?";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, subjectTopicId);
            ps.setString(2, name);
            ps.setInt(3, duration);
            ps.setString(4, description);
            ps.setInt(5, number);
            ps.setString(6, type);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws SQLException {
        QuizFormatDBContext db = new QuizFormatDBContext();
//        List<Quiz_Type> list = db.getAllQuiz_Type();
//        for (Quiz_Type quiz : list) {
//            System.out.println(quiz.toString());
//        }
        List<Subject_Topic> list = db.getAllSubjectTopic();
        for (Subject_Topic subject_Topic : list) {
            System.out.println(subject_Topic);
        }
    }



}
