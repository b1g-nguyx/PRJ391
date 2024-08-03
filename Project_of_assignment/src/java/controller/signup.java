/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Accounts;
import dal.AccountDAO;
import jakarta.servlet.http.HttpSession;



/**
 *
 * @author nguye
 */
public class signup extends HttpServlet {

    AccountDAO adao = new AccountDAO();

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
            out.println("<title>Servlet signup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signup at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("signup.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        try {
            if (!user.contains(" ")) {
                Accounts accounts = adao.getAccount(user);
                if (accounts == null) {
                    String password = request.getParameter("password");
                    String confirmPassword = request.getParameter("confirmpassword");
                    if (!password.contains(" ")) {
                        if (password.equals(confirmPassword)) {
                            String id = "HE" + String.valueOf(adao.getAll().size() + 1);
                            String firstname = request.getParameter("firstname");
                            String lastname = request.getParameter("lastname");                     
                            String filename = id+".png";
                            Accounts a = new Accounts(id, user, password, firstname, lastname, filename, 0);
                            adao.setInsert(a);
                            response.sendRedirect("login.jsp");
                        } else {
                            throw new Exception("Password and Confirm password is not equals!!");
                        }
                    } else {
                        throw new Exception("password connot contain whitespace!!");
                    }
                } else {
                    throw new Exception("account is exsit");
                }
            } else {
                throw new Exception("username connot contain whitespace!!");
            }
        } catch (Exception e) {
            request.setAttribute("err", e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
    
  
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
