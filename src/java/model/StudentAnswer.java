/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author sannx
 */
public class StudentAnswer {

    private String username;
    private int answerId;
    private int questionId;
    private int quizId;

    public StudentAnswer() {
    }

    public StudentAnswer(String username, int answerId, int questions, int quizId) {
        this.username = username;
        this.answerId = answerId;
        this.questionId = questions;
        this.quizId = quizId;
    }

    public String getStudent() {
        return username;
    }

    public void setStudent(String username) {
        this.username = username;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        try {
            this.answerId = Integer.parseInt(answerId);
        } catch (Exception e) {
            this.answerId = -1;
        }
    }

    public int getQuestions() {
        return questionId;
    }

    public void setQuestions(int questions) {
        this.questionId = questions;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

}
