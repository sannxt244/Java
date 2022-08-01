package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Answer;

public class AnswerDBContext extends DBContext {

    public Answer find(int id) {
        String sqlStatement = "SELECT [id]\n"
                + "      ,[content]\n"
                + "      ,[questionId]\n"
                + "      ,[isSolution]\n"
                + "      ,[explain]\n"
                + "  FROM [dbo].[Answer]\n"
                + "  WHERE [id] = " + id;
        try {
            System.out.println(sqlStatement);
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setContent(rs.getString("content"));
                answer.setQuestionId(rs.getInt("questionId"));
                answer.setIsSolution(rs.getBoolean("isSolution"));
                answer.setExplain(rs.getString("explain"));

                return answer;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public ArrayList<Answer> findByQuestion(int questionId) {
        ArrayList<Answer> answers = new ArrayList<>();
        String sqlStatement = "SELECT [id]\n"
                + "      ,[content]\n"
                + "      ,[questionId]\n"
                + "      ,[isSolution]\n"
                + "      ,[explain]\n"
                + "  FROM [dbo].[Answer]\n"
                + "  WHERE [questionId] = " + questionId;
        try {
            System.out.println(sqlStatement);
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setContent(rs.getString("content"));
                answer.setQuestionId(rs.getInt("questionId"));
                answer.setIsSolution(rs.getBoolean("isSolution"));
                answer.setExplain(rs.getString("explain"));

                answers.add(answer);
            }
            return answers;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
