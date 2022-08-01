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
import model.StudentAnswer;

/**
 *
 * @author sannx
 */
public class StudentAnswerDBContext extends DBContext {

    public StudentAnswer findByQuestion(String student, int questionId) {
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[answerId]\n"
                    + "      ,[questionId]\n"
                    + "      ,[quizId]\n"
                    + "  FROM [dbo].[Student_Answer]\n"
                    + "  WHERE username = '" + student + "' AND questionId = " + questionId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.setStudent(rs.getString("username"));
                studentAnswer.setAnswerId(rs.getString("answerId"));
                studentAnswer.setQuestions(rs.getInt("questionId"));
                studentAnswer.setQuizId(rs.getInt("quizId"));

                return studentAnswer;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<StudentAnswer> findByQuiz(String student, int quizId) {
        ArrayList<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[answerId]\n"
                    + "      ,[questionId]\n"
                    + "      ,[quizId]\n"
                    + "  FROM [dbo].[Student_Answer]\n"
                    + "  WHERE username = '" + student + "' AND quizId = " + quizId;
            System.out.println(sql);
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.setStudent(rs.getString("username"));
                studentAnswer.setAnswerId(rs.getString("answerId"));
                studentAnswer.setQuestions(rs.getInt("questionId"));
                studentAnswer.setQuizId(rs.getInt("quizId"));

                studentAnswers.add(studentAnswer);

            }
            return studentAnswers;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * 1 = right, -1 = no answer
     *
     * @param student
     * @param questionId
     * @return
     */
    public int checkStudentAnswer(String student, int questionId) {
        StudentAnswer studentAnswer = findByQuestion(student, questionId);
        int answerId = studentAnswer.getAnswerId();
        AnswerDBContext answerDB = new AnswerDBContext();

        // Check student answer
        if (studentAnswer != null) {
            for (Answer answer : answerDB.findByQuestion(questionId)) {
                if (answer.isIsSolution() && answer.getId() == answerId) {
                    return 1;
                }
            }
            return 0;
        } else {
            return -1;
        }
    }
}
