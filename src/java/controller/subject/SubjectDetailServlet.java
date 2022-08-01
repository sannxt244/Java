/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.subject;

import controller.authorization.BaseAuthController;
import dal.hahieu.CourseDBContext;
import dal.hahieu.PackageDBContext;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.haha.Course;
import model.haha.Package;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "SubjectDetailServlet", urlPatterns = {"/subjectdetail"})
public class SubjectDetailServlet extends BaseAuthController {

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
            PackageDBContext pk = new PackageDBContext();
            List<Package> packageList = pk.getAllPackage();
            CourseDBContext db = new CourseDBContext();
            int courseid = Integer.parseInt(request.getParameter("courseid"));
            int packageId = Integer.parseInt(request.getParameter("packageId"));
            Course course = db.getCourseByCourseIdAndPackageId(courseid, packageId);
            
            List<Category> cateList = db.getAllCategory();
                
            request.setAttribute("packageList", packageList);
            request.setAttribute("cateList", cateList);
            request.setAttribute("course", course);
            request.getRequestDispatcher("view/subjectDetail.jsp").forward(request, response); 
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
