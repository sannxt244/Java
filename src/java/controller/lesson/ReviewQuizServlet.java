package controller.lesson;

import controller.authorization.BaseAuthController;
import dal.minhlee.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.minhlee.Answer;
import model.minhlee.Question;
import model.minhlee.Quiz;
import model.minhlee.Quiz_review;
import model.minhlee.Subject_Topic;

@WebServlet(name = "ReviewQuizServlet", urlPatterns = {"/quiz-review"})
public class ReviewQuizServlet extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter o = response.getWriter();

        QuizDAO qd = new QuizDAO();

        String username = request.getParameter("username");
        int topicId = -1, duration = -1;
        try {
            topicId = Integer.parseInt(request.getParameter("topicId"));
            duration = Integer.parseInt(request.getParameter("duration"));
        } catch (NumberFormatException e) {
            System.out.println("topicId and duration: " + e);
        }

//        o.println("topicId"+topicId);
//        o.println("duration"+duration);
        String name = "";   // name of subject topic
        List<Subject_Topic> sj = qd.getAllSubject_Topic();
        for (int i = 0; i < sj.size(); i++) {
            if (sj.get(i).getId() == topicId) {
                name = sj.get(i).getName();
            }
        }
        List<Question> ls = qd.getAllQuestions(topicId);
        List<Question> temp = new ArrayList<Question>();

        int[] quesId = new int[ls.size()];
        int[] ansId = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            try {
                quesId[i] = Integer.parseInt(request.getParameter("quesId" + i));
                for (int j = 0; j < ls.size(); j++) {

                    if (ls.get(j).getId() == quesId[i]) {
                        temp.add(ls.get(i));
//                        o.println(temp.get(i).getContent());
                    }
                }
                if (request.getParameter("ans" + i) != null) {
                    ansId[i] = Integer.parseInt(request.getParameter("ans" + i));
                } else {
                    ansId[i] = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("parse Int: " + e);
            }
            o.println(quesId[i]);
            o.println(ansId[i]);
        }

        o.println(username);
        Quiz q = new Quiz(topicId, name, duration, quesId.length);
        int quizId = qd.ScoreExam(username, q, quesId, ansId); // get quizId
        List<Answer> la = qd.getAllAnswer();

        List<Quiz_review> qz = qd.getAllQuiz_review(quizId);
        int count = 0;
        for (int i = 0; i < qz.size(); i++) {
            if (qz.get(i).getAnswerId() == qz.get(i).getIsSolutionId()) {
                count++;
            }
        }
        o.println(count);
        float score = (float) count * 10 / qz.size();
//        o.println(score);
//        score = (float) Math.round(score * 100) / 100;
        qd.StoreScore(quizId, score);

//        request.setAttribute("username", username);
//        request.setAttribute("topicId", topicId);
        request.setAttribute("quizId", quizId);
        request.setAttribute("quiz_review", qz);
        request.setAttribute("question", temp);
        request.setAttribute("answer", la);
//        o.print("yolo");
        request.getRequestDispatcher("Quiz/quizReview.jsp").forward(request, response);
    }
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processGet(request, response);
    }
}
