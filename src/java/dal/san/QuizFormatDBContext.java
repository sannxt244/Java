package dal.san;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.quizFormat;

public class QuizFormatDBContext extends DBContext {

    public quizFormat find(int id) {
        String sqlStatement = "SELECT [id]\n"
                + "      ,[subjectTopicId]\n"
                + "      ,[name]\n"
                + "      ,[duration]\n"
                + "      ,[description]\n"
                + "      ,[number]\n"
                + "      ,[type]\n"
                + "  FROM [dbo].[QuizFormat]\n"
                + "  WHERE [id] = " + id;
        try {
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                quizFormat quizFormat = new quizFormat();
                quizFormat.setId(rs.getInt("id"));
                quizFormat.setSubjectTopicId(rs.getInt("subjectTopicId"));
                quizFormat.setName(rs.getString("name"));
                quizFormat.setDuration(rs.getInt("duration"));
                quizFormat.setDescription(rs.getString("description"));
                quizFormat.setNumber(rs.getInt("number"));
                quizFormat.setType(rs.getString("type"));
                return quizFormat;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public quizFormat findByTopic(int subjectTopicId) {
        String sqlStatement = "SELECT [id]\n"
                + "      ,[subjectTopicId]\n"
                + "      ,[name]\n"
                + "      ,[duration]\n"
                + "      ,[description]\n"
                + "      ,[number]\n"
                + "      ,[type]\n"
                + "  FROM [dbo].[QuizFormat]\n"
                + "  WHERE [subjectTopicId] = " + subjectTopicId;
        try {
            PreparedStatement stm = connection.prepareStatement(sqlStatement);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                quizFormat quizFormat = new quizFormat();
                quizFormat.setId(rs.getInt("id"));
                quizFormat.setSubjectTopicId(rs.getInt("subjectTopicId"));
                quizFormat.setName(rs.getString("name"));
                quizFormat.setDuration(rs.getInt("duration"));
                quizFormat.setDescription(rs.getString("description"));
                quizFormat.setNumber(rs.getInt("number"));
                quizFormat.setType(rs.getString("type"));
                return quizFormat;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
