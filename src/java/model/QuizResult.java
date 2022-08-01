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
public class QuizResult {

    private int rightAnswers;
    private int notAnswers;
    private int totalAnswers;

    public QuizResult() {
    }

    public QuizResult(int rightAnswers, int notAnswers, int totalAnswers) {
        this.rightAnswers = rightAnswers;
        this.notAnswers = notAnswers;
        this.totalAnswers = totalAnswers;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getNotAnswers() {
        return notAnswers;
    }

    public void setNotAnswers(int notAnswers) {
        this.notAnswers = notAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    @Override
    public String toString() {
        return "QuizResult{" + "rightAnswers=" + rightAnswers + ", notAnswers=" + notAnswers + ", totalAnswers=" + totalAnswers + '}';
    }

}
