/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import dal.donghieu.UserDBContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 *
 * @author ADMIN
 */
public class loginAccountServlet extends HttpServlet {

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
        if(request.getSession().getAttribute("account") != null){
            response.sendRedirect("home");
        }else{   
        String username = (String) request.getParameter("username");
        String regisSuccess = (String) request.getSession().getAttribute("successRegis");
        String resetSuccess = (String) request.getSession().getAttribute("resetSuccess");
        String sendMailSuccess = (String) request.getSession().getAttribute("sendMailSuccess");
        request.getSession().removeAttribute("successRegis");
        request.getSession().removeAttribute("resetSuccess");
        request.getSession().removeAttribute("sendMailSuccess");
        if (regisSuccess != null && regisSuccess.length() > 0) {
            request.setAttribute("successRegis", regisSuccess);
        }
        if (resetSuccess != null && resetSuccess.length() > 0) {
            request.setAttribute("resetSuccess", resetSuccess);
        }
        if (sendMailSuccess != null && sendMailSuccess.length() > 0) {
            request.setAttribute("sendMailSuccess", sendMailSuccess);
        }
        if (username != null) {
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            UserDBContext db = new UserDBContext();
            int status = db.getStatus(username);
            if (status == 1) {
                request.setAttribute("successVerify", "Your account has been verified. Let's login now!");
            } else if (status == 2) {
                db.verifyUser(username);
                if (roleId == 2) {
                    request.setAttribute("successVerify", "Your account has been verified. Please wait for the administrator to confirm your account!");
                } else {
                    request.setAttribute("successVerify", "Your account has been verified!");
                }
            } else {
                request.setAttribute("failVerify", "Your account has been denied registration!");
            }

        }
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDBContext db = new UserDBContext();
        User account = db.getAccount(username, password);
        if (account == null) {
            request.setAttribute("loginError", "We can't find this account");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);

        } else {

            if (db.isVerified(username) == false) {
                request.setAttribute("loginError", "Your account is not verified!");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            } else {
                if (db.getStatus(username) == 2) {
                    request.setAttribute("loginError", "Your account is waiting for administrator verification!");
                    request.getRequestDispatcher("view/login.jsp").forward(request, response);
                }else if(db.getStatus(username) == 3) {
                     request.setAttribute("loginError", "Your account has been canceled!");
                    request.getRequestDispatcher("view/login.jsp").forward(request, response);
                }
            }
           HttpSession session = request.getSession();
            request.getSession().setAttribute("account", account);
          session.setAttribute("username", account.getUserName());
          if(account.getRole().getId() == 3){
              
              response.sendRedirect("dashboard");
          }else
            response.sendRedirect("home");
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
