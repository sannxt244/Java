package controller.lesson;

import controller.authorization.BaseAuthController;
import dal.san.QuestionDBContext;
import dal.san.QuizFormatDBContext;
import dal.san.StudentAnswerDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Quiz;
import model.QuizResult;
import model.StudentAnswer;
import model.User;
import model.quizFormat;

public class QuestionServlet extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        // Get DB Context
        QuestionDBContext questionDBC = new QuestionDBContext();
        QuizFormatDBContext quizFormatDB = new QuizFormatDBContext();
        // Get parameters
        String topicIdRaw = request.getParameter("topic");
        // Is done quiz
        boolean isDone = false;
        try {
            int topicId = Integer.parseInt(topicIdRaw);
            if (account != null) {
                isDone = questionDBC.isDoneQuiz(account.getUserName(), topicId);
                request.setAttribute("username", account.getUserName());
                if (isDone == true) {
                    Quiz quiz = questionDBC.getQuizByTopicId(topicId);
                    QuizResult quizResult = getResultOfQuiz(quiz.getId(), account.getUserName());
                    request.setAttribute("quiz", quiz);
                    request.setAttribute("quizResult", quizResult);
                }
            }
            quizFormat quiformat = quizFormatDB.findByTopic(topicId);
            request.setAttribute("quizFormat", quiformat);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
        request.setAttribute("isDone", isDone);
        request.setAttribute("isDone", isDone);
        request.getRequestDispatcher("quiz-lesson.jsp").forward(request, response);
    }
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processGet(request, response);
    }
    public QuizResult getResultOfQuiz(int quizId, String username) {
        StudentAnswerDBContext studentAnswerDB = new StudentAnswerDBContext();

        ArrayList<StudentAnswer> studentAnswers = studentAnswerDB.findByQuiz(username, quizId);

        int numberOfRightAnswer = 0;
        int numberOfNotAnswer = 0;

        for (StudentAnswer studentAnswer : studentAnswers) {
            if (studentAnswerDB.checkStudentAnswer(username, studentAnswer.getQuestions()) == 1) {
                numberOfRightAnswer++;
            } else if (studentAnswerDB.checkStudentAnswer(username, studentAnswer.getQuestions()) == -1) {
                numberOfNotAnswer++;
            }
        }
        return new QuizResult(numberOfRightAnswer, numberOfNotAnswer, studentAnswers.size());
    }

}
