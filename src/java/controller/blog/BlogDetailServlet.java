package controller.blog;

import dal.san.BlogDBContext;
import dal.san.CategoryDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Category;

public class BlogDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Declare DAO
        BlogDBContext blogDBContext = new BlogDBContext();
        CategoryDBContext categoryDBContext = new CategoryDBContext();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Blog blog = blogDBContext.find(id);
            // Get category of blog

            Category category = categoryDBContext.findById(blog.getCategoryId());
            if (blog != null) {
                // List of lastest blog
                ArrayList<Blog> lastestBlogs = blogDBContext.findLastestByNumOfBlog(5);

                request.setAttribute("lastestBlogs", lastestBlogs);
                request.setAttribute("blog", blog);
                request.setAttribute("category", category);
                request.getRequestDispatcher("blogdetail.jsp").forward(request, response);
            } else {
                response.sendRedirect("blog");
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println(e.toString());
        }
    }
}
