/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import dal.donghieu.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ResetLog;
import model.User;

/**
 *
 * @author dell
 */
public class ResetServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        int logId = Integer.parseInt(request.getParameter("logId"));
        User user = db.getUserByUsername(username);
        ResetLog resetLog = db.getResetLogById(logId);
        long millis = System.currentTimeMillis();
        Timestamp now = new Timestamp(millis);
        int lastLogId = db.getLastResetLogIdByUsername(user);
        if (!(now.before(resetLog.getFrom()) || now.after(resetLog.getTo()))
                && resetLog.isChanged() == false && lastLogId == logId) {

            request.setAttribute("username", username);
            request.getRequestDispatcher("view/resetPassword.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("timeOver", "This link is no longer available, please re-send request!");
            response.sendRedirect("reset");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Validation v = new Validation();
        String newPassword = request.getParameter("newPassword");
        String reEnterPassword = request.getParameter("reEnterNewPassword");
        String username = request.getParameter("username");
        UserDBContext db = new UserDBContext();
        User user = db.getUserByUsername(username);

        int check = 0;
        
        if (newPassword == null || newPassword.length() == 0) {
            request.setAttribute("passwordError", "Enter your password, pls!");
            check = 1;
        }

        if (!newPassword.equals(reEnterPassword)) {
            request.setAttribute("passwordError", "Confirmation password is not correct");
            check = 1;
        }
        if(v.checkPassword(newPassword, request, response)==1){
            check = 1;
        }
        if (check == 0) {

            user.setPassword(newPassword);
            db.updateUser(user);
             new UserDBContext().updateResetLog(user, 0, true);
             request.getSession().setAttribute("resetSuccess", "Reset password successfully, now you can login!");
             response.sendRedirect("login");
        } else {
            request.setAttribute("username", username);
            request.getRequestDispatcher("view/resetPassword.jsp").forward(request, response);
           
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
