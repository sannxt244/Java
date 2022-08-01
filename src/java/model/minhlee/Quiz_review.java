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
public class Quiz_review {

    private String username;
    private int questionId;
    private int answerId;
    private int isSolutionId;
    private String explain;
    private int quizId;
    private float score;
    //

    public Quiz_review(String username, int questionId, int answerId, int isSolutionId, String explain, int quizId, float score) {
        this.username = username;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isSolutionId = isSolutionId;
        this.explain = explain;
        this.quizId = quizId;
        this.score = score;
    }

    public Quiz_review() {
    }

    public Quiz_review(String username, int questionId, int answerId, int isSolutionId, String explain, int quizId) {
        this.username = username;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isSolutionId = isSolutionId;
        this.explain = explain;
        this.quizId = quizId;
    }

    //
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getIsSolutionId() {
        return isSolutionId;
    }

    public void setIsSolutionId(int isSolutionId) {
        this.isSolutionId = isSolutionId;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

}
