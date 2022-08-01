/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.question;

import controller.authorization.BaseAuthController;
import dal.donghieu.QuestionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answer;
import model.Course;
import model.Question;
import model.Topic;

/**
 *
 * @author dell
 */
public class QuestionDetailServlet extends BaseAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isActive_Raw = request.getParameter("isActive");
        if (isActive_Raw != null && isActive_Raw.length() > 0) {
            boolean isActive = isActive_Raw.equals("1") ? true : false;
            QuestionDBContext db = new QuestionDBContext();
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            db.setStatus(questionId, isActive);
            int page = Integer.parseInt(request.getParameter("page"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            int topicId = Integer.parseInt(request.getParameter("topicId"));
            String search = request.getParameter("search");
            request.getSession().setAttribute("page", page);
            request.getSession().setAttribute("courseId1", courseId);
            request.getSession().setAttribute("topicId", topicId);
            request.getSession().setAttribute("search", search);
            response.sendRedirect("questionlist");

        } else {

            int questionId;
            String questionId_Raw = request.getParameter("questionId");
            if (questionId_Raw != null && questionId_Raw.length() > 0 || request.getSession().getAttribute("questionId") != null) {
                if (request.getSession().getAttribute("questionId") != null) {
                    questionId = (int) request.getSession().getAttribute("questionId");
                    request.getSession().removeAttribute("questionId");
                } else {
                    questionId = Integer.parseInt(questionId_Raw);
                }
                QuestionDBContext questionDB = new QuestionDBContext();
                Question question = questionDB.getQuestionById(questionId);
                int courseId = questionDB.getcourseIdByQuestionId(question.getId());
                ArrayList<Course> courses = questionDB.getAllCourse();
                // response.getWriter().print(question.getAnswerList().size());
                String explain = questionDB.getExplainByQuestionId(question.getId());

                request.setAttribute("courseId", courseId);
                request.setAttribute("explain", explain);
                request.setAttribute("courses", courses);
                request.setAttribute("question", question);
                request.getRequestDispatcher("view/questionDetail.jsp").forward(request, response);
            } else {
                response.sendRedirect("questionlist");
            }

        }
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDBContext questionDB = new QuestionDBContext();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String[] topicId_Raw = request.getParameterValues("topicId");
        int check = 0;
        for (String string : topicId_Raw) {
            if (string.equals("-1")) {
                check = 1;
            }
        }

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        if (check == 1) {
            topicId = -1;
        }
        String content = request.getParameter("content");
        boolean isActive = request.getParameter("isActive").equals("1") ? true : false;
        String[] answers = request.getParameterValues("answers");
        int isSolutionId = Integer.parseInt(request.getParameter("isSolution"));
        ArrayList<Answer> updateAnswers = questionDB.getAnswerByQuestionId(questionId);
        String explain = request.getParameter("explain");
        for (int i = 0; i < updateAnswers.size(); i++) {
            Answer a = updateAnswers.get(i);
            a.setContent(answers[i]);
            if (a.getId() == isSolutionId) {
                a.setIsSolution(true);
                a.setExplain(explain);
            } else {
                a.setExplain(null);
                a.setIsSolution(false);
            }
        }
        Question question = questionDB.getQuestionById(questionId);
        question.setAnswerList(updateAnswers);
        question.setContent(content);
        question.setIsActived(isActive);
        question.setTopicId(topicId);
        questionDB.updateQuestion(question);
        request.getSession().setAttribute("questionId", questionId);

        response.sendRedirect("questiondetail");
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
