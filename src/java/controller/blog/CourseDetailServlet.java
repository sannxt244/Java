package controller.blog;

import dal.san.CategoryDBContext;
import dal.san.CourseDBContext;
import dal.san.PricePackageDBContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Course;
import model.Price_Package;

public class CourseDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Declare DAO
        CategoryDBContext catagoryDB = new CategoryDBContext();
        CourseDBContext courceDB = new CourseDBContext();
        PricePackageDBContext pricePackageDB = new PricePackageDBContext();

        // Parse id to integer
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Course course = courceDB.find(id);
            Price_Package pricePackage = pricePackageDB.findByLowestPrice(course.getId());

            Category category = catagoryDB.findById(course.getCategoryId());


            req.setAttribute("course", course);
            req.setAttribute("category", category);
            req.setAttribute("pricePackage", pricePackage);
        } catch (NumberFormatException e) {
            res.sendRedirect("home");
            System.out.println(e.toString());
        }

        req.getRequestDispatcher("coursedetail.jsp").forward(req, res);
    }
}
