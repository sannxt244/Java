/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Lesson;
import model.Quiz;
import model.Topic;

/**
 *
 * @author sannx
 */
public class LessonDBContext extends DBContext {

    public ArrayList<Lesson> getLessonByTopic(int subjectTopicId) {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[video_link]\n"
                    + "      ,[html_content]\n"
                    + "      ,[subjectTopicId]\n"
                    + "      ,[type]\n"
                    + "  FROM [dbo].[Lesson]"
                    + "  WHERE subjectTopicId = " + subjectTopicId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("id"));
                lesson.setName(rs.getString("name"));
                lesson.setVideoLink(rs.getString("video_link"));
                lesson.setHtmlContent(rs.getString("html_content"));
                lesson.setSubjectTopicId(rs.getInt("subjectTopicId"));
                lesson.setType(rs.getString("type"));

                lessonList.add(lesson);
            }
            return lessonList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Topic> getTopicsByCourseId(int courseId) {
        ArrayList<Topic> topicList = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[order]\n"
                    + "      ,[courseId]\n"
                    + "  FROM [dbo].[Subject_Topic]"
                    + "  WHERE courseId = " + courseId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
                topic.setOrder(rs.getInt("order"));
                topic.setCourseId(rs.getInt("courseId"));
                ArrayList<Lesson> lessonList = getLessonByTopic(topic.getId());
                topic.setLessonList(lessonList);
                topicList.add(topic);
            }
            return topicList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Lesson getLessonById(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[video_link]\n"
                    + "      ,[html_content]\n"
                    + "      ,[subjectTopicId]\n"
                    + "      ,[type]\n"
                    + "  FROM [dbo].[Lesson]"
                    + "  WHERE id = " + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("id"));
                lesson.setName(rs.getString("name"));
                lesson.setVideoLink(rs.getString("video_link"));
                lesson.setHtmlContent(rs.getString("html_content"));
                lesson.setSubjectTopicId(rs.getInt("subjectTopicId"));
                lesson.setType(rs.getString("type"));

                return lesson;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static void main(String[] args) {
        LessonDBContext lessonDBC = new LessonDBContext();
        ArrayList<Topic> topicList = lessonDBC.getTopicsByCourseId(1);
        ArrayList<Lesson> lessonList = lessonDBC.getLessonByTopic(topicList.get(0).getId());
        for (Lesson topic : lessonList) {
            System.out.println(topic);
        }

    }
}
