/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.lessonManagement;

import controller.authorization.BaseAuthController;
import dal.dai.lessonDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Lesson;

/**
 *
 * @author HDC
 */
@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 2,
//        maxFileSize = 1024 * 1204 * 10,
//        maxRequestSize = 1024 * 1024 * 50,
        location = "C:\\Users\\HDC\\Desktop\\project\\SWP391_Group6\\web\\videos"
)
public class addLessonServlet extends BaseAuthController {

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
            out.println("<title>Servlet addLessonServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addLessonServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("lessonAdd.jsp").forward(request, response);
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
       String video = "";
        Part part = null;
        String filename = "";
        part = request.getPart("video");
        filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (filename.endsWith(".mp4")) {
                part.write(File.separator + filename);
                video = "videos" + File.separator + filename;
            }
        
        String name=request.getParameter("name");
        String content=request.getParameter("content");
        int subjecttopicId = Integer.parseInt(request.getParameter("subjecttopicId"));
        String type="lesson";
        int order = Integer.parseInt(request.getParameter("order"));
        
        Lesson l= new Lesson(name, video, content, subjecttopicId, type, order);
        lessonDAO lesson=new lessonDAO();
        
        if(lesson.checkOrder(order, "Lesson")==true){
            request.setAttribute("mess", "Order da ton tai, xin moi ban nhap lai");
            request.getRequestDispatcher("lessonAdd.jsp").forward(request, response);
            return;
        }
                
        lesson.addLesson(l);
        response.sendRedirect("listLessonServlet");
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
