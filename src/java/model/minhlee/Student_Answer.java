/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.minhlee;


/**
 *
 * @author Minh Lee
 */
public class Student_Answer {
    private String username;
    private int answerId;
    private int questionId;
    private int quizId;
    //

    public Student_Answer() {
    }

    public Student_Answer(String username, int answerId, int questionId, int quizId) {
        this.username = username;
        this.answerId = answerId;
        this.questionId = questionId;
        this.quizId = quizId;
    }

    //

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    
}
