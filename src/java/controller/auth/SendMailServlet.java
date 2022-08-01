/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import dal.donghieu.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ResetLog;
import model.User;
import controller.auth.Sendmail;

/**
 *
 * @author dell
 */
public class SendMailServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("timeOver") != null) {
            String timeOver = (String) request.getSession().getAttribute("timeOver");
            request.setAttribute("timeOver", timeOver);
            request.getSession().removeAttribute("timeOver");
        }
        request.getRequestDispatcher("view/enterMailToReset.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        UserDBContext db = new UserDBContext();
        User user = db.getUserByEmail(email);

        if (user != null) {
            String subject = "Reset Password";
            ArrayList<ResetLog> resetLogs = db.getResetLog(user);
            if (resetLogs != null) {
                db.updateResetLog(user, 5, true);
                new UserDBContext().createResetLog(user, 5);
            } else {
                db.createResetLog(user, 5);
            }
            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head> \n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Link reset password for " + user.getFullName() + " </h3>\n"
                    + "<a href=\"http://localhost:8080/SWP391_Group6/resetpassword?username=" + user.getUserName() + "&logId=" + new UserDBContext().getLastResetLogIdByUsername(user) + "\">Click here</a>"
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
            request.getSession().setAttribute("sendMailSuccess", "Send mail successfully, please check your email to reset your password");
            response.sendRedirect("login");
        } else {
            request.setAttribute("emailError", "We couldn't find an account that matches this email");
            request.getRequestDispatcher("view/enterMailToReset.jsp").forward(request, response);
        }
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
