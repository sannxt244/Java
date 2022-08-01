/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing;

import controller.authorization.BaseAuthController;
import dal.donghieu.PostDBContext;
import dal.san.CategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Category;
import model.User;

/**
 *
 * @author dell
 */
public class PostListServlet extends BaseAuthController {

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
        PostDBContext db = new PostDBContext();
        CategoryDBContext categoryDBContext = new CategoryDBContext();
        User user = (User) request.getSession().getAttribute("account");
        String page_Raw = request.getParameter("page");
        String categoryId_Raw = request.getParameter("categoryId");
        String search = request.getParameter("search");
        if (request.getSession().getAttribute("page") != null) {
            page_Raw = (String) request.getSession().getAttribute("page");
            request.getSession().removeAttribute("page");
        }
        if (request.getSession().getAttribute("categoryId") != null) {
            categoryId_Raw = (String) request.getSession().getAttribute("categoryId");
            request.getSession().removeAttribute("categoryId");

        }
        if (request.getSession().getAttribute("search") != null) {
            search = (String) request.getSession().getAttribute("search");
            request.getSession().removeAttribute("search");

        }
        int page = 1;
        int categoryId = 0;

        if (page_Raw != null) {
            page = Integer.parseInt(page_Raw);
        }
        if (categoryId_Raw != null) {
            categoryId = Integer.parseInt(categoryId_Raw);
        }
        int PAGE_SIZE = 10;
        int totalRow = 0;
        int totalPage = 0;
        ArrayList<Blog> blogs = new ArrayList<>();
        if (categoryId != 0) {
            blogs = db.getBlogsByCategoryID(user, categoryId, page, PAGE_SIZE);
            totalRow = db.getTotalRowByCategoryId(user, categoryId);
        } else if (search != null && search.length() > 0) {
            blogs = db.getBlogsBySearch(user, search, page, PAGE_SIZE);
            totalRow = db.getTotalRowBySearch(user, search);
        } else {
            blogs = db.getAllBlog(user, page, PAGE_SIZE);
            totalRow = db.getTotalRow(user);
        }

        ArrayList<Category> categories = categoryDBContext.findAll();

        totalPage = (totalRow % 2 == 0) ? (totalRow / PAGE_SIZE) : (totalRow / PAGE_SIZE + 1);
        int start = Math.max(1, page - 10);
        int end = Math.min(page + 1, totalPage);
        request.setAttribute("end", end);
        request.setAttribute("start", start);
        request.setAttribute("page", page);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("categories", categories);
        request.setAttribute("search", search);
        request.getRequestDispatcher("view/postlist.jsp").forward(request, response);

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
        User user = (User) request.getSession().getAttribute("account");
        request.setCharacterEncoding("UTF-8");
        PostDBContext db = new PostDBContext();
        CategoryDBContext categoryDBContext = new CategoryDBContext();
        String page_Raw = request.getParameter("page");
        String categoryId_Raw = request.getParameter("categoryId");
        String search = request.getParameter("search");
        int page = 1;
        int categoryId = 0;

        if (page_Raw != null) {
            page = Integer.parseInt(page_Raw);
        }
        if (categoryId_Raw != null) {
            categoryId = Integer.parseInt(categoryId_Raw);
        }

        int PAGE_SIZE = 10;
        int totalRow = 0;
        int totalPage = 0;
        ArrayList<Blog> blogs = new ArrayList<>();
        if (categoryId != 0) {
            blogs = db.getBlogsByCategoryID(user, categoryId, page, PAGE_SIZE);
            totalRow = db.getTotalRowByCategoryId(user, categoryId);
        } else if (search != null && search.length() > 0) {
            blogs = db.getBlogsBySearch(user, search, page, PAGE_SIZE);
            totalRow = db.getTotalRowBySearch(user, search);
        } else {
            blogs = db.getAllBlog(user, page, PAGE_SIZE);
            totalRow = db.getTotalRow(user);
        }

        ArrayList<Category> categories = categoryDBContext.findAll();

        totalPage = (totalRow % 2 == 0) ? (totalRow / PAGE_SIZE) : (totalRow / PAGE_SIZE + 1);
        int start = Math.max(1, page - 10);
        int end = Math.min(page + 1, totalPage);
        request.setAttribute("end", end);
        request.setAttribute("start", start);
        request.setAttribute("page", page);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("categories", categories);
        request.setAttribute("search", search);
        request.getRequestDispatcher("view/postlist.jsp").forward(request, response);

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
