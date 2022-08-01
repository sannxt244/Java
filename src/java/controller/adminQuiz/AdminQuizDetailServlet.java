/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminQuiz;

import controller.authorization.BaseAuthController;
import dal.hahieu.QuizFormatDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.haha.QuizFormat;
import model.haha.Subject_Topic;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "AdminQuizDetailServlet", urlPatterns = {"/AdminQuizDetailServlet"})
public class AdminQuizDetailServlet extends BaseAuthController {

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
            out.println("<title>Servlet AdminQuizDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminQuizDetailServlet at " + request.getContextPath() + "</h1>");
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
        QuizFormatDBContext db = new QuizFormatDBContext();
        int quizId = Integer.parseInt(request.getParameter("quizId"));

        QuizFormat quizFormat = db.getQuizFormatDetail(quizId);
        List<Subject_Topic> subjectList = db.getAllSubjectTopic();

        request.setAttribute("quizFormat", quizFormat);
        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("view/QuizFormatDetail.jsp").forward(request, response); 
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
        QuizFormatDBContext db = new QuizFormatDBContext();
        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
        String name = request.getParameter("quiz_name");
        int quiz_Duration = Integer.parseInt(request.getParameter("quiz_Duration"));
        String description = request.getParameter("description");
        int quiz_number = Integer.parseInt(request.getParameter("quiz_number"));
        String quiz_type = request.getParameter("quiz_type"); 
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        
        db.editQuiz_Format(quizId, subject_id, name, quiz_Duration, description, quiz_number, quiz_type);
        String abc = "/AdminQuizDetailServlet" + "?" + "quizId=" + quizId;
        response.sendRedirect(request.getContextPath() + abc);
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
