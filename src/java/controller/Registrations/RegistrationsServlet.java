package controller.Registrations;

import Utils.DateTime;
import controller.authorization.BaseAuthController;
import dal.donghieu.UserDBContext;
import dal.san.CourseDBContext;
import dal.san.CourseRegisterDBContext;
import dal.san.PackageDBContext;
import dal.san.PricePackageDBContext;
import dal.san.RegistrationsDBContext;
import dal.san.StateDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Registration;
import model.User;
import model.Package;
import model.Price_Package;
import model.State;

public class RegistrationsServlet extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("registrations-list.jsp").forward(req, res);
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Declare database context
        CourseRegisterDBContext courseRegisterDBC = new CourseRegisterDBContext();
        RegistrationsDBContext registrationDBC = new RegistrationsDBContext();

        // Get parameter
        String courseIdRaw = req.getParameter("course");// Course Id
        String searchName = req.getParameter("searchName").toLowerCase().trim();// Username
        String searchFrom = req.getParameter("searchFrom").toLowerCase().trim();// search registration from
        String searchTo = req.getParameter("searchTo").toLowerCase().trim();// search registration to
        String searchStatus = req.getParameter("searchStatus").toLowerCase().trim();// search registration status
        String searchEmail = req.getParameter("searchEmail").toLowerCase().trim();// search user email
        String sortField = req.getParameter("sortField");// sort
        try {
            int courseId = Integer.parseInt(courseIdRaw);
            // Get registration by course id
            ArrayList<Registration> registrations = courseRegisterDBC.findByCourseId(courseId);
            registrations = registrationDBC.filter(courseId, searchName, searchFrom, searchTo, searchStatus, searchEmail, sortField);
            pushData(res, registrations);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void pushData(HttpServletResponse res, ArrayList<Registration> registrations) throws ServletException, IOException {
        UserDBContext userDBC = new UserDBContext();
        CourseDBContext courseDBC = new CourseDBContext();
        PackageDBContext packageDBC = new PackageDBContext();
        PricePackageDBContext pricePackageDBC = new PricePackageDBContext();
        StateDBContext stateDBC = new StateDBContext();

        try (PrintWriter out = res.getWriter()) {
            for (Registration registration : registrations) {
                User user = userDBC.getUserByUsername(registration.getUsername());
                Course course = courseDBC.find(registration.getCourseId());
                Package pack = packageDBC.find(registration.getPackageId());

                int courseId = registration.getCourseId();
                int packageId = registration.getPackageId();
                Price_Package pricePackage = pricePackageDBC.findById(courseId, packageId);

                State state = stateDBC.find(registration.getStateId());
                out.println("<tr>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + registration.getUsername() + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + user.getEmail() + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + registration.getDateRegister() + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + course.getName() + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + pack.getName() + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + pricePackage.getPrice() + "</th>\n");
                switch (state.getId()) {
                    case 1:
                        out.println("<th scope=\"row\" class=\"fw-normal\"><div class=\"bg-success px-2 py-1 rounded text-light\">" + state.getState() + "</div></th>\n");
                        break;
                    case 2:
                        out.println("<th scope=\"row\" class=\"fw-normal\"><div class=\"bg-danger px-2 py-1 rounded text-light\">" + state.getState() + "</div></th>\n");
                        break;
                    default:
                        out.println("<th scope=\"row\" class=\"fw-normal\"><div class=\"bg-warning px-2 py-1 rounded text-light\">" + state.getState() + "</div></th>\n");
                        break;
                }

                out.println("   <th scope=\"row\" class=\"fw-normal\">" + registration.getDateRegister() + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + DateTime.addMonth(registration.getDateRegister(), Integer.parseInt(pack.getDuration())) + "</th>\n"
                        + "     <th scope=\"row\" class=\"fw-normal\">" + registration.getLastUpdateBy() + "</th>\n"
                        + "     <th scope=\"row\">\n"
                        + "         <a class=\"btn btn-success\" href=\"registration-details?action=update&course=" + course.getId() + "&username=" + registration.getUsername() + "\">Update</a>\n"
                        + "     </th>\n"
                        + " </tr>");
            }
        }
    }
}
