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
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import dal.AccountDAO;

import jakarta.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import model.Shopping_Cart_Item;


/**
 *
 * @author nguye
 */
public class login extends HttpServlet {

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
        Cookie[] arrC = request.getCookies();
        for (Cookie cookie : arrC) {
            if (cookie.getName().equals("cart")) {
                String[] strList = cookie.getValue().split("!");
                for (String string : strList) {
                    String[] sList = string.split(":");
                    if (sList[0].equals() {
                        list.add(new Shopping_Cart_Item(Integer.parseInt(sList[2]), sList[1]));
                    }
                }
                break;
            }
        }
        boolean[] Bcheck = new boolean[list.size()];
        for (boolean b : Bcheck) {
            b = false;
        }
        for (int y = 0; y < list.size() - 1; y++) {
            int quan = list.get(y).getQuantity();
            if (!Bcheck[y]) {
                for (int i = y + 1; i < list.size(); i++) {
                    if (list.get(y).getProduct_id().equals(list.get(i).getProduct_id())) {
                        quan += list.get(i).getQuantity();
                        Bcheck[i] = true;
                    }
                }
                list.get(y).setQuantity(quan);
                txt = txt.replace(txt, pc);
            }
        }
        List<Shopping_Cart_Item> lists = new ArrayList<>();

        int i = 0;
        for (Shopping_Cart_Item shopping_Cart_Item : list) {
            if (!Bcheck[i]) {
                lists.add(shopping_Cart_Item);
            }
            i++;
        }
        session.setAttribute("SPC", lists);
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
        HttpSession session = request.getSession();
        Cookie array[] = request.getCookies();
        for (Cookie cookie : array) {
            if (cookie.getName().equals("userC")) {
                request.setAttribute("user", cookie.getValue());
                continue;
            }
            if (session.getAttribute("save") != null) {
                if (cookie.getName().equals("passC")) {
                    request.setAttribute("password", cookie.getValue());
                }
            }
        }
        String logout = request.getParameter("logout");
        if (logout != null) {
            session.invalidate();
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        String rememeber = request.getParameter("remember");
        Accounts account = adao.getAccount(username);

        try {
            if (account != null) {
                if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                    session.setAttribute("login", "1");
                    session.setAttribute("role", account.getRole());
                    session.setAttribute("username", account.getUsername());
                    session.setAttribute("image", account.getImage());
                    session.setAttribute("account", adao.getAccount(username));
                    Cookie cookieUser = new Cookie("userC", username);
                    cookieUser.setMaxAge(30);
                    response.addCookie(cookieUser);
                    if (rememeber != null) {
                        Cookie cookiePassword = new Cookie("passC", password);
                        cookiePassword.setMaxAge(30);
                        response.addCookie(cookiePassword);
                        session.setAttribute("save", "1");
                    }
                    if(account.getHehe()==1){
                        session.setAttribute("hehe", "1");
                    }
                    response.sendRedirect("index.jsp");
                } else {
                    throw new Exception("password is incorrect, please check again!!");
                }
            } else {
                throw new Exception("username is not exsit, please check agian!");
            }

        } catch (Exception e) {
            request.setAttribute("err", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);

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
