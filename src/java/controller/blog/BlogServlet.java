package controller.blog;

import dal.san.BlogDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import Utils.Pagination;

public class BlogServlet extends HttpServlet {

    final int ROWS_OF_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String category = request.getParameter("category") == null ? "" : request.getParameter("category");
        // Declare Database Context
        BlogDBContext blogDB = new BlogDBContext();
        // Declare list blog

        ArrayList<Blog> lastestBlogs = blogDB.findLastestByNumOfBlog(5);// List of lastest blog
        ArrayList<Blog> blogs;

        try {
            int page = Integer.parseInt(request.getParameter("page"));
            blogs = blogDB.findByFilter(search, category, page, ROWS_OF_PAGE);
            request.setAttribute("pageList", Pagination.get(page, blogDB.findByFilter(search, category).size() / ROWS_OF_PAGE == 0 ? blogDB.findByFilter(search, category).size() / ROWS_OF_PAGE : blogDB.findByFilter(search, category).size() / ROWS_OF_PAGE + 1));
        } catch (NumberFormatException e) {
            blogs = blogDB.findByFilter(search, category, 1, ROWS_OF_PAGE);
            request.setAttribute("pageList", Pagination.get(1, blogDB.findByFilter(search, category).size() / ROWS_OF_PAGE == 0 ? blogDB.findByFilter(search, category).size() / ROWS_OF_PAGE : blogDB.findByFilter(search, category).size() / ROWS_OF_PAGE + 1));
            System.out.println(e.toString());
        }

        request.setAttribute("lastestBlogs", lastestBlogs);
        request.setAttribute("blogs", blogs);
        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }

}
