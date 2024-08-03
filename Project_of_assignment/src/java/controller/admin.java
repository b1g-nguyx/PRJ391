/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.PaymentDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;

/**
 *
 * @author nguye
 */
public class admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public class stat {

        String fir, sec;

        public stat(String fir, String sec) {
            this.fir = fir;
            this.sec = sec;
        }

        public String getFir() {
            return fir;
        }

        public void setFir(String fir) {
            this.fir = fir;
        }

        public String getSec() {
            return sec;
        }

        public void setSec(String sec) {
            this.sec = sec;
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin at " + request.getContextPath() + "</h1>");
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
        PaymentDAO pydao = new PaymentDAO();
        String admin = request.getParameter("admin");
        request.setAttribute("check", admin);
        if (admin != null) {

            switch (admin) {
                case "Statistics" -> {
                    List<stat> tst = new ArrayList<>();
                    tst.add(new stat("revenue", String.valueOf(pydao.totalofTotal())));
                    tst.add(new stat("product sold", "14"));
                    tst.add(new stat("The number of product", "57"));
                    tst.add(new stat("The number of user", "24"));
                    request.setAttribute("sts", tst);
                }
                case "Accounts" -> {
                    AccountDAO adao = new AccountDAO();
                    request.setAttribute("acc", adao.getAll());
                }
                case "Products" -> {
                    ProductDAO pdao = new ProductDAO();
                    request.setAttribute("pro", pdao.getAll());
                }
            }
        }
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    public List<String> getFirst() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Statistics");
        list.add("Products");
        list.add("Accounts");
        list.add("Payment");

        return list;
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
        processRequest(request, response);
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
