/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.lessonManagement;

import controller.authorization.BaseAuthController;
import dal.dai.lessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lesson;
import model.quizFormat;
import model.subjectTopic;

/**
 *
 * @author HDC
 */
public class listLessonServlet extends BaseAuthController {

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
            out.println("<title>Servlet listLessonServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listLessonServlet at " + request.getContextPath() + "</h1>");
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        lessonDAO dao = new lessonDAO();
        List<subjectTopic> list = new ArrayList();
        String sid = request.getParameter("sid");
        if (sid != null) {
            request.getSession().setAttribute("courseId", sid);
        } else {
            sid = (String) request.getSession().getAttribute("courseId");
        }

        list = dao.AllSubjectTopic(sid);
        for (subjectTopic sub : list) {
            List<Lesson> listLess = dao.LessonBySubjectTopicId(sub.getId());
            List<quizFormat> listQuiz = dao.QuizFormatBySubjectTopicId(sub.getId());
            sub.setL(listLess);
            sub.setQ(listQuiz);
        }
        request.setAttribute("sid", sid);
        request.setAttribute("listSubjectTopic", list);
        request.getRequestDispatcher("lessonlist.jsp").forward(request, response);

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
