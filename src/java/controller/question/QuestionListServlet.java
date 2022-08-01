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
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Question;
import model.Role;
import model.Topic;
import model.User;

/**
 *
 * @author dell
 */
public class QuestionListServlet extends BaseAuthController {

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
//        User a = new User();
//        a.setUserName("expert1");
//        Role r = new Role();
//        r.setId(2);
//        a.setRole(r);
        request.setCharacterEncoding("UTF-8");
        QuestionDBContext questionDB = new QuestionDBContext();
        String page_Raw = request.getParameter("page");
        String courseId_Raw = request.getParameter("courseId");
        String[] topicId_raw = request.getParameterValues("topicId");
        String search = request.getParameter("search");

        int page = 1;
        int courseId = 0;
        int topicId = 0;

        if (page_Raw != null) {
            page = Integer.parseInt(page_Raw);
        }
        if (courseId_Raw != null) {
            courseId = Integer.parseInt(courseId_Raw);
        }
        if (topicId_raw != null) {
            for (String string : topicId_raw) {
                if (string != null && string.length() > 0 && !string.equals("0")) {
                    topicId = Integer.parseInt(string);
                    break;
                }
            }
        }
        if (request.getSession().getAttribute("topicId") != null) {
            topicId = (int) request.getSession().getAttribute("topicId");
            request.getSession().removeAttribute("topicId");
        }
        if (request.getSession().getAttribute("page") != null) {
            page = (int) request.getSession().getAttribute("page");
            request.getSession().removeAttribute("page");
        }
        if (request.getSession().getAttribute("courseId1") != null) {
            courseId = (int) request.getSession().getAttribute("courseId1");
            request.getSession().removeAttribute("courseId1");

        }

        if (request.getSession().getAttribute("search") != null) {
            search = (String) request.getSession().getAttribute("search");
            request.getSession().removeAttribute("search");

        }

        User user = (User) request.getSession().getAttribute("account");
        // user = a;
        int PAGE_SIZE = 10;
        int totalRow = 0;
        int totalPage = 0;
        ArrayList<Question> questions = questionDB.getAllQuestion(user, page, PAGE_SIZE, courseId, topicId, search);
        ArrayList<Course> courses = questionDB.getAllCourse();
        totalRow = questionDB.getTotalRow(user, courseId, topicId, search);

        totalPage = (totalRow % 2 == 0) ? (totalRow / PAGE_SIZE) : (totalRow / PAGE_SIZE + 1);
        int start = Math.max(1, page - 10);
        int end = Math.min(page + 1, totalPage);
        request.setAttribute("end", end);
        request.setAttribute("start", start);
        request.setAttribute("page", page);
        request.setAttribute("courseId", courseId);
        request.setAttribute("topicId", topicId);
        request.setAttribute("search", search);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("courses", courses);
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("view/questionlist.jsp").forward(request, response);

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
        processRequest(request, response);
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
