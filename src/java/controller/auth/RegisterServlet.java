/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;


import dal.donghieu.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.User;

/**
 *
 * @author dell
 */
public class RegisterServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDBContext db = new UserDBContext();
        int lastId = db.getLastId();
        request.setAttribute("lastId", lastId);
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Validation v = new Validation();
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String roleId = request.getParameter("role");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int check = 0;
        if(v.checkGender(gender, request, response) == 1) check = 1;
        if(v.checkPhoneNumber(phoneNumber, request, response) == 1) check = 1;
        if(v.checkFullName(fullName, request, response)==1)check = 1;
        if(v.checkEmai(email, request, response)==1)check = 1;
        if (v.checkUsername(username, request, response) == 1)check = 1;
        if (v.checkPassword(password, request, response) == 1)check = 1;
        if(v.checkRoleId(roleId, request, response) == 1) check = 1;
        
        
      
        request.setAttribute("fullName", fullName);
        request.setAttribute("gender", (gender!=null && gender.length() >0)?gender.equals("Male"):null);
        request.setAttribute("role", (roleId!=null && roleId.length() >0)?roleId.equals("Customer"):null);
        request.setAttribute("email", email);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
      
        if(check==0){ 
             Role role = new Role();
             if(roleId.equals("Customer")){
                 role.setId(1);
             }
             else role.setId(2);
            User user = new User( fullName, gender.equals("Male"), email, phoneNumber, role, username, password);
            UserDBContext db = new UserDBContext();
            db.registerUser(user);
            
            
             String subject = "Verify account";
             String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head> \n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Link reset password for " + user.getFullName() + " </h3>\n"
                    + "<a href=\"http://localhost:8080/SWP391_Group6/login?username=" + user.getUserName() +"&roleId=" + user.getRole().getId()+"\">Click here to verify</a>"
                    //                + "    <div>Full Name :Dong Ngoc Hieu</div>\n"
                    //                + "    <div>Phone : 0359238165</div>\n"
                    //                + "    <div>address : Khoi Ky, Dai Tu, Thai Nguyen</div>\n"
                    //                + "    <h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";
             Sendmail s = new Sendmail();
            s.send(user.getEmail(), subject, message, "hieudnhe153007@fpt.edu.vn", "hoilamgi11");            
            request.getSession().setAttribute("successRegis", "Check your mail to verify your account!");
            response.sendRedirect("login");
        }else{
            request.getRequestDispatcher("view/register.jsp").forward(request, response);
        }
    }

}
