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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.minhlee.Answer;
import model.minhlee.Question;
import model.minhlee.Quiz_review;

/**

 * @author sannx

 */
public class quizReviewServlet extends HttpServlet {

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

            out.println("<title>Servlet quizReviewServlet</title>");

            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet quizReviewServlet at " + request.getContextPath() + "</h1>");
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
        QuizDAO qd = new QuizDAO();


        int quizId = -1, topicId = -1;
        try {
            quizId = Integer.parseInt(request.getParameter("quizId"));
            topicId = Integer.parseInt(request.getParameter("topicId"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }


        List<Quiz_review> qz = qd.getAllQuiz_review(quizId);
        List<Question> lq = qd.getAllQuestions(topicId);
        List<Answer> la = qd.getAllAnswer();
        List<Question> tempQ = new ArrayList<>();
        List<Answer> tempA = new ArrayList<>();
        for (int i = 0; i < lq.size(); i++) {
            for (int j = 0; j < qz.size(); j++) {

                if (lq.get(i).getId() == qz.get(j).getQuestionId()) {

                    tempQ.add(lq.get(i));
                    break;
                }
            }
        }


        for (int i = 0; i < la.size(); i++) {
            for (int j = 0; j < qz.size(); j++) {
                if (la.get(i).getQuesitonId() == qz.get(j).getQuestionId()) {

                    tempA.add(la.get(i));
                    break;
                }
            }
        }

        request.setAttribute("topicId", topicId);

        request.setAttribute("question", tempQ);
        request.setAttribute("answer", tempA);
        request.setAttribute("quiz_review", qz);
        request.getRequestDispatcher("Quiz/quizReview.jsp").forward(request, response);

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
        processRequest(request, response);
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
