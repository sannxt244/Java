/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.subject;

import controller.authorization.BaseAuthController;
import dal.hahieu.CourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "EditSubjectLessonServlet", urlPatterns = {"/EditSubjectLessonServlet"})
public class EditSubjectLessonServlet extends BaseAuthController {

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
         //String name, int isActive, String description, int categoryId, String img, int courseId, int packageId
        
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int packageId = Integer.parseInt(request.getParameter("packageId"));
        String name = request.getParameter("course_name");
        int isActive = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String img = request.getParameter("img");
        int price = Integer.parseInt(request.getParameter("price"));
        int priceSale = Integer.parseInt(request.getParameter("priceSale"));
             
        CourseDBContext sd = new CourseDBContext();
        sd.editCourseDetail(name, isActive, description, categoryId, img, courseId, packageId);     
        //int price, int priceSale
        
        sd.editCourseDetailPrice(price, priceSale, courseId, packageId);
        String abc = "/subjectdetail" + "?" + "courseid=" + courseId + "&" + "packageId=" + packageId;
        response.sendRedirect(request.getContextPath() + abc);
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
