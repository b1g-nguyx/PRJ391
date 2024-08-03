/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import dal.AccountDAO;
import jakarta.servlet.http.HttpSession;
import model.Accounts;

/**
 *
 * @author nguye
 */
@MultipartConfig
public class profile extends HttpServlet {

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
            out.println("<title>Servlet profile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet profile at " + request.getContextPath() + "</h1>");
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

        String save = request.getParameter("upload");

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
        HttpSession session = request.getSession();
        String btn = request.getParameter("btn");
        if (btn != null) {
            switch (btn) {
                case "cp" -> {
                    String OldPass = request.getParameter("OLDP");
                    try {
                        if (OldPass.equals(adao.getAccount((String) session.getAttribute("username")).getPassword())) {
                            String Npass = request.getParameter("NP");
                            String CPass = request.getParameter("CP");
                            if (!Npass.contains(" ")) {
                                if (Npass.equals(CPass)) {
                                    adao.updateP(CPass, (String) session.getAttribute("username"));
                                    request.setAttribute("scc", "Successfully for change password!");
                                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                                } else {
                                    throw new Exception("Password and Confirm password is not quals!");
                                }
                            } else {
                                throw new Exception("Your new password is cannot contain whiltespace!!");
                            }
                        } else {
                            throw new Exception("Your password is incorret!!");
                        }
                    } catch (Exception e) {
                        request.setAttribute("err", e.getMessage());
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                    }
                }
                case "all" -> {

                    try {
                        String username = request.getParameter("username");
                        Part part = request.getPart("img");
                        String realPath = request.getServletContext().getRealPath("/assets/img/accounts");
                        String source = Path.of(part.getSubmittedFileName()).getFileName().toString();
                        Accounts as = adao.getAccount(username);
                        if (!source.isEmpty()) {
                            String filename = as.getImage();
                            if (!Files.exists(Path.of(realPath))) {
                                Files.createDirectory(Path.of(realPath));
                            }
                            part.write(realPath + "/" + filename);
                            if (session.getAttribute("hehe") == null) {
                                adao.hehe(username, 1);
                                session.setAttribute("hehe", "1");
                            }
                        }

                        String firstname = request.getParameter("firstname");
                        String lastname = request.getParameter("lastname");
                        Accounts a = new Accounts(username, firstname, lastname);
                        adao.updateFromUser(a);
                        session.removeAttribute("account");
                        session.setAttribute("account", adao.getAccount(username));
                        request.setAttribute("scc", "Your information has been saved!!");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
//                         Accounts as = adao.getAccount(username);
//////                        request.getRequestDispatcher("profile.jsp").forward(request, response);
//                        try (PrintWriter out = response.getWriter()) {
//                            /* TODO output your page here. You may use following sample code. */
//                            out.println("<!DOCTYPE html>");
//                            out.println("<html>");
//                            out.println("<head>");
//                            out.println("<title>Servlet profile</title>");
//                            out.println("</head>");
//                            out.println("<body>");
//                            out.println("<h1> <img src='assets/img/accounts/" + as.getImage() + "'/></h1>");
//                            out.println("<h1>" + session.getAttribute("image") + "</h1>");
//                            out.println("<h3>" + source + "</h3>");
//                            out.println("</body>");
//                            out.println("</html>");
//                        }
                    } catch (Exception e) {
                    }

                }
            }
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
