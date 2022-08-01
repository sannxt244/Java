/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.donghieu;

import dal.DBContext;
import dal.hahieu.CourseDBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Blog;
import model.Course;
import model.Question;
import model.Topic;
import model.User;

/**
 *
 * @author dell
 */
public class QuestionDBContext extends DBContext {

    public ArrayList<Course> getAllCourse() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.course\n"
                    + "ORDER BY name";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setIsActive(rs.getBoolean(3));
                course.setCreated_by(rs.getString(4));
                course.setDescription(rs.getString(5));
                course.setCategoryId(rs.getInt(6));
                course.setImg(rs.getString(7));
                course.setCreatedDate(rs.getDate(8));
                course.setTopics(getTopicByCourseID(rs.getInt(1)));
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Topic> getTopicByCourseID(int courseId) {
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Subject_Topic\n"
                    + "WHERE courseId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt(1));
                topic.setName(rs.getString(2));
                topic.setCourseId(rs.getInt(3));
                topic.setOrder(rs.getInt(4));
                topics.add(topic);
            }
            return topics;
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getTotalRow(User user,int courseId, int topicId, String search) {
        try {
            String sql = "SELECT COUNT(*) FROM Question JOIN dbo.Subject_Topic\n"
                    + "ON Subject_Topic.id = Question.topicId\n"
                    + "JOIN dbo.course\n"
                    + "ON course.id = Subject_Topic.courseId";

            if (courseId != 0) {
                if (topicId != 0) {
                    sql += "WHERE  courseId = ? AND topicId = ?\n";
                } else {
                    sql += "WHERE  courseId = ?\n";
                }
            } else if (search != null && search.length() > 0) {
                sql += "WHERE  FREETEXT(content,?)\n";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            if (courseId != 0) {
                if (topicId != 0) {
                    stm.setInt(1, courseId);
                    stm.setInt(2, topicId);
                } else {
                    stm.setInt(1, courseId);
                }
            } else if (search != null && search.length() > 0) {
                stm.setString(1, search);

            }

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Question> getAllQuestion(User user, int page, int PAGE_SIZE, int courseId, int topicId, String search) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "SELECT Question.Id,content,topicId,Question.isActive,importBy FROM Question JOIN dbo.Subject_Topic\n"
                    + "ON Subject_Topic.id = Question.topicId\n"
                    + "JOIN dbo.course\n"
                    + "ON course.id = Subject_Topic.courseId\n";

            if (courseId != 0) {
                if (topicId != 0) {
                    sql += "WHERE  courseId = ? AND topicId = ?\n";
                } else {
                    sql += "WHERE  courseId = ?\n";
                }
            } else if (search != null && search.length() > 0) {
                sql += "WHERE  FREETEXT(content,?)\n";
            } 
            if (user.getRole().getId() == 2 && courseId == 0 && topicId == 0 && (search == null || search.length() <= 0)) {
                sql += " Where importBy = ?\n";
            }
            if(user.getRole().getId() == 2 && (courseId != 0 || topicId != 0 || (search != null && search.length() > 0)))
            {
                sql += "  and importBy = ?\n";
            }
            sql += "ORDER BY Question.Id OFFSET (?-1)*? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);

            if (courseId != 0) {
                if (topicId != 0) {
                    if (user.getRole().getId() == 2) {
                        stm.setInt(1, courseId);
                        stm.setInt(2, topicId);
                        stm.setString(3, user.getUserName());
                        stm.setInt(4, page);
                        stm.setInt(5, PAGE_SIZE);
                        stm.setInt(6, PAGE_SIZE);
                    } else {
                        stm.setInt(1, courseId);
                        stm.setInt(2, topicId);
                        stm.setInt(3, page);
                        stm.setInt(4, PAGE_SIZE);
                        stm.setInt(5, PAGE_SIZE);
                    }

                } else {
                    if (user.getRole().getId() == 2) {
                        stm.setInt(1, courseId);
                        stm.setString(2, user.getUserName());
                        stm.setInt(3, page);
                        stm.setInt(4, PAGE_SIZE);
                        stm.setInt(5, PAGE_SIZE);

                    } else {
                        stm.setInt(1, courseId);
                        stm.setInt(2, page);
                        stm.setInt(3, PAGE_SIZE);
                        stm.setInt(4, PAGE_SIZE);
                    }
                }
            } else if (search != null && search.length() > 0) {
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

            } else {
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

            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt(1));
                q.setContent(rs.getString(2));
                q.setTopicId(rs.getInt(3));
                q.setIsActived(rs.getBoolean(4));
                questions.add(q);
            }
            return questions;
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setStatus(int questionId, boolean active) {
        String sql = "UPDATE [Question]\n"
                + "   SET [isActive] = ?\n"
                + " WHERE Id = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, active);
            stm.setInt(2, questionId);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Question getQuestionById(int questionId) {

        try {
            String sql = "SELECT * from dbo.Question\n"
                    + "WHERE Id  = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt(1));
                question.setContent(rs.getString(2));
                question.setTopicId(rs.getInt(3));
                question.setIsActived(rs.getBoolean(4));
                question.setAnswerList(getAnswerByQuestionId(questionId));
                return question;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Answer> getAnswerByQuestionId(int questionId) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Answer WHERE questionId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt(1));
                answer.setContent(rs.getString(2));
                answer.setQuestionId(rs.getInt(3));
                answer.setIsSolution(rs.getBoolean(4));
                answer.setExplain(rs.getString(5));
                answers.add(answer);
            }
            return answers;
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getcourseIdByQuestionId(int topicId) {
        try {
            String sql = "SELECT courseId FROM Question JOIN dbo.Subject_Topic\n"
                    + "ON Subject_Topic.id = Question.topicId\n"
                    + "WHERE Question.Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, topicId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String getExplainByQuestionId(int id) {
        try {
            String sql = "SELECT explain FROM dbo.Answer JOIN dbo.Question\n"
                    + "ON Question.Id = Answer.questionId\n"
                    + "WHERE questionId = ? AND isSolution = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//many to many

    public void updateQuestion(Question question) {
        try {
            String sql_update_question = "UPDATE [Question]\n"
                    + "   SET [content] = ?\n"
                    + "      ,[topicId] = ?\n"
                    + "      ,[isActive] = ?\n"
                    + " WHERE Id = ?";

            String sql_update_answer = "UPDATE [Answer]\n"
                    + "   SET[content] = ?\n"
                    + "      ,[isSolution] = ?\n"
                    + "      ,[explain] = ?\n"
                    + " WHERE id = ?";
            connection.setAutoCommit(false);

            PreparedStatement stm_UPDATE_QUESTION = connection.prepareStatement(sql_update_question);

            stm_UPDATE_QUESTION.setString(1, question.getContent());
            stm_UPDATE_QUESTION.setInt(2, question.getTopicId());
            stm_UPDATE_QUESTION.setBoolean(3, question.isIsActived());
            stm_UPDATE_QUESTION.setInt(4, question.getId());
            stm_UPDATE_QUESTION.executeUpdate();

            for (Answer answer : question.getAnswerList()) {
                PreparedStatement stm_UPDATE_ANSWER = connection.prepareStatement(sql_update_answer);
                stm_UPDATE_ANSWER.setString(1, answer.getContent());
                stm_UPDATE_ANSWER.setBoolean(2, answer.isIsSolution());
                stm_UPDATE_ANSWER.setString(3, answer.getExplain());
                stm_UPDATE_ANSWER.setInt(4, answer.getId());
                stm_UPDATE_ANSWER.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection       
        }
    }

    public void importQuestion(User user,ArrayList<Question> questions) {

        String _sql_import_questions = "INSERT INTO [Question]\n"
                + "           ([content]\n"
                + "           ,[topicId]\n"
                + "           ,[isActive]"
                + "           ,[importBy])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,0\n"
                + "           ,?)";
        PreparedStatement import_Question_stm = null;
        String sql_import_answer = "INSERT INTO [Answer]\n"
                + "           ([content]\n"
                + "           ,[questionId]\n"
                + "           ,[isSolution]\n"
                + "           ,[explain])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm_import_answer = null;
        try {
            for (Question question : questions) {
                PreparedStatement stm_INSERT_QUESTION = connection.prepareStatement(_sql_import_questions);
                stm_INSERT_QUESTION.setString(1, question.getContent());
                stm_INSERT_QUESTION.setInt(2, question.getTopicId());
                stm_INSERT_QUESTION.setString(3, user.getUserName());
                stm_INSERT_QUESTION.executeUpdate();

                for (Answer answer : question.getAnswerList()) {
                    stm_import_answer = connection.prepareStatement(sql_import_answer);
                    int id = getLastQuestionId();
                    stm_import_answer.setString(1, answer.getContent());
                    stm_import_answer.setInt(2, id);
                    stm_import_answer.setBoolean(3, answer.isIsSolution());
                    stm_import_answer.setString(4, answer.getExplain());
                    stm_import_answer.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (import_Question_stm != null) {
                try {
                    import_Question_stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean questionIsExist(String questionContent) {
        try {
            String sql = "SELECT * FROM dbo.Question\n"
                    + "WHERE content = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, questionContent);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
//    public void importAnswer(ArrayList<Answer> answers) {
//
//        String sql = "INSERT INTO [Answer]\n"
//                + "           ([content]\n"
//                + "           ,[questionId]\n"
//                + "           ,[isSolution]\n"
//                + "           ,[explain])\n"
//                + "     VALUES\n"
//                + "           (?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?)";
//        PreparedStatement stm = null;
//        try {
//
//            for (Answer answer : question.getAnswerList()) {
//                stm = connection.prepareStatement(sql);
//                int id = getLastQuestionId();
//                stm.setString(1, answer.getContent());
//                stm.setInt(2, id);
//                stm.setBoolean(3, answer.isIsSolution());
//                stm.setString(4, answer.getExplain());
//                stm.executeUpdate();
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (stm != null) {
//                try {
//                    stm.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }

    private int getLastQuestionId() {
        try {
            String sql = "SELECT TOP 1 id FROM dbo.Question \n"
                    + "ORDER BY Id DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dal.san.QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
