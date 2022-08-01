/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.minhlee;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.minhlee.Answer;
import model.minhlee.Question;
import model.minhlee.Quiz;
import model.minhlee.Quiz_review;
import model.minhlee.Student_Answer;
import model.minhlee.Subject_Topic;

/**
 *
 * @author Minh Lee
 */
public class QuizDAO extends DBContext{
    
    
    
    public List<Question> getAllQuestions(int topicId ) {
        List<Question> ls = new ArrayList<>();
        String sql="select * from question where topicId = '"+ topicId +"'";
        
        try{
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Question q = new Question(rs.getInt(1), rs.getString(2), rs.getInt(3));
                ls.add(q);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return ls;
    }
    
    public List<Answer> getAllAnswer (){
        List<Answer> ls = new ArrayList<>();
        String sql = "select * from answer";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Answer a = new Answer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                ls.add(a);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return ls;
    }
    
    
    public List<Quiz> getAllQuiz() {
        List<Quiz> ls = new ArrayList<Quiz>();
        String sql = "select * from Quiz";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Quiz q = new Quiz(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7));
                ls.add(q);
            }
        }catch(SQLException e){
            System.out.println("getAllQuiz: "+e);
        }
        
        return ls;
    }
    
    public List<Subject_Topic> getAllSubject_Topic (){
        List<Subject_Topic> ls = new ArrayList<Subject_Topic>();
        String sql = "select * from Subject_Topic";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Subject_Topic sj = new Subject_Topic(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                ls.add(sj);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return ls;
    }
    
    public void InsertQuiz(Quiz q){
        String sql = "insert into Quiz(subjectTopicId, name, duration, number) values (?,?,?,?)";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, q.getSubjectTopicId());
                st.setString(2, q.getName());
                st.setInt(3,q.getDuration());
                st.setInt(4, q.getNumber());
                st.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Insert Quiz: "+e);
        }
    }
    
    public int ScoreExam(String username,Quiz q, int[] quesId, int[] ansId){    // can get quizId in Student_answer
        
        InsertQuiz(q);
        int quizId = -1;
        
        List<Quiz> ls = new ArrayList<Quiz>();
        try{
            String sql1 = "select * from Quiz";
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Quiz qz = new Quiz();
                qz.setId(rs.getInt("id"));
                ls.add(qz);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        quizId =ls.get((ls.size()-1)).getId();  // get last object of list Quiz
        
        for (int i = 0; i < quesId.length; i++) {
            
            String sql = "insert into Student_Answer(username,answerId,questionId,quizId) values(?,?,?,?)";
            
            try{
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, username);
                st.setInt(2, ansId[i]);
                st.setInt(3,quesId[i]);
                st.setInt(4, quizId);
                st.executeUpdate();
                
            }catch(SQLException e){
                System.out.println("ScoreExam: "+e);
            }
        }   // end for
        
        return quizId;
    }
    
    public List<Quiz_review> getAllQuiz_review (int quizId){
        List<Quiz_review> ls = new ArrayList<Quiz_review>();
        
        String sql = "select sa.username, sa.questionId, sa.answerId, a.Id as isSolutionId, a.explain, sa.quizId  from Student_Answer as sa inner join Answer as a on sa.questionId = a.questionId where a.isSolution = 1 and sa.quizId = " + quizId;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Quiz_review q = new Quiz_review(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                ls.add(q);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return ls;
    }
    
    public List<Student_Answer> getAllStudent_Answer(String username, int topicId){
        List<Student_Answer> ls = new ArrayList<Student_Answer>();
        String sql = "select * from Student_Answer where username= "+username+" and topicId = "+ topicId;
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Student_Answer s = new  Student_Answer(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                ls.add(s);
            }
        }catch(SQLException e){
            System.out.println("getAllStudetn_Answer: " + e);
        }
        
        return ls;
    }
    
    public void StoreScore (int quizId, float score){
        String sql = "update Quiz set score = ? where quiz.id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, score);
            st.setInt(2, quizId);
            
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    
    public static void main(String[] args) {
        QuizDAO qd = new QuizDAO();
        Quiz q = new Quiz(1, "C++", 10, 5);
        
        int[] quesId = new int[2];
        quesId[0]=1;    quesId[1]=2;
        int[] ansId = new int[2];
        ansId[0]=1;     ansId[1]=4;
        qd.ScoreExam("aalflattkp", q, quesId, ansId);
        
    }
    
}
