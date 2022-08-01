
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
import java.nio.file.Path;
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
public class PostDetailServlet extends BaseAuthController {

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
        request.setCharacterEncoding("UTF-8");
        String page_Raw = request.getParameter("page");
        String categoryId_Raw = request.getParameter("categoryId");
        String search = request.getParameter("search");
        int page = 1;
        int categoryId = 0;
        if (search == null || search.length() <= 0) {
            search = "";
        }
        if (page_Raw != null) {
            page = Integer.parseInt(page_Raw);
        }
        if (categoryId_Raw != null) {
            categoryId = Integer.parseInt(categoryId_Raw);
        }

        String blogId_Raw = request.getParameter("blogId");

        if (blogId_Raw != null && blogId_Raw.length() > 0) {
            PostDBContext db = new PostDBContext();
            int id = Integer.parseInt(blogId_Raw);
            Blog blog = db.getBlogById(id);
            if (blog != null) {
                CategoryDBContext categoryDBContext = new CategoryDBContext();

                ArrayList<Category> categories = categoryDBContext.findAll();

                request.setAttribute("categories", categories);
                request.setAttribute("blog", blog);
                request.setAttribute("page", page);
                request.setAttribute("categoryId", categoryId);
                request.setAttribute("search", search);
                request.getRequestDispatcher("view/postdetail.jsp").forward(request, response);

            } else {
                response.sendRedirect("postlist?page=" + page + "&categoryId=" + categoryId + "&search=" + search);
            }

        } else {
            response.sendRedirect("postlist?page=" + page + "&categoryId=" + categoryId + "&search=" + search);
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
        PostDBContext db = new PostDBContext();
        request.setCharacterEncoding("UTF-8");
        String page = request.getParameter("page");
        String search = request.getParameter("search");
        String categoryId = request.getParameter("categoryId");
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        String isActive_raw = request.getParameter("isActive");
        boolean isActive = isActive_raw.equals("1") ? true : false;
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String formPostList = request.getParameter("fromPostlist");
        if ((content == null || content.length() <= 0) && (formPostList == null || formPostList.length() <= 0)) {
            Blog blog = db.getBlogById(blogId);
            CategoryDBContext categoryDBContext = new CategoryDBContext();

            ArrayList<Category> categories = categoryDBContext.findAll();

            request.setAttribute("categories", categories);
            request.setAttribute("blog", blog);
            request.setAttribute("page", page);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("search", search);
            request.setAttribute("content_error", "Please enter content!");
            request.getRequestDispatcher("view/postdetail.jsp").forward(request, response);

        }
        String categoryIdAfterUpdate_Raw = request.getParameter("categoryIdAfterUpdate");
        String image = "";
        int categoryIdAfterUpdate = 0;
        if (categoryIdAfterUpdate_Raw != null && categoryIdAfterUpdate_Raw.length() > 0) {
            categoryIdAfterUpdate = Integer.parseInt(categoryIdAfterUpdate_Raw);
        }
        Part part = null;
        String filename = "";

        if (formPostList == null || formPostList.length() <= 0) {
            part = request.getPart("image");
            filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        }

        if (filename != null && filename.length() > 0) {
            String realPath = request.getServletContext().getRealPath("/img/blog");

            String fullpath = realPath + "/" + filename;
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            if (filename.endsWith(".jpg") || filename.endsWith(".png")) {
                part.write(File.separator + filename);
                image = "img" + File.separator + "blog" + File.separator + filename;
            } else {

                Blog blog = db.getBlogById(blogId);
                if (blog != null) {
                    CategoryDBContext categoryDBContext = new CategoryDBContext();

                    ArrayList<Category> categories = categoryDBContext.findAll();

                    request.setAttribute("categories", categories);
                    request.setAttribute("blog", blog);
                    request.setAttribute("page", page);
                    request.setAttribute("categoryId", categoryId);
                    request.setAttribute("search", search);
                    request.setAttribute("file_Error", "Please upload image file only!");
                    request.getRequestDispatcher("view/postdetail.jsp").forward(request, response);

                }

            }
        }

        Blog blog = db.getBlogById(blogId);
        blog.setIsActived(isActive);
        if (title != null && title.length() > 0) {
            blog.setTitle(title);
        }
        if (content != null && content.length() > 0) {
            blog.setContent(content);
        }
        if (categoryIdAfterUpdate != 0) {
            blog.setCategoryId(categoryIdAfterUpdate);
        }
        if (image != null && image.length() > 0) {
            blog.setImage(image);
        }
        db.updateBlog(blog);
        CategoryDBContext categoryDBContext = new CategoryDBContext();

        ArrayList<Category> categories = categoryDBContext.findAll();

        request.setAttribute("categories", categories);
        request.setAttribute("blog", blog);
        request.setAttribute("successMsg", "Update successfully");

        if (formPostList != null && formPostList.length() > 0) {
            request.getSession().setAttribute("page", page);
            request.getSession().setAttribute("categoryId", categoryId);
            request.getSession().setAttribute("search", search);
            response.sendRedirect("postlist");
        } else {
            request.getRequestDispatcher("view/postdetail.jsp").forward(request, response);
        }

    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Project" + File.separator + "SWP391_Group6" + File.separator + "web" + File.separator + "img" + File.separator + "blog");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
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
