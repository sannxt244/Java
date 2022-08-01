/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homePage;

import controller.authorization.BaseAuthController;
import dal.donghieu.UserDBContext;
import dal.minhlee.HomeDAO;
import dal.minhlee.ProfileDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class profileServlet extends BaseAuthController {

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
            out.println("<title>Servlet profileServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet profileServlet at " + request.getContextPath() + "</h1>");
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
    protected void  processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        User user = (User) request.getSession().getAttribute("account");
        String username = user.getUserName();
        UserDBContext udb = new UserDBContext();
        User u = udb.getUserByUsername(username);
        request.setAttribute("user", u);
        
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
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
    protected void  processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserDBContext udb = new UserDBContext();
        
        String username = request.getParameter("Username");
        User u = udb.getUserByUsername(username);
        
        
        String fullname = request.getParameter("Fullname");
        String g = request.getParameter("Gender");
        String email = request.getParameter("Email");
        String phone = request.getParameter("Phone");
        Boolean gender = false;
        try{
            
            if (g.equalsIgnoreCase("true") == true){
                gender = true;
            }
            else {
                gender = false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
            u.setFullName(fullname);
        
        u.setGender(gender);
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$")){
            ProfileDAO p = new ProfileDAO();
            List<User> ls = p.getAll_User();
                    
            int check = -1;
            for (int i = 0; i < ls.size(); i++) {
                if (ls.get(i).getEmail().equals(email) != true && ls.get(i).getUserName() != username ){
                    check = 1;
                }
                else
                    check = 0;
            }                
            
            if (check == 1)
                u.setEmail(email);
            else
                request.setAttribute("messEmail", "The email was existed !");

        }
        
        else
            request.setAttribute("messEmail", "Email is wrong format ! Example: 'dongngochieu@gmail.com' ");
        if (phone.matches("^0[0-9]{9}$"))
            u.setPhoneNumber(phone);
        else
            request.setAttribute("messPhone", "Phone must be a string with 10 digits !");
        
        udb.User_update(u);
//        
        request.setAttribute("user", u);
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
        
        
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
    
//    public static void main(String[] args) {
//        
//        UserDBContext udb = new UserDBContext();
//        
//        String username = "dongngochieu33";
//        User u = udb.getUserByUsername(username);
//        
//        System.out.println(u.getFullName());
//        String fullname = "Dong Ngoc Hieu";
//        Boolean gender = false;
//        String email = "hieuYolo@gmail.com";
//        String phone = "0987656789";
//        
//        
//        u.setFullName(fullname);
//        u.setGender(gender);
//        u.setEmail(email);
//        u.setPhoneNumber(phone);
//        
//        udb.User_update(u);
//        System.out.println(udb.getUserByUsername("dongngochieu33").getFullName());
//        
//        
//    }
}
