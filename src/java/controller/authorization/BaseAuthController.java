/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authorization;

import dal.donghieu.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author SAP-LAP-FPT
 */
public abstract class BaseAuthController extends HttpServlet {

    private boolean isLogin(HttpServletRequest request) {
        User account = (User) request.getSession().getAttribute("account");
        return account != null;
    }

    private boolean isAuth(HttpServletRequest request) {
        User account = (User) request.getSession().getAttribute("account");

        String url = request.getServletPath();
        UserDBContext db = new UserDBContext();
        int num = db.getNumberOfRoles(account.getUserName(), url);
        return num >= 1;

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
        if (isLogin(request)) {
            //business
            if (!isAuth(request)) {
                String url = request.getServletPath();
                request.getSession().setAttribute("link", url);
                response.sendRedirect("home");
            } else {

                processGet(request, response);
            }
        } else {
            String url = request.getServletPath();
            request.getSession().setAttribute("link", url);
            response.sendRedirect("login");
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

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
        if (isLogin(request)) {
            //business
            if (!isAuth(request)) {
                response.sendRedirect("home");
            }
            processPost(request, response);
        } else {
            response.sendRedirect("login");
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
