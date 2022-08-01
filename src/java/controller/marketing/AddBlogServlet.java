/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing;

import controller.authorization.BaseAuthController;
import dal.donghieu.PostDBContext;
import dal.san.CategoryDBContext;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Blog;
import model.Category;
import model.User;

/**
 *
 * @author dell
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1204 * 10,
        maxRequestSize = 1024 * 1024 * 50,
        location = "E:\\Projects\\merge4\\web\\img\\blog"
)
public class AddBlogServlet extends BaseAuthController {

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
        CategoryDBContext categoryDBContext = new CategoryDBContext();

        ArrayList<Category> categories = categoryDBContext.findAll();

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("view/addblog.jsp").forward(request, response);
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
        PostDBContext db = new PostDBContext();
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String image = "";

        Part part = part = request.getPart("image");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (filename != null && filename.length() > 0) {
            String realPath = request.getServletContext().getRealPath("/img/blog");

            String fullpath = realPath + "/" + filename;
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(File.separator + filename);
            image = "img" + File.separator + "blog" + File.separator + filename;
        }

        User user = (User) request.getSession().getAttribute("account");
        String username = user.getUserName();
        db.addBlog(title, content, categoryId, image, username);

        request.getSession().setAttribute("addSuccess", "Add blog successfully");
        response.sendRedirect("postlist");

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
