package controller.Registrations;

import controller.authorization.BaseAuthController;
import dal.donghieu.UserDBContext;
import dal.san.CourseRegisterDBContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Registration;
import model.User;

@WebServlet(name = "AddRegistrationServlet", urlPatterns = {"/add-registration"})
public class AddRegistrationServlet extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String createdBy = req.getParameter("createdBy");
        String courseIdRaw = req.getParameter("course");
        String stateIdRaw = req.getParameter("state");
        String packageIdRaw = req.getParameter("package");
        String courseId = req.getParameter("courseId");
        // Declare DB Context
        UserDBContext userDBC = new UserDBContext();
        CourseRegisterDBContext courseRegisterDBC = new CourseRegisterDBContext();
        try {
            User user = new User(username, Boolean.parseBoolean(gender), email, mobile, createdBy);
            Registration registration = new Registration(username, Integer.parseInt(courseIdRaw), Integer.parseInt(stateIdRaw), Integer.parseInt(packageIdRaw));
            userDBC.addByAdmin(user);
            courseRegisterDBC.add(registration);
            res.sendRedirect("registrations?course=" + courseId);
        } catch (Exception e) {
        }

    }

}
