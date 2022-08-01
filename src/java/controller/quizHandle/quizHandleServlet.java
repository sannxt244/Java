/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.quizHandle;

import dal.minhlee.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.print.Printer;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
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
public class quizHandleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet quizHandleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet quizHandleServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        PrintWriter o = response.getWriter();
        QuizDAO qd = new QuizDAO();

        String username = request.getParameter("username");
        int topicId = -1;
        try {
            topicId = Integer.parseInt(request.getParameter("topicId"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        List<Question> ls = qd.getAllQuestions(topicId);
        List<Answer> l = qd.getAllAnswer();

        Random r = new Random();
        List<Question> quesRandom = new ArrayList<Question>();
        List<Answer> ans = new ArrayList<Answer>(); // base on question

        int count = -1;   // number of question for Quiz table in database
        if (ls.size() > 50) {
            count = 50;
        } else {
            count = ls.size();
        }

//        o.print(count);
        for (int i = 0; i < count; i++) {
            int randomIndex = r.nextInt(ls.size());
//            o.println(randomIndex);
            quesRandom.add(ls.get(randomIndex));
            ls.remove(randomIndex);
            for (int j = 0; j < l.size(); j++) {
                if (l.get(j).getQuesitonId() == quesRandom.get(i).getId()) { // check if quesId of ans == id of Question
                    ans.add(l.get(j));
                }
            }
        }

        request.setAttribute("username", username);
        request.setAttribute("topic", topicId);
        request.setAttribute("question", quesRandom);
        request.setAttribute("answer", ans);
        request.getRequestDispatcher("Quiz/quizHandle.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        o.println("topicId"+topicId);
        o.println("duration"+duration);
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
//        o.println("quizId: "+quizId);
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
        request.setAttribute("topicId", topicId);
        request.setAttribute("quizId", quizId);
        request.setAttribute("quiz_review", qz);
        request.setAttribute("question", temp);
        request.setAttribute("answer", la);
//        o.print("yolo");
        request.getRequestDispatcher("Quiz/quizReview.jsp").forward(request, response);
//
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
