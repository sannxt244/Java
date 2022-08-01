/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.UserList;

import dal.minhlee.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Minh Lee
 */
public class userListServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet userListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet userListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
//        PrintWriter o = response.getWriter();
        
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("account");
        
        if (u!=null){

            if (u.getRole().getId() == 3){

                UserDAO ud = new UserDAO();
                List<User> ls = ud.getAllUser();

                request.setAttribute("user", ls);
    //            o.print("yolo!");
                request.getRequestDispatcher("Userlist/userList.jsp").forward(request, response);
            } 
            else{
            response.sendRedirect("home");
            }
        }
        else{
            response.sendRedirect("home");
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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("account");
        if ( u!= null){
            if (u.getRole().getId() == 3){
        String search = request.getParameter("search");
        PrintWriter o = response.getWriter();
        o.print(search);
        
        UserDAO ud = new UserDAO();
        List<User> userls = ud.getAllUser();
        List<User> tempUser = new ArrayList<User>();

        for (int i = 0; i < userls.size(); i++) {
            if (userls.get(i).getUserName().toLowerCase().contains(search.toLowerCase()) || userls.get(i).getUserName().equalsIgnoreCase(search) ){
                tempUser.add(userls.get(i));
            }
            else continue;
        }
        
//        o.print(tempUser.size());
        if (tempUser.size() != 0)
            request.setAttribute("user", tempUser);
        else
            request.setAttribute("user", userls);
        
        request.getRequestDispatcher("Userlist/userList.jsp").forward(request, response);
            }
            else 
                response.sendRedirect("home");
        }
        else
            response.sendRedirect("home");
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
