/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;
import model.Product;
import model.Shopping_Cart_Item;

/**
 *
 * @author nguye
 */
public class shoppingCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String txt)
            throws ServletException, IOException {
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(60 * 60);
        response.addCookie(c);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean check = false;
        ProductDAO pdao = new ProductDAO();
        String idP = request.getParameter("idP");
        String quantity = request.getParameter("quantity");
        String idU = request.getParameter("idU");
        String pc = request.getParameter("pc");
        List<Shopping_Cart_Item> list = new ArrayList<>();
        Cookie[] arrC = request.getCookies();
        String txt = "";
        for (Cookie cookie : arrC) {
            if (cookie.getName().equals("cart")) {
                check = true;
                break;
            }
        }
        if (check) {
            for (Cookie cookie : arrC) {
                if (cookie.getName().equals("cart")) {
                    String cv = cookie.getValue();
                    txt = cv + "!" + idU + ":" + idP + ":" + quantity;
                }
            }
        } else {
            txt = idU + ":" + idP + ":" + quantity;
        }
        processRequest(request, response, txt);
        list.add(new Shopping_Cart_Item(Integer.parseInt(quantity), idP));
        for (Cookie cookie : arrC) {
            if (cookie.getName().equals("cart")) {
                String[] strList = cookie.getValue().split("!");
                for (String string : strList) {
                    String[] sList = string.split(":");
                    if (sList[0].equals(idU)) {
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
        if (idU != null) {
            request.setAttribute("p", pdao.getProduct(idP));
            request.setAttribute("pc", pc);
            request.getRequestDispatcher("detailproduct.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
