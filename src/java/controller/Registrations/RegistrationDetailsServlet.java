package controller.Registrations;

import dal.donghieu.UserDBContext;
import dal.san.CourseDBContext;
import dal.san.CourseRegisterDBContext;
import dal.san.PackageDBContext;
import dal.san.PricePackageDBContext;
import dal.san.StateDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.Price_Package;
import model.Registration;
import model.State;
import model.Package;
import model.User;
import Utils.DateTime;
import controller.authorization.BaseAuthController;

public class RegistrationDetailsServlet extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");
        if (action == null) {
            out.print("No action");
        } else if (action.toLowerCase().equals("add")) {
            addRoute(req, res);
        } else if (action.toLowerCase().equals("update")) {
            updateRoute(req, res);
        }
        req.setAttribute("action", action);
        req.getRequestDispatcher("registration-details.jsp").forward(req, res);
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Get user
        HttpSession session = req.getSession();
        User account = (User) session.getAttribute("account");
        if (account == null) {
            account = new User();
            account.setUserName("Admin");
        }
        // Declare Database context
        CourseRegisterDBContext courseRegisterDBC = new CourseRegisterDBContext();
        // Get params
        String statusRaw = req.getParameter("status");
        String courseIdRaw = req.getParameter("course");
        String username = req.getParameter("username");
        try {
            int statusId = Integer.parseInt(statusRaw);
            int courseId = Integer.parseInt(courseIdRaw);
            Registration registration = courseRegisterDBC.find(username, courseId);
            registration.setStateId(statusId);
            registration.setLastUpdateBy(account.getUserName());
            courseRegisterDBC.update(registration);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
        req.getRequestDispatcher("registrations-list.jsp").forward(req, res);
    }

    protected void updateRoute(HttpServletRequest req, HttpServletResponse res) {
        // Declare database context
        CourseRegisterDBContext courseRegisterDBC = new CourseRegisterDBContext();
        UserDBContext userDBC = new UserDBContext();
        CourseDBContext courseDBC = new CourseDBContext();
        PackageDBContext packageDBC = new PackageDBContext();
        PricePackageDBContext pricePackageDBC = new PricePackageDBContext();
        StateDBContext stateDBC = new StateDBContext();
        // Get params
        String courseRaw = req.getParameter("course");
        String username = req.getParameter("username");
        try {
            int courseId = Integer.parseInt(courseRaw);
            // Get registration by course id
            Registration registration = courseRegisterDBC.find(username, courseId);
            User user = userDBC.getUserByUsername(registration.getUsername());
            Course course = courseDBC.find(registration.getCourseId());
            model.Package pack = packageDBC.find(registration.getPackageId());

            Price_Package pricePackage = pricePackageDBC.findById(registration.getCourseId(), registration.getPackageId());

            State state = stateDBC.find(registration.getStateId());

            req.setAttribute("Username", user.getUserName());
            req.setAttribute("Course", course);
            req.setAttribute("Package", pack.getName());
            req.setAttribute("Price", pricePackage.getPrice());
            req.setAttribute("SalePrice", pricePackage.getPriceSale());
            req.setAttribute("Gender", user.isGender());
            req.setAttribute("Email", user.getEmail());
            req.setAttribute("Mobile", user.getPhoneNumber());
            req.setAttribute("RegistrationTime", registration.getDateRegister());
            req.setAttribute("Status", state.getId());
            req.setAttribute("ValidFrom", registration.getDateRegister());
            req.setAttribute("ValidTo", DateTime.addMonth(registration.getDateRegister(), Integer.parseInt(pack.getDuration())));
        } catch (NumberFormatException ne) {
            System.out.println(ne.toString());
        }
    }

    protected void addRoute(HttpServletRequest req, HttpServletResponse res) {
        CourseRegisterDBContext courseRegisterDBC = new CourseRegisterDBContext();
        UserDBContext userDBC = new UserDBContext();
        CourseDBContext courseDBC = new CourseDBContext();
        PackageDBContext packageDBC = new PackageDBContext();
        PricePackageDBContext pricePackageDBC = new PricePackageDBContext();
        StateDBContext stateDBC = new StateDBContext();
        // Get params
        String courseRaw = req.getParameter("course");
        String created = req.getParameter("created");
        try {
            int courseId = Integer.parseInt(courseRaw);
            // Get registration by course id
            Course course = courseDBC.find(courseId);
            ArrayList<Price_Package> pricePackages = pricePackageDBC.findByCourseId(courseId);
            ArrayList<Package> Packages = new ArrayList<>();
            for (Price_Package pricePackage : pricePackages) {
                Packages.add(packageDBC.find(pricePackage.getPackageId()));
            }
            req.setAttribute("courseId", courseId);
            req.setAttribute("Course", course);
            req.setAttribute("PricePackages", pricePackages);
            req.setAttribute("Packages", Packages);
            req.setAttribute("Created", created);
        } catch (NumberFormatException ne) {
            System.out.println(ne.toString());
        }
    }
}
