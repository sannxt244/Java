/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.question;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.User;

/**
 *
 * @author dell
 */
public class DownloadQuestionTemplate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DownloadQuestionTemplate() {
        super();
    }

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
        User user = new User();
        user.setUserName("dongngochieu");
        user.setPassword("1");
        Role r = new Role();
        r.setId(3);
        user.setRole(r);
        request.getSession().setAttribute("account", user);
        User account = (User) request.getSession().getAttribute("account");
        ServletContext context = getServletContext();
        String fullPath = context.getRealPath("/file/QuestionImport.xlsx");
        Path path = Paths.get(fullPath);
        byte[] data = Files.readAllBytes(path);
        // Thiết lập thông tin trả về
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=QuestionOf"+account.getUserName()+".xlsx");
        response.setContentLength(data.length);
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
        // Ghi file ra response outputstream. 
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
        response.sendRedirect("questionImport");
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
        doGet(request, response);
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
