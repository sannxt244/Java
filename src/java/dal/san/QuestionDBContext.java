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
import model.Answer;
import model.Question;
import model.Quiz;
import model.QuizResult;
import model.StudentAnswer;

/**
 *
 * @author sannx
 */
public class QuestionDBContext extends DBContext {

    public ArrayList<Answer> getAnswerByQuestionId(int questionId) {
        ArrayList<Answer> answerList = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[content]\n"
                    + "      ,[questionId]\n"
                    + "      ,[isSolution]\n"
                    + "  FROM [dbo].[Answer]"
                    + "  WHERE questionId = " + questionId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setContent(rs.getString("content"));
                answer.setQuestionId(rs.getInt("questionId"));
                answer.setIsSolution(rs.getBoolean("isSolution"));

                answerList.add(answer);
            }
            return answerList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Question> getQuestionsByTopicId(int topicId) {
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            String sql = "SELECT [Id]\n"
                    + "      ,[content]\n"
                    + "      ,[topicId]\n"
                    + "  FROM [dbo].[Question]"
                    + "  WHERE topicId = " + topicId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setContent(rs.getString("content"));
                question.setTopicId(rs.getInt("topicId"));
                ArrayList<Answer> answerList = getAnswerByQuestionId(question.getTopicId());
                question.setAnswerList(answerList);
                questionList.add(question);
            }
            return questionList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Integer> getQuestionsByQuizId(String student, int quizId) {
        ArrayList<Integer> questionList = new ArrayList<>();
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[answerId]\n"
                    + "      ,[questionId]\n"
                    + "      ,[quizId]\n"
                    + "  FROM [dbo].[Student_Answer]\n"
                    + "  WHERE username = '" + student + "' AND quizId = " + quizId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                questionList.add(rs.getInt("questionId"));
            }
            return questionList;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Answer getQuestionSolution(int questionId) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[content]\n"
                    + "      ,[questionId]\n"
                    + "      ,[isSolution]\n"
                    + "  FROM [dbo].[Answer]\n"
                    + "  WHERE questionId = " + questionId + " and isSolution = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setContent(rs.getString("content"));
                answer.setQuestionId(rs.getInt("questionId"));
                answer.setIsSolution(rs.getBoolean("isSolution"));
                return answer;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public int checkStudentAnswer(String student, int questionId) {
        StudentAnswer studentAnswer = null;
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[answerId]\n"
                    + "      ,[questionId]\n"
                    + "      ,[quizId]\n"
                    + "  FROM [dbo].[Student_Answer]"
                    + "  WHERE username = '" + student + "' and questionId = " + questionId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                studentAnswer = new StudentAnswer();
                studentAnswer.setStudent(rs.getString("username"));
                studentAnswer.setAnswerId(rs.getString("answerId"));
                studentAnswer.setQuestions(rs.getInt("questionId"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        // Check student answer
        if (studentAnswer != null && studentAnswer.getAnswerId() == -1) {
            return -1;
        }
        if (studentAnswer != null && studentAnswer.getAnswerId() == getQuestionSolution(questionId).getId()) {
            return 1;
        } else {
            return 0;
        }
    }

    public QuizResult getResultOfQuiz(int quizId, String student) {
        ArrayList<Integer> questionList = getQuestionsByQuizId(student, quizId);
        int amountRightAnswer = 0;
        int notAnswer = 0;
        for (int question : questionList) {
            System.out.println(checkStudentAnswer(student, question));
            if (checkStudentAnswer(student, question) == 1) {
                amountRightAnswer++;
            } else if (checkStudentAnswer(student, question) == -1) {
                notAnswer++;
            }
        }
        return new QuizResult(amountRightAnswer, notAnswer, questionList.size());
    }

    public int getTotalAnswerOfQuiz(int quizId, String student) {
        ArrayList<Integer> questionList = getQuestionsByQuizId(student, quizId);

        return questionList.size();
    }

    public Quiz getQuizByTopicId(int topicId) {
        try {
            String sql = "SELECT TOP 1 [id]\n"
                    + "      ,[subjectTopicId]\n"
                    + "      ,[name]\n"
                    + "      ,[duration]\n"
                    + "      ,[description]\n"
                    + "      ,[number]\n"
                    + "      ,[type]\n"
                    + "  FROM [dbo].[Quiz]\n"
                    + "  WHERE subjectTopicId = " + topicId + "\n"
                    + "  ORDER BY [id] DESC";
            System.out.println(sql);
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setSubjectTopicId(rs.getInt("subjectTopicId"));
                quiz.setName(rs.getString("name"));
                quiz.setDuration(rs.getInt("duration"));
                quiz.setDescription(rs.getString("description"));
                quiz.setNumber(rs.getInt("number"));
                quiz.setType(rs.getString("type"));
                return quiz;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Quiz getQuizById(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[subjectTopicId]\n"
                    + "      ,[name]\n"
                    + "      ,[duration]\n"
                    + "      ,[description]\n"
                    + "      ,[number]\n"
                    + "      ,[type]\n"
                    + "  FROM [dbo].[Quiz]\n"
                    + "  WHERE id = " + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setSubjectTopicId(rs.getInt("subjectTopicId"));
                quiz.setName(rs.getString("name"));
                quiz.setDuration(rs.getInt("duration"));
                quiz.setDescription(rs.getString("description"));
                quiz.setNumber(rs.getInt("number"));
                quiz.setType(rs.getString("type"));

                return quiz;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean isDoneQuiz(String studentId, int topicId) {
        try {
            String sql = "SELECT [Student_Answer].[username]\n"
                    + "      ,[Student_Answer].[answerId]\n"
                    + "      ,[Student_Answer].[questionId]\n"
                    + "      ,[Student_Answer].[quizId]\n"
                    + "	  ,[Quiz].[subjectTopicId]\n"
                    + "  FROM [dbo].[Student_Answer] \n"
                    + "  JOIN [dbo].[Quiz] ON [Student_Answer].quizId = [Quiz].id \n"
                    + "  WHERE username = '" + studentId + "' and subjectTopicId = " + topicId;
            System.out.println(sql);
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static void main(String[] args) {
        QuestionDBContext q = new QuestionDBContext();
        System.out.println(q.getQuizByTopicId(1));

    }
}
